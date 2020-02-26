package projectzero.cli;

import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;

import javax.lang.model.SourceVersion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Validation {
    public static ArrayList<String> validCommands = new ArrayList<String>(Arrays.asList(
         "addClass", "addMethod", "addField", "addRelationship", "deleteClass" ,"deleteMethod",
         "deleteField", "deleteRelationship", "editClass", "editMethod", "editField", "displayAllClasses",
         "displayClass", "help", "save", "load"
    ));

    public static boolean isValidMenuInput(String input) {
        if(validCommands.contains(input)){
            return true;
        }
        return false;
    }


    public static boolean isValidClass(UmlClassManager manager, String name){

       List<UmlClass> classes = manager.listUmlClasses();

        for(UmlClass temp : classes){
            if(temp.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
    public static boolean isValidName(String name) {
        if (SourceVersion.isIdentifier(name) && !SourceVersion.isKeyword(name)) {
                return true;
        }
        return false;

    }
}
