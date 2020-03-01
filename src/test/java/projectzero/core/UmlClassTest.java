package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UmlClassTest {

    @Test
    public void testUmlClassAddFieldSucceedsOnValidField() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Field field = new Field("field");

            Assertions.assertTrue(umlClass.addField(field));
            Assertions.assertTrue(umlClass.getFields().contains(field));
        });
    }

    @Test
    public void testUmlClassAddFieldReturnsFalseOnDuplicateField() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Field field1 = new Field("field");
            Field field2 = new Field("field");

            Assertions.assertTrue(umlClass.addField(field1));
            Assertions.assertTrue(umlClass.getFields().contains(field1));
            Assertions.assertFalse(umlClass.addField(field2));
        });
    }

    @Test
    public void testUmlClassAddMethodSucceedsOnValidMethod() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Method method = new Method("method1");

            umlClass.addMethod(method);

            Assertions.assertTrue(umlClass.getMethods().contains(method));
        });
    }

    @Test
    public void testUmlClassAddMethodReturnsFalseOnDuplicateMethod() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Method method1 = new Method("method");
            Method method2 = new Method("method");

            Assertions.assertTrue(umlClass.addMethod(method1));
            Assertions.assertTrue(umlClass.getMethods().contains(method1));
            Assertions.assertFalse(umlClass.addMethod(method2));
        });
    }

    @Test
    public void testUmlClassAddRelationshipSucceedsOnValidRelationship() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass from = new UmlClass("From");
            UmlClass to1 = new UmlClass("To1");
            UmlClass to2 = new UmlClass("To2");

            Relationship relationship1 = new Relationship(to1);
            Relationship relationship2 = new Relationship(to2);

            Assertions.assertTrue(from.addRelationship(relationship1));
            Assertions.assertTrue(from.getRelationships().contains(relationship1));
            Assertions.assertTrue(from.addRelationship(relationship2));
            Assertions.assertTrue(from.getRelationships().contains(relationship2));
            Assertions.assertTrue(to1.addRelationship(relationship2));
            Assertions.assertTrue(to1.getRelationships().contains(relationship2));
        });
    }

    @Test
    public void testUmlClassAddRelationshipReturnsFalseOnDuplicateRelationship() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass from = new UmlClass("From");
            UmlClass to = new UmlClass("To");

            Relationship relationship1 = new Relationship(to);
            Relationship relationship2 = new Relationship(to);

            Assertions.assertTrue(from.addRelationship(relationship1));
            Assertions.assertTrue(from.getRelationships().contains(relationship1));
            Assertions.assertFalse(from.addRelationship(relationship2));
        });
    }

    @Test
    public void testUmlClassAddRelationshipReturnsFalseOnInverseRelationship() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass from = new UmlClass("From");
            UmlClass to = new UmlClass("To");

            Relationship toTo = new Relationship(to);
            Relationship toFrom = new Relationship(from);

            Assertions.assertTrue(from.addRelationship(toTo));
            Assertions.assertFalse(to.addRelationship(toFrom));
        });
    }
}
