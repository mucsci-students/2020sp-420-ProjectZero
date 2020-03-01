package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RelationshipTest {

    @Test
    public void testRelationshipConstructorDoesNotThrowWithValidName() {
        Assertions.assertDoesNotThrow(() -> {
           Relationship relationship = new Relationship(new UmlClass("Class"));
        });
    }
}

