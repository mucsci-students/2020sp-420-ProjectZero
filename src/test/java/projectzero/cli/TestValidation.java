package projectzero.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestValidation {
    @Test
    void testAValidMenuInput(){
        Assertions.assertTrue(Validation.isValidMenuInput("addClass"));
        Assertions.assertTrue(Validation.isValidMenuInput("addMethod"));
        Assertions.assertTrue(Validation.isValidMenuInput("save"));
    }

    @Test
    void testANonValidMenuInput(){
        Assertions.assertFalse(Validation.isValidMenuInput("saveFile"));
        Assertions.assertFalse(Validation.isValidMenuInput("loadFile"));
        Assertions.assertFalse(Validation.isValidMenuInput("edit"));
    }
}
