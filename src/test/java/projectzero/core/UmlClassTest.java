package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UmlClassTest {

    @Test
    void testAddFieldReturnsTrueOnSuccess() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addField(new Field("field")));
    }

    @Test
    void testAddFieldReturnsFalseOnBadName() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertFalse(umlClass.addField(new Field("!field")));
    }

    @Test
    void testAddFieldReturnsFalseOnDuplicateField() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addField(new Field("field")));
        Assertions.assertFalse(umlClass.addField(new Field("field")));
    }

    @Test
    void testDeleteFieldReturnsTrueOnNullValue() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.deleteField("field"));
    }

    @Test
    void testDeleteFieldReturnsTrueOnSuccessfulRemove() {
        UmlClass umlClass = new UmlClass("class");
        umlClass.addField(new Field("field"));
        Assertions.assertEquals(1, umlClass.listFields().size());

        Assertions.assertTrue(umlClass.deleteField("field"));
        Assertions.assertEquals(0, umlClass.listFields().size());
    }

    @Test
    void testGetFieldReturnsNullOnNonExistentField() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertNull(umlClass.getField("field"));
    }

    @Test
    void testGetFieldReturnsFieldOnExistentField() {
        UmlClass umlClass = new UmlClass("class");
        Field field = new Field("field");

        umlClass.addField(field);

        Assertions.assertEquals(field, umlClass.getField("field"));
    }

    @Test
    void testListFieldsReturnsCorrectValues() {
        UmlClass umlClass = new UmlClass("class");
        Assertions.assertEquals(0, umlClass.listFields().size());

        Field field = new Field("field");
        umlClass.addField(field);
        Assertions.assertEquals(1, umlClass.listFields().size());

        umlClass.deleteField("field");
        Assertions.assertEquals(0, umlClass.listFields().size());
    }

    @Test
    public void testUpdateFieldReturnsTrueOnSuccessfulAdd() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addField(new Field("field")));
    }

    @Test
    public void testUpdateFieldReturnsTrueOnSuccessfulUpdate() {
        UmlClass umlClass = new UmlClass("class");

        Field fieldOriginal = new Field("fieldOriginal");
        Field fieldReplacement = new Field("fieldReplacement");

        Assertions.assertTrue(umlClass.updateField(fieldOriginal.getName(), fieldOriginal));
        Assertions.assertTrue(umlClass.updateField(fieldReplacement.getName(), fieldReplacement));
    }

    @Test
    void testAddMethodReturnsTrueOnSuccess() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addMethod(new Method("method")));
    }

    @Test
    void testAddMethodReturnsFalseOnBadName() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertFalse(umlClass.addMethod(new Method("!method")));
    }

    @Test
    void testAddMethodReturnsFalseOnDuplicateMethod() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addMethod(new Method("method")));
        Assertions.assertFalse(umlClass.addMethod(new Method("method")));
    }

    @Test
    public void testAddRelationshipReturnsTrueOnSuccess() {
        UmlClass from = new UmlClass("from");
        UmlClass to = new UmlClass("to");

        Assertions.assertTrue(from.addRelationship(new Relationship(to)));
    }

    @Test
    public void testAddRelationshipReturnsFalseOnDuplicateRelationship() {
        UmlClass from = new UmlClass("from");
        UmlClass to = new UmlClass("to");

        Assertions.assertTrue(from.addRelationship(new Relationship(to)));
        Assertions.assertFalse(from.addRelationship(new Relationship(to)));
    }

    @Test
    public void testAddRelationshipReturnsFalseOnInverseRelationship() {
        UmlClass from = new UmlClass("from");
        UmlClass to = new UmlClass("to");

        Assertions.assertTrue(from.addRelationship(new Relationship(to)));
        Assertions.assertFalse(to.addRelationship(new Relationship(from)));
    }
}
