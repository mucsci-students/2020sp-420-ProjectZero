package projectzero.cli;

import java.util.Scanner;

public class Validation {
    public static boolean isValidMenuInput(int[] validInputList, String input) {
        boolean validOption = false;
        try {
            int option = Integer.parseInt(input);
            validOption = isValidInt(option, validInputList);
        } catch (Exception e) {
            System.out.println("Not valid\n");
            return false;
        }
        return validOption;
    }

    private static boolean isValidInt(int input, int[] validInputList) {
        boolean validOption = false;
        for (int i : validInputList) {
            if (i == input)
                validOption = true;
        }
        return validOption;
    }

    public static boolean isValidName(Scanner nameScanner) {
        String className = nameScanner.nextLine();

        return !className.contains(" ");
    }
}
