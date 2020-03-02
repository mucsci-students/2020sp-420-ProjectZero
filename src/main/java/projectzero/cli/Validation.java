package projectzero.cli;

import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;

import javax.lang.model.SourceVersion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validation {
    public static ArrayList<String> validCommands = new ArrayList<String>(Arrays.asList(
         "addClass", "addMethod", "addField", "addRelationship", "deleteClass" ,"deleteMethod",
         "deleteField", "deleteRelationship", "editClass", "editMethod", "editField", "displayAllClasses",
         "displayClass", "help", "save", "load", "quit"
    ));

    public static boolean isValidMenuInput(String input) {
        if(validCommands.contains(input)){
            return true;
        }
        return false;
    }
}
