package projectzero.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projectzero.core.exceptions.InvalidNameException;

import javax.lang.model.SourceVersion;
import java.util.List;
import java.util.Objects;

public class UmlClass {
    private final String name;
    private double x, y;
    private final ObservableList<Field> fields;
    private final ObservableList<Method> methods;
    private final ObservableList<Relationship> relationships;

    public UmlClass(String name) throws InvalidNameException {
        if (!SourceVersion.isIdentifier(name)) {
            throw new InvalidNameException();
        }

        this.name = name;
        this.fields = FXCollections.observableArrayList();
        this.methods = FXCollections.observableArrayList();
        this.relationships = FXCollections.observableArrayList();
    }

    private UmlClass(@JsonProperty("name") String name,
                     @JsonProperty("fields") List<Field> fields,
                     @JsonProperty("methods") List<Method> methods,
                     @JsonProperty("relationships") List<Relationship> relationships) throws InvalidNameException {
        if (!SourceVersion.isIdentifier(name)) {
            throw new InvalidNameException();
        }

        this.name = name;
        this.fields = FXCollections.observableList(fields);
        this.methods = FXCollections.observableList(methods);
        this.relationships = FXCollections.observableList(relationships);
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public ObservableList<Field> getFields() {
        return fields;
    }

    public ObservableList<Method> getMethods() {
        return methods;
    }

    public ObservableList<Relationship> getRelationships() {
        return relationships;
    }

    public boolean addField(Field field) {
        if (fields.contains(field)) {
            return false;
        }

        fields.add(field);
        return true;
    }

    public Field getField(String fieldName) {
        for (Field f : fields) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }
        return null;
    }

    public Method getMethod(String methodName) {
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                return m;
            }
        }
        return null;
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
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + "\n");
        stringBuilder.append("Fields:\n");
        for (Field f : fields) {
            stringBuilder.append(f + "\n");
        }
        stringBuilder.append("\n");
        stringBuilder.append("Methods:\n");
        for (Method m : methods) {
            stringBuilder.append(m + "\n");
        }
        stringBuilder.append("\n");
        stringBuilder.append("Relationships:\n");
        for (Relationship r : relationships) {
            stringBuilder.append(r + "\n");
        }
        return stringBuilder.toString();
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
