package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UmlClassTest {
    @Test
    public void testUmlClassAddFieldSucceeds() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addField(new Field("field")));
    }

    @Test
    public void testUmlClassAddFieldFailsOnDuplicateField() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addField(new Field("field")));
        Assertions.assertFalse(umlClass.addField(new Field("field")));
    }

    @Test
    public void testUmlClassAddMethodSucceeds() {
        UmlClass umlClass = new UmlClass("class");
        Assertions.assertTrue(umlClass.addMethod(new Method("method")));
    }

    @Test
    public void testUmlClassAddFieldFailsOnDuplicateMethod() {
        UmlClass umlClass = new UmlClass("class");

        Assertions.assertTrue(umlClass.addMethod(new Method("method")));
        Assertions.assertFalse(umlClass.addMethod(new Method("method")));
    }

    @Test
    public void testUmlClassAddRelationshipSucceeds() {
        UmlClass to = new UmlClass("to");
        UmlClass from = new UmlClass("from");

        Relationship relationship = new Relationship(to);

        Assertions.assertTrue(from.addRelationship(relationship));
    }

    @Test
    public void testUmlClassAddRelationshipFailsOnDuplicateRelationship() {
        UmlClass to = new UmlClass("to");
        UmlClass from = new UmlClass("from");

        Relationship relationship = new Relationship(to);

        Assertions.assertTrue(from.addRelationship(relationship));
        Assertions.assertFalse(from.addRelationship(relationship));
    }

    @Test
    public void testUmlClassAddRelationshipFailsOnInverse() {
        UmlClass to = new UmlClass("to");
        UmlClass from = new UmlClass("from");

        Relationship toToFromRelationship = new Relationship(from);
        Relationship fromToToRelationship = new Relationship(to);

        Assertions.assertTrue(from.addRelationship(fromToToRelationship));
        Assertions.assertFalse(to.addRelationship(toToFromRelationship));
    }
}
