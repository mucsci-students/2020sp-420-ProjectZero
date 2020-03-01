package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projectzero.core.exceptions.InvalidNameException;

public class FieldTest {

    @Test
    public void testFieldConstructorDoesNotThrowWithValidName() {
        Assertions.assertDoesNotThrow(() -> {
            Field field = new Field("field");
        });
    }

    @Test
    public void testFieldConstructorThrowsWithInvalidName() {
        Assertions.assertThrows(InvalidNameException.class, () -> {
            Field field = new Field("&field");
        });
    }
}
