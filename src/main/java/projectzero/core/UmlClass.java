package projectzero.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projectzero.core.exceptions.InvalidNameException;

import javax.lang.model.SourceVersion;
import java.util.List;
import java.util.Objects;

public class UmlClass {
    private final String name;
    private final SimpleDoubleProperty x;
    private final SimpleDoubleProperty y;
    private final ObservableList<Field> fields;
    private final ObservableList<Method> methods;
    private final ObservableList<Relationship> relationships;

    /**
     *  Constructor will create the most basic UmlClass possible. It
     *      will assign UmlClass's name to the one provided. Then assign
     *      all other parameters to 0 or empty list where necessary.
     * @param name is the identifier for the UmlClass.
     * @throws InvalidNameException this will be thrown if an invalid name is provided.
     */
    public UmlClass(String name) throws InvalidNameException {
        if (!SourceVersion.isIdentifier(name)) {
            throw new InvalidNameException();
        }

        this.name = name;
        this.x = new SimpleDoubleProperty(0);
        this.y = new SimpleDoubleProperty(0);
        this.fields = FXCollections.observableArrayList();
        this.methods = FXCollections.observableArrayList();
        this.relationships = FXCollections.observableArrayList();
    }

    /**
     *
     * @param name is the identifier for the UmlClass.
     * @param x is the X of the GUI representation of the UmlClass.
     * @param y is the Y of the GUI representation of the UmlClass.
     * @param fields list of Fields' associated with this UmlClass.
     * @param methods list of Methods' associated with this UmlClass.
     * @param relationships list of Relationships' associated with this UmlClass.
     * @throws InvalidNameException this will be thrown if an invalid name is provided.
     */
    private UmlClass(@JsonProperty("name") String name,
                     @JsonProperty("x") double x,
                     @JsonProperty("y") double y,
                     @JsonProperty("fields") List<Field> fields,
                     @JsonProperty("methods") List<Method> methods,
                     @JsonProperty("relationships") List<Relationship> relationships) throws InvalidNameException {
        if (!SourceVersion.isIdentifier(name)) {
            throw new InvalidNameException();
        }

        this.name = name;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.fields = FXCollections.observableList(fields);
        this.methods = FXCollections.observableList(methods);
        this.relationships = FXCollections.observableList(relationships);
    }

    /**
     * @return name identifier of this UmlClass.
     */
    public String getName() {
        return name;
    }

    /**
     * @return X position of the GUI representation in double form.
     */
    public double getX() {
        return x.get();
    }

    /**
     * @return X position of the GUI representation in SimpleDoubleProperty form.
     */
    public SimpleDoubleProperty xProperty() {
        return x;
    }
    /**
     * @return set X position of the GUI representation.
     */
    public void setX(double x) {
        this.x.set(x);
    }
    /**
     * @return Y position of the GUI representation in double form.
     */
    public double getY() {
        return y.get();
    }
    /**
     * @return Y position of the GUI representation in SimpleDoubleProperty form.
     */
    public SimpleDoubleProperty yProperty() {
        return y;
    }
    /**
     * @return set Y position of the GUI representation.
     */
    public void setY(double y) {
        this.y.set(y);
    }

    /**
     * @return an ObservableList<Field> associated with this UmlClass.
     */
    public ObservableList<Field> getFields() {
        return fields;
    }
    /**
     * @return an ObservableList<Method> associated with this UmlClass.
     */
    public ObservableList<Method> getMethods() {
        return methods;
    }
    /**
     * @return an ObservableList<Relationship> associated with this UmlClass.
     */
    public ObservableList<Relationship> getRelationships() {
        return relationships;
    }

    /**
     *
     * @param field a Field object to be added to this UmlClass's ObservableList<Field>.
     * @return it will return false if the ObservableList<Field> contains a Field equal
     *      to the one provided and not add a duplicate Field object. It will return true
     *      if the ObservableList<Field> does not contain an equal Field object and add
     *      it to the ObservableList<Field>.
     */
    public boolean addField(Field field) {
        if (fields.contains(field)) {
            return false;
        }

        fields.add(field);
        return true;
    }

