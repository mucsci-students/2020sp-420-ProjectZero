package projectzero.core;

import java.util.HashMap;
import java.util.Objects;
import java.util.Map;

public class UmlClass {
    private String name;
    private Map<String, Field> fieldMap;
    private Map<String, Method> methodMap;
    private Map<String, Relationship> relationshipMap;

    public UmlClass() {
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
        if (fieldMap.containsKey(field.getName())) {
            return false;
        }

        fieldMap.put(field.getName(), field);
        return true;
    }

    public boolean addMethod(Method method) {
        if (methodMap.containsKey(method.getName())) {
            return false;
        }

        methodMap.put(method.getName(), method);
        return true;
    }

    public boolean addRelationship(Relationship relationship) {
        if (relationshipMap.containsKey(relationship.getTo().getName())) {
            return false;
        }

        if (relationship.getTo().getRelationshipMap().get(this.getName()) != null) {
            return false;
        }

        relationshipMap.put(relationship.getTo().getName(), relationship);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UmlClass umlClass = (UmlClass) o;
        return Objects.equals(getName(), umlClass.getName()) &&
                Objects.equals(getFieldMap(), umlClass.getFieldMap()) &&
                Objects.equals(getMethodMap(), umlClass.getMethodMap()) &&
                Objects.equals(getRelationshipMap(), umlClass.getRelationshipMap());
    }
}
