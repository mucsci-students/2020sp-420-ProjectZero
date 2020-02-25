package projectzero.core;

import javax.lang.model.SourceVersion;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UmlClass {
    private String name;
    private Map<String, Field> fieldMap;

    public UmlClass() {
        this.fieldMap = new HashMap<>();
    }

    public UmlClass(String name) {
        this.fieldMap = new HashMap<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Field> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Field> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public boolean addField(Field field) {
        if (!SourceVersion.isIdentifier(field.getName()) ||  fieldMap.containsKey(field.getName())) {
            return false;
        }

        fieldMap.put(field.getName(), field);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UmlClass umlClass = (UmlClass) o;
        return Objects.equals(getName(), umlClass.getName()) &&
                Objects.equals(getFieldMap(), umlClass.getFieldMap());
    }
}