    /**
     * @param fieldName the name of a Field object trying to retrieved from the
     *                      ObservableList<Field>.
     * @return will return a Field object of the fieldName provided else it will
     *          return null.
     */
    public Field getField(String fieldName) {
        for (Field f : fields) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }
        return null;
    }
    /**
     * @param methodName the name of a Method object trying to retrieved from the
     *                      ObservableList<Method>.
     * @return will return a Method object of the fieldName provided else it will
     *          return null.
     */
    public Method getMethod(String methodName) {
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                return m;
            }
        }
        return null;
    }
    /**
     * @param field the name of a Field object trying to removed from the
     *                      ObservableList<Field>.
     * @return will return true if the Field object provided was removed
     *          else it will return false.
     */
    public boolean deleteField(Field field) {
        return fields.remove(field);
    }

    /**
     * @param oldField the Field object that will be Updated.
     * @param newField the Field object that has the updated data.
     * @return will return false if the Field could not be updated
     *          else return true otherwise.
     */
    public boolean updateField(Field oldField, Field newField) {
        boolean added = addField(newField);

        if (added) {
            fields.remove(oldField);
            return true;
        }

        return false;
    }

    /**
     *
     * @param method a Method object that will try to be added to
     *               the ObservableList<Method>.
     * @return will return true if the Method object was successfully
     *      added to the ObservableList<Method> else return false.
     */
    public boolean addMethod(Method method) {
        if (methods.contains(method)) {
            return false;
        }

        methods.add(method);
        return true;
    }

    /**
     *
     * @param method a method Object that will be removed from the current
     *               ObservableList<Method>.
     * @return will return true if this Method object was deleted from the
     *              current ObservableList<Method> else return false.
     */
    public boolean deleteMethod(Method method) {
        return methods.remove(method);
    }

    /**
     * @param oldMethod the Method object that will be Updated.
     * @param newMethod the Method object that has the updated data.
     * @return will return false if the Method could not be updated
     *          else return true otherwise.
     */
    public boolean updateMethod(Method oldMethod, Method newMethod) {
        boolean added = addMethod(newMethod);

        if (added) {
            methods.remove(oldMethod);
            return true;
        }

        return false;
    }

    /**
     *
     * @param relationship a Relationship object to be added to
     *                     ObservableLsit<Relationship>.
     * @return  will return true if the Relationship object provided
     *          was added to the ObservableList<Relationship> else
     *          return false.
     */
    public boolean addRelationship(Relationship relationship) {
        if (relationships.contains(relationship)) {
            return false;
        }

//        for (Relationship r : relationship.getTo().getRelationships()) {
//            if (r.getTo().equals(this.getName())) {
//                return false;
//            }
//        }

        relationships.add(relationship);
        return true;
    }

    /**
     *
     * @param relationship a Relationship object to be deleted from
     *                     ObservableList<Relationship>.
     * @return  will return true if this object was removed from
     *          ObservableList<Relationship> else it returns false.
     */
    public boolean deleteRelationship(Relationship relationship) {
        return relationships.remove(relationship);
    }

    /**
     * @param oldRelationship the Relationship object that will be Updated.
     * @param newRelationship the Relationship object that has the updated data.
     * @return will return false if the Relationship could not be updated
     *          else return true otherwise.
     */
    public boolean updateRelationship(Relationship oldRelationship, Relationship newRelationship) {
        boolean added = addRelationship(newRelationship);

        if (added) {
            relationships.remove(oldRelationship);
            return true;
        }

        return false;
    }

    /**
     * @return will return a String representation of this UmlClass.
     */
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

    /**
     *
     * @param o checks if o object is equal to this UmlClass.
     * @return true if this object is equal to the o object provided
     *         or false otherwise.
     */
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
