package projectzero.cli;

import javax.lang.model.SourceVersion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Validation {
    public static ArrayList<String> validCommands = new ArrayList<String>(Arrays.asList(
         "addClass", "addMethod", "addField", "addRelationship", "deleteClass" ,"deleteMethod",
         "deleteField", "deleteRelationship", "editClass", "editMethod", "editField", "displayAll",
         "displayClass", "help", "save", "load"
    ));

    public static boolean isValidMenuInput(String input) {
        if(validCommands.contains(input)){
            return true;
        }
        return false;
    }



    public static boolean isValidName(String name) {
        if (!SourceVersion.isIdentifier(name) && !SourceVersion.isKeyword(name)) {
                return true;
        }
        return false;

    }
}
