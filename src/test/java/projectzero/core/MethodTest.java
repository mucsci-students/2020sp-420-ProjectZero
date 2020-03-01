package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projectzero.core.exceptions.InvalidNameException;

public class MethodTest {

    @Test
    public void testMethodConstructorDoesNotThrowWithValidName() {
        Assertions.assertDoesNotThrow(() -> {
            Method method = new Method("method");
        });
    }

    @Test
    public void testMethodConstructorThrowsWithInvalidName() {
        Assertions.assertThrows(InvalidNameException.class, () -> {
            Method method = new Method("&method");
        });
    }
}
