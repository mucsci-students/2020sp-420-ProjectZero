package projectzero.core;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import projectzero.core.exceptions.DuplicateAttributeException;
import projectzero.core.exceptions.ExistingInverseRelationshipException;
import projectzero.core.exceptions.InvalidNameException;

import javax.lang.model.SourceVersion;
import java.io.IOException;
import java.util.*;

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

    public boolean updateField(Field oldField, Field newField) {
        boolean added = addField(newField);

        if (added) {
            fields.remove(oldField);
            return true;
        }

        return false;
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

    public boolean updateMethod(Method oldMethod, Method newMethod) {
        boolean added = addMethod(newMethod);

        if (added) {
            methods.remove(oldMethod);
            return true;
        }

        return false;
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

    public boolean updateRelationship(Relationship oldRelationship, Relationship newRelationship) {
        boolean added = addRelationship(newRelationship);

        if (added) {
            relationships.remove(oldRelationship);
            return true;
        }

        return false;
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
