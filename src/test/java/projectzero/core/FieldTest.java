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

    @Test
    public void testToString() throws InvalidNameException {
        Field.Builder builder = new Field.Builder();
        Assertions.assertTrue(builder.withName("Field1").withType("int").build().toString().equals("Field1: int"));
        Assertions.assertFalse(builder.withName("Field2").withType("String").build().equals("Not a to string"));
    }
}

