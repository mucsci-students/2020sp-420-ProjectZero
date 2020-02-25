package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UmlClassTest {

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
