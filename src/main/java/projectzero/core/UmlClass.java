package projectzero.core;

import javax.lang.model.SourceVersion;
import java.util.*;

public class UmlClass {
    private String name;
    private Map<String, Field> fieldMap;
    private Map<String, Method> methodMap;
    private Map<String, Relationship> relationshipMap;

    public UmlClass() {
        this.fieldMap = new HashMap<>();
        this.methodMap = new HashMap<>();
        this.relationshipMap = new HashMap<>();
    }

    public UmlClass(String name) {
        this.name = name;
        this.fieldMap = new HashMap<>();
        this.methodMap = new HashMap<>();
        this.relationshipMap = new HashMap<>();
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


    public Map<String, Method> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, Method> methodMap) {
        this.methodMap = methodMap;
    }


    public Map<String, Relationship> getRelationshipMap() {
        return relationshipMap;
    }

    public void setRelationshipMap(Map<String, Relationship> relationshipMap) {
        this.relationshipMap = relationshipMap;
    }

    public boolean addField(Field field) {
        if (!SourceVersion.isIdentifier(field.getName()) || fieldMap.containsKey(field.getName())) {
            return false;
        }

        fieldMap.put(field.getName(), field);
        return true;
    }

    public boolean deleteField(String fieldName) {
        this.fieldMap.remove(fieldName);
        return true;
    }

    public Field getField(String fieldName) {
        return fieldMap.getOrDefault(fieldName, null);
    }

    public List<Field> listFields() {
        return new ArrayList<>(fieldMap.values());
    }

    public boolean updateField(String fieldName, Field field) {
        this.fieldMap.replace(fieldName, field);
        return true;
    }

    public boolean addMethod(Method method) {
        if (!SourceVersion.isIdentifier(method.getName()) || methodMap.containsKey(method.getName())) {
            return false;
        }

        methodMap.put(method.getName(), method);
        return true;
    }

    public boolean addRelationship(Relationship relationship) {
        if (this.relationshipMap.containsKey(relationship.getTo().getName()) || relationship.getTo().getRelationshipMap().containsKey(this.getName())) {
            return false;
        }

        this.relationshipMap.put(relationship.getTo().getName(), relationship);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UmlClass umlClass = (UmlClass) o;
        return Objects.equals(getName(), umlClass.getName()) &&
                Objects.equals(getMethodMap(), umlClass.getMethodMap()) &&
                Objects.equals(getFieldMap(), umlClass.getFieldMap()) &&
                Objects.equals(getRelationshipMap(), umlClass.getRelationshipMap());
    }
}
