package projectzero.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projectzero.core.exceptions.InvalidNameException;

import java.util.ArrayList;

public class MethodTest {

    @Test
    public void testMethodConstructorDoesNotThrowWithValidName() {
        Assertions.assertDoesNotThrow(() -> {
            new Method.Builder()
                    .withName("method")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build();
        });
    }

    @Test
    public void testMethodConstructorThrowsWithInvalidName() {
        Assertions.assertThrows(InvalidNameException.class, () -> new Method.Builder()
                .withName("&method")
                .withType("type")
                .withParameterTypes(new ArrayList<>())
                .build()
        );
    }

    @Test
    public void testMethodConstructorThrowsWithNullName() {
        Assertions.assertThrows(NullPointerException.class, () -> new Method.Builder()
                .withType("type")
                .withParameterTypes(new ArrayList<>())
                .build())
        ;
    }

    @Test
    public void testMethodConstructorThrowsWithNullType() {
        Assertions.assertThrows(NullPointerException.class, () -> new Method.Builder()
                .withName("method")
                .withParameterTypes(new ArrayList<>())
                .build()
        );
    }

    @Test
    public void testMethodConstructorThrowsWithNullParameterTypes() {
        Assertions.assertThrows(NullPointerException.class, () -> new Method.Builder()
                .withName("method")
                .withType("type")
                .build()
        );
    }
}
