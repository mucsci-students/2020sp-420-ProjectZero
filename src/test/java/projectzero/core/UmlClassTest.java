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
