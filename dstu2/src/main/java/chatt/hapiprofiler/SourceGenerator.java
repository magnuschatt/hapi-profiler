package chatt.hapiprofiler;

import ca.uhn.fhir.model.api.IDatatype;
import ca.uhn.fhir.model.api.annotation.Block;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.BaseResource;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class SourceGenerator {

    private ResourceProfile profile;
    private Map<Element, JavaClassSource> srcMap;

    public void generate(ResourceProfile profile) {
        this.profile = profile;
        initSrcMap();
        initClasses();

        createFields();

        joinClasses();
        writeToFile();
    }

    private void createFields() {
        for (Element element : srcMap.keySet()) {
            JavaClassSource src = srcMap.get(element);
            List<Element> children = getChildren(element);
            for (Element child : children) {
                createField(child, src);
            }
        }
    }

    private void createField(Element element, JavaClassSource src) {
        for (Class aClass : element.getAllowedTypes()) {
            rootSrc().addImport(aClass);
        }

        String fieldType = getInferredFieldType(element);

        src.addField("protected " + fieldType + " " + element.getName());
    }

    private String getInferredFieldType(Element element) {
        List<Class> types = element.getAllowedTypes();
        int numTypes = types.size();
        boolean pureRef = types.stream()
                .filter(BaseResource.class::isAssignableFrom)
                .count() == numTypes;

        String fieldType;
        if (numTypes == 0) {
            fieldType = capitalize(element.getName());
        } else if (pureRef) {
            fieldType = ResourceReferenceDt.class.getSimpleName();
            rootSrc().addImport(ResourceReferenceDt.class);
        } else if (numTypes == 1){
            fieldType = types.get(0).getSimpleName();
        } else {
            fieldType = IDatatype.class.getSimpleName();
            rootSrc().addImport(IDatatype.class);
        }

        if (element.getMax() != 1) {
            fieldType = "List<" + fieldType + ">";
            rootSrc().addImport(List.class);
        }

        return fieldType;
    }

    private List<Element> getChildren(Element parent) {
        return parent == null ? profile.getChildren() : parent.getChildren();
    }

    private void joinClasses() {
        for (JavaClassSource src : srcMap.values()) {
            if (src == rootSrc()) continue;
            rootSrc().addNestedType(src);
        }
    }

    private void initClasses() {
        rootSrc().setName(profile.getName());
        rootSrc().extendSuperType(BaseResource.class);
        rootSrc().addAnnotation(ResourceDef.class)
                .setStringValue("name", profile.getBaseName())
                .setStringValue("id", profile.getId())
                .setStringValue("profile", profile.getUrl());

        for (Element element : srcMap.keySet()) {
            if (element == null) continue;
            JavaClassSource src = srcMap.get(element);

            rootSrc().addImport(Block.class);
            src.addAnnotation(Block.class);
            src.setName(capitalize(element.getName()));
            src.setStatic(true);
        }
    }

    private void initSrcMap() {
        this.srcMap = new LinkedHashMap<>();
        srcMap.put(null, Roaster.create(JavaClassSource.class));

        Queue<Element> queue = new ArrayDeque<>();
        queue.addAll(profile.getChildren());
        while (!queue.isEmpty()) {
            Element element = queue.poll();
            if (element.hasChildren()) {
                queue.addAll(element.getChildren());
                srcMap.put(element, Roaster.create(JavaClassSource.class));
            }
        }
    }

    private JavaClassSource rootSrc() {
        return srcMap.get(null);
    }

    private void writeToFile() {
        String src = rootSrc().toString();
        String filename = rootSrc().getName() + ".java";
        String dir = "src/test/java/";

        Util.write(dir + filename, src);
    }

}
