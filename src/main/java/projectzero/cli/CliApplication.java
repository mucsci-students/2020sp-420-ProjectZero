package projectzero.cli;

import projectzero.core.*;
import projectzero.core.exceptions.InvalidNameException;

import java.io.InvalidClassException;
import java.util.*;

public class CliApplication {
    private UmlClassManager MainManager;
    private String inputLine;
    private HashMap<String, String> helpMap;

    public CliApplication() {
        MainManager = new UmlClassManager();
    }

    public void run() {
        MainMenu();
    }

    private void MainMenu() {
        initHelpMap();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter command or help:");

        while (true) {
            inputLine = input.nextLine();

            if (inputLine.length() > 1) {
                determineCommand(inputLine);
            } else {
                System.out.println("Not a command");
            }
        }
    }

    private void determineCommand(String inputLine) {
        try {
            if (inputLine.split(" ")[0].equals("list")) {
                printList(inputLine);
            } else if (inputLine.equals("help")) {
                printHelp();
            } else if (inputLine.equals("quit")) {
                System.exit(0);
            } else {
                String command = inputLine.substring(0, inputLine.indexOf(" "));
                String arguments = inputLine.substring(inputLine.indexOf(" ") + 1);
                if (!Validation.isValidMenuInput(command)) {
                    System.out.println("Not a valid command.");
                } else {
                    if(arguments.contains("help")) {
                        System.out.println(helpMap.get(command));
                        return;
                    }
                    switch (command) {
                        case "addClass":
                            addClass(arguments);
                            break;
                        case "addMethod":
                            addMethod(arguments);
                            break;
                        case "addField":
                            addField(arguments);
                            break;
                        case "deleteClass":
                            deleteClass(arguments);
                            break;
                        case "deleteMethod":
                            deleteMethod(arguments);
                            break;
                        case "deleteField":
                            deleteField(arguments);
                            break;
                        case "addRelationship":
                            addRelationships(arguments);
                            break;
                        case "deleteRelationship":
                            deleteRelationship(arguments);
                            break;
                        case "save":
                            try {
                                MainManager.save(arguments);
                                System.out.println("File saved.");
                            } catch (Exception e) {
                                System.out.println("Did not save correctly.");
                            }
                            break;
                        case "load":
                            try {
                                MainManager.load(arguments);
                                System.out.println("File Loaded.");
                            } catch (Exception e) {
                                System.out.println("Did not load correctly.");
                            }
                            break;

                        case "editClass":
                            editClass(arguments);
                            break;
                        case "editField":
                            editField(arguments);
                            break;
                        case "editMethod":
                            editMethod(arguments);
                            break;
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input\n");
        }
    }

    private void editMethod(String s) {
        String[] arguments = s.split(" ");

        if (arguments.length != 4) {
            System.out.println("Invalid Number of Arguments");
        }

        try {
            UmlClass umlClass = MainManager.getUmlClass(arguments[0]);
            umlClass.updateMethod(umlClass.getMethod(arguments[1]), new Method.Builder().withName(arguments[2]).withType(arguments[3]).withParameterTypes(new ArrayList<>()).build());
            System.out.println("Method " + arguments[1] + " has been changed to " + arguments[2] + ".");
        } catch (InvalidNameException e) {
            System.out.println("Invalid Class name.");
        } catch (NullPointerException e) {
            System.out.println("Method not found.");
        }
    }

    private void editField(String s) {
        String[] arguments = s.split(" ");

        if (arguments.length != 4) {
            System.out.println("Invalid Number of Arguments");
        }

        try {
            UmlClass umlClass = MainManager.getUmlClass(arguments[0]);
            umlClass.updateField(umlClass.getField(arguments[1]), new Field.Builder().withName(arguments[2]).withType(arguments[3]).build());
            System.out.println("Field " + arguments[1] + " has been changed to " + arguments[2] + ".");
        } catch (InvalidNameException e) {
            System.out.println("Invalid Class name.");
        } catch (NullPointerException e) {
            System.out.println("Field not found.");
        }
    }

    private void editClass(String arguments) {
        String oldClassName = arguments.substring(0, arguments.indexOf(" "));
        String newClassName = arguments.substring(arguments.indexOf(" ") + 1);
        try {
            UmlClass oldUMLClass = MainManager.getUmlClass(oldClassName);
            if(oldUMLClass == null) {
                throw new InvalidNameException();
            }
            UmlClass newUMLClass = new UmlClass(newClassName);
            oldUMLClass.getFields().forEach(newUMLClass::addField);
            oldUMLClass.getMethods().forEach(newUMLClass::addMethod);
            oldUMLClass.getRelationships().forEach(newUMLClass::addRelationship);
            MainManager.updateUmlClass(oldClassName, newUMLClass);
            System.out.println("Class " + oldClassName + " has been changed to " + newClassName + ".");
        } catch (InvalidNameException e) {
            System.out.println("Invalid Class name.");
        }
    }

    private void deleteRelationship(String arguments) {
        String from = arguments.substring(0, arguments.indexOf(" "));
        String to = arguments.substring(arguments.indexOf(" ") + 1);
        UmlClass temp = MainManager.getUmlClass(from);

        try {
            for (Relationship r : temp.getRelationships()) {
                if (r.getTo().getName().equals(to)) {
                    temp.deleteRelationship(r);
                    break;
                }
            }
            System.out.println("Relationship from " + from + " to " + to + " has been deleted.");
        } catch (NullPointerException e) {
            System.out.println("Relationship not found");
        }
    }

    private void addRelationships(String s) {
        String[] arguments = s.split(" ");

        if (arguments.length != 3) {
            System.out.println("Invalid Number of Arguments");
        }

        if (arguments[0].equals(arguments[1])) {
            System.out.println("Relationship can't be made");
        } else {
            try {
                if (!MainManager.getUmlClass(arguments[0]).addRelationship(new Relationship.Builder()
                        .withTo(MainManager.getUmlClass(arguments[1]))
                        .withType(Relationship.Type.valueOf(arguments[2].toUpperCase()))
                        .build())
                ) {
                    System.out.println("Relationship can not be made.");
                } else {
                    System.out.println("Relationship added From " + arguments[0] + " to " + arguments[1] + ".");
                }
            } catch (NullPointerException e) {
                System.out.println("Class does not exist.");
            }
        }

    }

    private void deleteField(String arguments) {
        String className = arguments.substring(0, arguments.indexOf(" "));
        String field = arguments.substring(arguments.indexOf(" ") + 1);
        UmlClass temp = MainManager.getUmlClass(className);

        try {
            for (Field m : temp.getFields()) {
                if (m.getName().equals(field)) {
                    temp.deleteField(m);
                    break;
                }
            }
            System.out.println("Field " + field + " has been deleted from " + className + ".");
        } catch (NullPointerException e) {
            System.out.println("Field not found");
        }
    }

    private void deleteMethod(String arguments) {
        String className = arguments.substring(0, arguments.indexOf(" "));
        String method = arguments.substring(arguments.indexOf(" ") + 1);
        UmlClass temp = MainManager.getUmlClass(className);
        try {
            for (Method m : temp.getMethods()) {
                if (m.getName().equals(method)) {
                    temp.deleteMethod(m);
                    break;
                }
            }
            System.out.println("Method " + method + " has been deleted from " + className + ".");
        } catch (NullPointerException e) {
            System.out.println("Method not found.");
        }


    }

    private void deleteClass(String arguments) {
        try {
            MainManager.deleteUmlClass(arguments);
            System.out.println(arguments + " was deleted.");
        } catch (NullPointerException e) {
            System.out.println("Class not found.");
        }


    }

    private void addField(String s) {
        String[] arguments = s.split(" ");

        if (arguments.length != 3) {
            System.out.println("Invalid number of arguments");
            return;
        }

        try {
            MainManager.getUmlClass(arguments[0]).addField(new Field.Builder()
                    .withName(arguments[1])
                    .withType(arguments[2])
                    .build()
            );
            System.out.println("The field " + arguments[1] + " was added to " + arguments[0]);
        } catch (InvalidNameException e) {
            System.out.println("Invalid field input");
        } catch (NullPointerException e) {
            System.out.println("Class not found");
        }

    }

    private void addMethod(String s) {
        String[] arguments = s.split(" ");

        if (arguments.length < 3) {
            System.out.println("Invalid number of arguments");
            return;
        }
        List<String> paramTypesList;
        if(arguments.length > 3){
            String[] paramTypes = Arrays.copyOfRange(arguments,3,arguments.length);
            paramTypesList = Arrays.asList(paramTypes);
        }
        else{
            paramTypesList = new ArrayList<>();
        }
        try {
            MainManager.getUmlClass(arguments[0]).addMethod(new Method.Builder()
                    .withName(arguments[1])
                    .withType(arguments[2])
                    .withParameterTypes(paramTypesList)
                    .build()
            );
            System.out.println("The method " + arguments[1] + " was added to " + arguments[0]);
        } catch (InvalidNameException e) {
            System.out.println("Invalid method input");
        } catch (NullPointerException e) {
            System.out.println("Class not found");
        }


    }

    private void printHelp() {
        System.out.println("addClass <class name>\n" +
                "addMethod <class name> <method name> <method type> [<method parameter types>]\n" +
                "addField <class name> <field name> <field type>\n" +
                "addRelationship <from class name> <to class name> {AGGREGATION | COMPOSITION | GENERALIZATION}\n");
        System.out.println("deleteClass <class name>\n" +
                "deleteMethod <class name> <method name>\n" +
                "deleteField <class name> <field name>\n" +
                "deleteRelationship <from class name> <to class name>\n");
        System.out.println("editClass <old class name> <new class name>\n" +
                "editMethod <class name> <old method name> <new method name> <new method type> [<new method parameter types>]\n" +
                "editField <class name> <old field name> <new field name> <new field type>\n");
        System.out.println("list [<class names>]\n");
        System.out.println("save <file name>\n" +
                "load <file name>\n");
    }

    private void initHelpMap(){
        helpMap = new HashMap<>();
        helpMap.put("addClass","addClass <class name>\n");
        helpMap.put("addMethod","addMethod <class name> <method name> <method type> [<method parameter types>]\n");
        helpMap.put("addField", "addField <class name> <field name> <field type>\n");
        helpMap.put("addRelationship", "addRelationship <from class name> <to class name> {AGGREGATION | COMPOSITION | GENERALIZATION}\n");
        helpMap.put("deleteClass","deleteClass <class name>\n");
        helpMap.put("deleteMethod","deleteMethod <class name> <method name>\n");
        helpMap.put("deleteField","deleteField <class name> <field name>\n");
        helpMap.put("deleteRelationship","deleteRelationship <from class name> <to class name>\n");
        helpMap.put("editClass","editClass <old class name> <new class name>\n");
        helpMap.put("editMethod",  "editMethod <class name> <old method name> <new method name> <new method type> [<new method parameter types>]\n");
        helpMap.put("editField", "editField <class name> <old field name> <new field name> <new field type>\n");
        helpMap.put("list","list [<class names>]\n");
        helpMap.put("save","save <file name>\n");
        helpMap.put("load","load <file name>\n");
    }

    private void addClass(String name) {

        try {
            MainManager.addUmlClass(new UmlClass(name));
            System.out.println(name + " " + "was added");
        } catch (InvalidNameException e) {
            System.out.println("Not a valid class name.");
        } catch (NullPointerException e) {
            System.out.println("Class already exists.");
        }


    }

    private void printList(String inputLine) {
        String[] splitInput = inputLine.split(" ");
        if(splitInput.length > 1){
            for(String x : Arrays.copyOfRange(splitInput, 1 , splitInput.length)){
                UmlClass tempClass = MainManager.getUmlClass(x);
                printUmlClass(tempClass);
            }
        }
        else {
            List<UmlClass> tempList = MainManager.listUmlClasses();
            for(UmlClass umlClass : tempList){
                printUmlClass(umlClass);
            }
        }

    }
    private void printUmlClass(UmlClass umlClass){
        if(umlClass == null){
            System.out.println("Class doesnt exist");
            return;
        }
        System.out.print("[ ");
        System.out.println(umlClass.getName() + ": ");
        System.out.print("\t");
        System.out.println("Fields: ");
        for (Field f : umlClass.getFields()) {
            System.out.println("\t  " + f);
        }
        System.out.print("\t");
        System.out.println("Methods: ");
        for (Method m : umlClass.getMethods()) {
            System.out.println("\t  " + m);
        }
        System.out.print("\t");
        System.out.println("Relationships: ");
        for (Relationship r : umlClass.getRelationships()) {
            System.out.println("\t  " + r);
        }
        System.out.println("]\n");
    }
}
