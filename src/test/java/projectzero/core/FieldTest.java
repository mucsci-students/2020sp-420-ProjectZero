package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projectzero.core.exceptions.InvalidNameException;

public class FieldTest {

    @Test
    public void testFieldConstructorDoesNotThrowWithValidName() {
        Assertions.assertDoesNotThrow(() -> {
            new Field.Builder()
                    .withName("field")
                    .withType("type")
                    .build();
        });
    }

    @Test
    public void testFieldConstructorThrowsWithInvalidName() {
        Assertions.assertThrows(InvalidNameException.class, () -> {
            new Field.Builder()
                    .withName("&field")
                    .withType("type")
                    .build();
        });
    }

    @Test
    public void testFieldConstructorThrowsWithNullName() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Field.Builder()
                    .withType("type")
                    .build();
        });
    }

    @Test
    public void testFieldConstructorThrowsWithNullType() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Field.Builder()
                    .withName("field")
                    .build();
        });
    }
}

