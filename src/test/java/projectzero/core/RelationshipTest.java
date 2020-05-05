package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RelationshipTest {

    @Test
    public void testRelationshipConstructorDoesNotThrowWithValidName() {
        Assertions.assertDoesNotThrow(() -> {
            new Relationship.Builder()
                    .withTo("class")
                    .withType(Relationship.Type.AGGREGATION)
                    .build();
        });
    }

    @Test
    public void testRelationshipConstructorThrowsInvalidName(){
        Assertions.assertThrows(NullPointerException.class,() -> {
            new Relationship.Builder()
                    .withTo(null)
                    .withType(Relationship.Type.AGGREGATION)
                    .build();
        });
    }

    @Test
    public void testToString(){
        Assertions.assertTrue(new Relationship.Builder().withTo("anotherClass")
                .withType(Relationship.Type.AGGREGATION).build().toString().equals("to: anotherClass"));
        Assertions.assertFalse(new Relationship.Builder().withTo("anotherClass2")
                .withType(Relationship.Type.AGGREGATION).build().toString().equals("Not toString"));
    }
}

