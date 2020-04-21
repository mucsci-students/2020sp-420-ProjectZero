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
}

