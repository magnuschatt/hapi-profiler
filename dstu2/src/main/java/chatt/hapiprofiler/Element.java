package chatt.hapiprofiler;

import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Description;
import com.sun.istack.internal.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Element {

    private final String name;
    private int min = 0;
    private int max = 1;
    private int order;
    private boolean modifier;
    private boolean summary;
    private String shortDescr;
    private String definition;
    private List<Class> allowedTypes;
    private List<Element> children;

    public Element(@NotNull String name) {
        checkNotNull(name, "Element name can not be set to null.");
        checkArgument(!name.equals("Element name can not be set to empty string."));
        this.name = name;
    }

    public static Element from(@NotNull Field field) {
        Child childAnno = field.getAnnotation(Child.class);
        checkNotNull(childAnno, "No Child annotation present on given field.");

        Element element = new Element(childAnno.name());

        element.setMin(childAnno.min());
        element.setMax(childAnno.max());
        element.setAllowedTypes(childAnno.type());
        element.setOrder(childAnno.order());
        element.setModifier(childAnno.modifier());
        element.setSummary(childAnno.summary());

        Description descrAnno = field.getAnnotation(Description.class);
        if (descrAnno != null) {
            element.setShortDescr(descrAnno.shortDefinition());
            element.setDefinition(descrAnno.formalDefinition());
        }

        return element;
    }

    @Override
    public String toString() {
        return name;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public int getMin() {
        return min;
    }
    public Element setMin(int min) {
        this.min = min;
        return this;
    }

    public int getMax() {
        return max;
    }
    public Element setMax(int max) {
        this.max = max;
        return this;
    }

    public Element setCardinality(int min, int max) {
        setMin(min);
        setMax(max);
        return this;
    }

    public List<Class> getAllowedTypes() {
        if (allowedTypes == null) {
            allowedTypes = new ArrayList<>();
        }
        return allowedTypes;
    }
    public Element setAllowedTypes(List<Class> allowedTypes) {
        this.allowedTypes = allowedTypes;
        return this;
    }
    public Element setAllowedTypes(Class... allowedTypes) {
        getAllowedTypes().clear();
        getAllowedTypes().addAll(Arrays.asList(allowedTypes));
        return this;
    }

    public int getOrder() {
        return order;
    }
    public Element setOrder(int order) {
        this.order = order;
        return this;
    }

    public boolean isModifier() {
        return modifier;
    }
    public Element setModifier(boolean modifier) {
        this.modifier = modifier;
        return this;
    }

    public boolean isSummary() {
        return summary;
    }
    public Element setSummary(boolean summary) {
        this.summary = summary;
        return this;
    }

    public String getShortDescr() {
        return shortDescr;
    }
    public Element setShortDescr(String shortDescr) {
        this.shortDescr = shortDescr;
        return this;
    }

    public String getDefinition() {
        return definition;
    }
    public Element setDefinition(String definition) {
        this.definition = definition;
        return this;
    }

    public List<Element> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }
    public boolean hasChildren() {
        return !getChildren().isEmpty();
    }

    public Element deprecate() {
        setCardinality(0, 0);
        return this;
    }
}
