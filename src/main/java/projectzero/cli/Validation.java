package projectzero.cli;

import java.util.ArrayList;
import java.util.Arrays;

public class Validation {
    public static ArrayList<String> validCommands = new ArrayList<String>(Arrays.asList(
            "addClass", "addMethod", "addField", "addRelationship", "deleteClass", "deleteMethod",
            "deleteField", "deleteRelationship", "editClass", "editMethod", "editField", "displayAllClasses",
            "displayClass", "help", "save", "load", "quit"
    ));

    public static boolean isValidMenuInput(String input) {
        return validCommands.contains(input);
    }
}
