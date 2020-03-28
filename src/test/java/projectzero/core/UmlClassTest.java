package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class UmlClassTest {

    @Test
    public void testUmlClassAddFieldSucceedsOnValidField() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Field field = new Field.Builder().withName("field").withType("type").build();

            Assertions.assertTrue(umlClass.addField(field));
            Assertions.assertTrue(umlClass.getFields().contains(field));
        });
    }

    @Test
    public void testUmlClassAddFieldReturnsFalseOnDuplicateField() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Field field1 = new Field.Builder().withName("field").withType("type").build();
            Field field2 = new Field.Builder().withName("field").withType("type").build();

            Assertions.assertTrue(umlClass.addField(field1));
            Assertions.assertTrue(umlClass.getFields().contains(field1));
            Assertions.assertFalse(umlClass.addField(field2));
        });
    }

    @Test
    public void testUmlClassDeleteFieldReturnsTrueOnSuccessfulDelete() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Field field = new Field.Builder().withName("field").withType("type").build();

            Assertions.assertTrue(umlClass.addField(field));
            Assertions.assertTrue(umlClass.getFields().contains(field));
            Assertions.assertTrue(umlClass.deleteField(field));
            Assertions.assertFalse(umlClass.getFields().contains(field));
        });
    }

    @Test
    public void testUmlClassDeleteFieldReturnsFalseOnUnsuccessfulDelete() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Field field = new Field.Builder().withName("field").withType("type").build();

            Assertions.assertFalse(umlClass.getFields().contains(field));
            Assertions.assertFalse(umlClass.deleteField(field));
            Assertions.assertFalse(umlClass.getFields().contains(field));
        });
    }

    @Test
    public void testUmlClassUpdateFieldReturnsTrueOnSuccessfulUpdate() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Field field1 = new Field.Builder().withName("field1").withType("type").build();
            Field field2 = new Field.Builder().withName("field2").withType("type").build();

            Assertions.assertTrue(umlClass.addField(field1));
            Assertions.assertTrue(umlClass.getFields().contains(field1));
            Assertions.assertTrue(umlClass.updateField(field1, field2));
            Assertions.assertFalse(umlClass.getFields().contains(field1));
            Assertions.assertTrue(umlClass.getFields().contains(field2));
        });
    }

    @Test
    public void testUmlClassUpdateFieldReturnsFalseOnUnsuccessfulUpdate() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Field field1 = new Field.Builder().withName("field1").withType("type").build();
            Field field2 = new Field.Builder().withName("field2").withType("type").build();

            Assertions.assertTrue(umlClass.addField(field1));
            Assertions.assertTrue(umlClass.getFields().contains(field1));
            Assertions.assertTrue(umlClass.addField(field2));
            Assertions.assertTrue(umlClass.getFields().contains(field2));
            Assertions.assertFalse(umlClass.updateField(field1, field2));
            Assertions.assertTrue(umlClass.getFields().contains(field1));
            Assertions.assertTrue(umlClass.getFields().contains(field2));
        });
    }

    @Test
    public void testUmlClassAddMethodSucceedsOnValidMethod() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Method method = new Method.Builder()
                    .withName("method")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build();

            umlClass.addMethod(method);

            Assertions.assertTrue(umlClass.getMethods().contains(method));
        });
    }

    @Test
    public void testUmlClassAddMethodReturnsFalseOnDuplicateMethod() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Method method1 = new Method.Builder()
                    .withName("method")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build();
            Method method2 = new Method.Builder()
                    .withName("method")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build();

            Assertions.assertTrue(umlClass.addMethod(method1));
            Assertions.assertTrue(umlClass.getMethods().contains(method1));
            Assertions.assertFalse(umlClass.addMethod(method2));
        });
    }

    @Test
    public void testUmlClassDeleteMethodReturnsTrueOnSuccessfulDelete() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Method method = new Method.Builder()
                    .withName("method")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build();

            Assertions.assertTrue(umlClass.addMethod(method));
            Assertions.assertTrue(umlClass.getMethods().contains(method));
            Assertions.assertTrue(umlClass.deleteMethod(method));
            Assertions.assertFalse(umlClass.getMethods().contains(method));
        });
    }

    @Test
    public void testUmlClassDeleteMethodReturnsFalseOnUnsuccessfulDelete() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Method method = new Method.Builder()
                    .withName("method")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build();

            Assertions.assertFalse(umlClass.getMethods().contains(method));
            Assertions.assertFalse(umlClass.deleteMethod(method));
            Assertions.assertFalse(umlClass.getMethods().contains(method));
        });
    }

    @Test
    public void testUmlClassUpdateMethodReturnsTrueOnSuccessfulUpdate() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Method method1 = new Method.Builder()
                    .withName("method1")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build();
            Method method2 = new Method.Builder()
                    .withName("method2")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build();

            Assertions.assertTrue(umlClass.addMethod(method1));
            Assertions.assertTrue(umlClass.getMethods().contains(method1));
            Assertions.assertTrue(umlClass.updateMethod(method1, method2));
            Assertions.assertFalse(umlClass.getMethods().contains(method1));
            Assertions.assertTrue(umlClass.getMethods().contains(method2));
        });
    }

    @Test
    public void testUmlClassUpdateMethodReturnsFalseOnUnsuccessfulUpdate() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass umlClass = new UmlClass("Class");
            Method method1 = new Method.Builder()
                    .withName("method1")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build();
            Method method2 = new Method.Builder()
                    .withName("method2")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build();

            Assertions.assertTrue(umlClass.addMethod(method1));
            Assertions.assertTrue(umlClass.getMethods().contains(method1));
            Assertions.assertTrue(umlClass.addMethod(method2));
            Assertions.assertTrue(umlClass.getMethods().contains(method2));
            Assertions.assertFalse(umlClass.updateMethod(method1, method2));
            Assertions.assertTrue(umlClass.getMethods().contains(method1));
            Assertions.assertTrue(umlClass.getMethods().contains(method2));
        });
    }

    @Test
    public void testUmlClassAddRelationshipSucceedsOnValidRelationship() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass from = new UmlClass("From");
            UmlClass to1 = new UmlClass("To1");
            UmlClass to2 = new UmlClass("To2");

            Relationship relationship1 = new Relationship.Builder()
                    .withTo(to1)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();
            Relationship relationship2 = new Relationship.Builder()
                    .withTo(to2)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();

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

            Relationship relationship1 = new Relationship.Builder()
                    .withTo(to)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();
            Relationship relationship2 = new Relationship.Builder()
                    .withTo(to)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();

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

            Relationship toTo = new Relationship.Builder()
                    .withTo(to)
                    .withType(Relationship.Type.GENERALIZATION)
                    .build();
            Relationship toFrom = new Relationship.Builder()
                    .withTo(from)
                    .withType(Relationship.Type.GENERALIZATION)
                    .build();

            Assertions.assertTrue(from.addRelationship(toTo));
            Assertions.assertFalse(to.addRelationship(toFrom));
        });
    }

    @Test
    public void testUmlClassDeleteRelationshipReturnsTrueOnSuccessfulDelete() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass from = new UmlClass("From");
            UmlClass to = new UmlClass("To");

            Relationship relationship = new Relationship.Builder()
                    .withTo(to)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();

            Assertions.assertFalse(from.getRelationships().contains(relationship));
            Assertions.assertTrue(from.addRelationship(relationship));
            Assertions.assertTrue(from.getRelationships().contains(relationship));
            Assertions.assertTrue(from.deleteRelationship(relationship));
            Assertions.assertFalse(from.getRelationships().contains(relationship));
        });
    }

    @Test
    public void testUmlClassDeleteRelationshipReturnsFalseOnUnsuccessfulDelete() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass from = new UmlClass("From");
            UmlClass to = new UmlClass("To");

            Relationship relationship = new Relationship.Builder()
                    .withTo(to)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();

            Assertions.assertFalse(from.getRelationships().contains(relationship));
            Assertions.assertFalse(from.deleteRelationship(relationship));
            Assertions.assertFalse(from.getRelationships().contains(relationship));
        });
    }

    @Test
    public void testUmlClassUpdateRelationshipReturnsTrueOnSuccessfulUpdate() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass from = new UmlClass("From");
            UmlClass to1 = new UmlClass("To1");
            UmlClass to2 = new UmlClass("To2");

            Relationship relationship1 = new Relationship.Builder()
                    .withTo(to1)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();
            Relationship relationship2 = new Relationship.Builder()
                    .withTo(to2)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();

            Assertions.assertTrue(from.addRelationship(relationship1));
            Assertions.assertTrue(from.getRelationships().contains(relationship1));
            Assertions.assertTrue(from.updateRelationship(relationship1, relationship2));
            Assertions.assertFalse(from.getRelationships().contains(relationship1));
            Assertions.assertTrue(from.getRelationships().contains(relationship2));
        });
    }

    @Test
    public void testUmlClassUpdateRelationshipReturnsFalseOnUnsuccessfulUpdate() {
        Assertions.assertDoesNotThrow(() -> {
            UmlClass from = new UmlClass("From");
            UmlClass to1 = new UmlClass("To1");
            UmlClass to2 = new UmlClass("To2");

            Relationship relationship1 = new Relationship.Builder()
                    .withTo(to1)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();
            Relationship relationship2 = new Relationship.Builder()
                    .withTo(to2)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();

            Assertions.assertTrue(from.addRelationship(relationship1));
            Assertions.assertTrue(from.getRelationships().contains(relationship1));
            Assertions.assertTrue(from.addRelationship(relationship2));
            Assertions.assertTrue(from.getRelationships().contains(relationship2));
            Assertions.assertFalse(from.updateRelationship(relationship1, relationship2));
            Assertions.assertTrue(from.getRelationships().contains(relationship1));
            Assertions.assertTrue(from.getRelationships().contains(relationship2));
        });
    }
}
