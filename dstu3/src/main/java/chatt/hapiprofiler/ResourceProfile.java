package chatt.hapiprofiler;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import org.hl7.fhir.dstu3.model.Resource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ResourceProfile {

    private final Class<? extends Resource> baseResource;

    private List<Element> children;
    private String name;
    private String id;
    private String url;

    public ResourceProfile(Class<? extends Resource> baseResource) {
        checkNotNull(baseResource, "Parameter value, baseResource, must not be null.");
        ResourceDef resourceDef = baseResource.getAnnotation(ResourceDef.class);
        checkNotNull(baseResource, "Parameter value, baseResource, must have the ResourceDef annotation.");

        this.baseResource = baseResource;
        this.name = resourceDef.name();
        this.id = resourceDef.id();
        this.url = resourceDef.profile();

        getChildren().addAll(extractElements(baseResource));
    }

    private List<Element> extractElements(Class aClass) {
        List<Element> result = new ArrayList<>();
        for (Field field : aClass.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Child.class)) continue;
            Element element = Element.from(field);
            result.add(element);

            if (element.getAllowedTypes().isEmpty()) {
                String typeName = Util.extractUnlistedType(field);

                Class type = Arrays.stream(baseResource.getDeclaredClasses())
                        .filter(c -> c.getName().equals(typeName))
                        .findAny().orElse(null);

                element.getChildren().addAll(extractElements(type));
            }
        }
        return result;
    }

    public List<Element> getChildren() {
        if (children == null) children = new ArrayList<>();
        return children;
    }

    public ResourceProfile setChildren(List<Element> children) {
        this.children = children;
        return this;
    }

    public ResourceProfile removeChild(String name) {
        children.removeIf(c -> c.getName().equals(name));
        return this;
    }

    public Class<? extends Resource> getBaseResource() {
        return baseResource;
    }

    public String getName() {
        return name;
    }

    public ResourceProfile setName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    public ResourceProfile setId(String id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ResourceProfile setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getBaseName() {
        return baseResource.getSimpleName();
    }

    public Element addChild() {
        Element element = new Element();
        getChildren().add(element);
        return element;
    }
}
