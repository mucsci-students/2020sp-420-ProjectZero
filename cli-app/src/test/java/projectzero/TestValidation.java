package projectzero;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Scanner;

public class TestValidation {
    @Test
    public void inputStringToValidMenuInput(){
        int[] validInputs = {1,2,3};
        String input = "This should not work";
        Assertions.assertFalse(Validation.isValidMenuInput(validInputs,input));
    }

    @Test
    public void inputNotAValidOptionMenuInput(){
        int[] validInputs = {1,2,3};
        String input = "4";
        Assertions.assertFalse(Validation.isValidMenuInput(validInputs,input));
    }
    @Test
    public void inputIsValidMenuInput(){
        int[] validInputs = {1,2,3};
        String input = "1";
        Assertions.assertTrue(Validation.isValidMenuInput(validInputs,input));
    }
    @Test
    public void inputSpacedIntValidMenuInput(){
        int[] validInputs = {1,2,3};
        String input = "1 2";
        Assertions.assertFalse(Validation.isValidMenuInput(validInputs,input));
    }
    @Test
    public void inputSpacedIntsInvalidMenuInput(){
        int[] validInputs = {1,2,3};
        String input = "0 4";
        Assertions.assertFalse(Validation.isValidMenuInput(validInputs,input));
    }
    @Test
    public void withoutSpacesValidName(){
        Assertions.assertTrue(Validation.isValidName(new Scanner("PassableClass")));
    }
    @Test
    public void spacesValidName(){
        Assertions.assertFalse(Validation.isValidName(new Scanner("Unacceptable Class")));
    }
}
