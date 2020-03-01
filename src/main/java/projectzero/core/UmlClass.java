package projectzero.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import projectzero.core.exceptions.InvalidNameException;

import javax.lang.model.SourceVersion;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UmlClass {
    private final String name;
    private final List<Field> fields;
    private final List<Method> methods;
    private final List<Relationship> relationships;

    public UmlClass(String name) throws InvalidNameException {
        if (!SourceVersion.isIdentifier(name)) {
            throw new InvalidNameException();
        }

        this.name = name;
        this.fields = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.relationships = new ArrayList<>();
    }

    private UmlClass(@JsonProperty("name") String name,
                     @JsonProperty("fields") List<Field> fields,
                     @JsonProperty("methods") List<Method> methods,
                     @JsonProperty("relationships") List<Relationship> relationships) throws InvalidNameException {
        if (!SourceVersion.isIdentifier(name)) {
            throw new InvalidNameException();
        }

        this.name = name;
        this.fields = fields;
        this.methods = methods;
        this.relationships = relationships;
    }

    public String getName() {
        return name;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public boolean addField(Field field) {
        if (fields.contains(field)) {
            return false;
        }

        fields.add(field);
        return true;
    }

    public boolean deleteField(Field field) {
        return fields.remove(field);
    }

    public Field getField(Field field) {
        int index = fields.indexOf(field);
        return index == -1 ? null : fields.get(index);
    }

    public boolean updateField(Field oldField, Field newField) {
        fields.remove(oldField);
        return addField(newField);
    }

    public boolean addMethod(Method method) {
        if (methods.contains(method)) {
            return false;
        }

        methods.add(method);
        return true;
    }

    public boolean deleteMethod(Method method) {
        return methods.remove(method);
    }

    public Method getMethod(Method method) {
        int index = methods.indexOf(method);
        return index == -1 ? null : methods.get(index);
    }

    public boolean updateMethod(Method oldMethod, Method newMethod) {
        methods.remove(oldMethod);
        return addMethod(newMethod);
    }

    public boolean addRelationship(Relationship relationship) {
        if (relationships.contains(relationship)) {
            return false;
        }

        for (Relationship r : relationship.getTo().getRelationships()) {
            if (r.getTo().equals(this)) {
                return false;
            }
        }

        relationships.add(relationship);
        return true;
    }

    public boolean deleteRelationship(Relationship relationship) {
        return relationships.remove(relationship);
    }

    public Relationship getRelationship(Relationship relationship) {
        int index = relationships.indexOf(relationship);
        return index == -1 ? null : relationships.get(index);
    }

    public boolean updateRelationship(Relationship oldRelationship, Relationship newRelationship) {
        relationships.remove(oldRelationship);
        return addRelationship(newRelationship);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UmlClass umlClass = (UmlClass) o;
        return Objects.equals(getName(), umlClass.getName()) &&
                Objects.equals(getFields(), umlClass.getFields()) &&
                Objects.equals(getMethods(), umlClass.getMethods()) &&
                Objects.equals(getRelationships(), umlClass.getRelationships());
    }
}
