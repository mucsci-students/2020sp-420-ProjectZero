package projectzero.cli;

import projectzero.core.*;
import projectzero.core.exceptions.InvalidNameException;

import java.util.List;
import java.util.Scanner;

public class CliApplication {
    private UmlClassManager MainManager;
    private String inputLine;

    public CliApplication() {
        MainManager = new UmlClassManager();
    }

    public void run() {
        MainMenu();
    }

    private void MainMenu() {
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
            if (inputLine.equals("displayAllClasses")) {
                printList();
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
                        case "displayClass":
                            displayOneClass(arguments);
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

    private void editMethod(String arguments) {
        String className = arguments.substring(0, arguments.indexOf(" "));
        arguments = arguments.substring(className.length() + 1);
        String oMethodName = arguments.substring(0, arguments.indexOf(" "));
        String nMethodName = arguments.substring(arguments.indexOf(" ") + 1);
        try {
            MainManager.getUmlClass(className).updateMethod(MainManager.getUmlClass(className).getMethod(oMethodName), new Method(nMethodName));
            System.out.println("Method " + oMethodName + " has been changed to " + nMethodName + ".");
        } catch (InvalidNameException e) {
            System.out.println("Invalid Class name.");
        } catch (NullPointerException e) {
            System.out.println("Method not found.");
        }
    }

    private void editField(String arguments) {
        String className = arguments.substring(0, arguments.indexOf(" "));
        arguments = arguments.substring(className.length() + 1);
        String oFieldName = arguments.substring(0, arguments.indexOf(" "));
        String nFieldName = arguments.substring(arguments.indexOf(" ") + 1);
        try {
            MainManager.getUmlClass(className).updateField(MainManager.getUmlClass(className).getField(oFieldName), new Field(nFieldName));
            System.out.println("Field " + oFieldName + " has been changed to " + nFieldName + ".");
        } catch (InvalidNameException e) {
            System.out.println("Invalid Class name.");
        } catch (NullPointerException e) {
            System.out.println("Field not found.");
        }
    }

    private void editClass(String arguments) {
        String oClassName = arguments.substring(0, arguments.indexOf(" "));
        String nClassName = arguments.substring(arguments.indexOf(" ") + 1);
        try {
            MainManager.updateUmlClass(oClassName, new UmlClass(nClassName));
            System.out.println("Class " + oClassName + " has been changed to " + nClassName + ".");
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

    private void addRelationships(String arguments) {
        String from = arguments.substring(0, arguments.indexOf(" "));
        String to = arguments.substring(arguments.indexOf(" ") + 1);
        if (from.equals(to)) {
            System.out.println("Relationship can't be made");
        } else {
            try {
                if (!MainManager.getUmlClass(from).addRelationship(new Relationship(MainManager.getUmlClass(to)))) {
                    System.out.println("Relationship can not be made.");
                } else {
                    System.out.println("Relationship added From " + from + " to " + to + ".");
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
        String className = s.substring(0, s.indexOf(" "));
        String field = s.substring(s.indexOf(" ") + 1);

        try {
            MainManager.getUmlClass(className).addField(new Field(field));
            System.out.println("The field " + field + " was added to " + className);
        } catch (InvalidNameException e) {
            System.out.println("Invalid field input");
        } catch (NullPointerException e) {
            System.out.println("Class not found");
        }

    }

    private void addMethod(String s) {
        String className = s.substring(0, s.indexOf(" "));
        String method = s.substring(s.indexOf(" ") + 1);


        try {
            MainManager.getUmlClass(className).addMethod(new Method(method));
            System.out.println("Method " + method + " was added to " + className);
        } catch (InvalidNameException e) {
            System.out.println("Invalid method input");
        } catch (NullPointerException e) {
            System.out.println("Class not found");
        }


    }

    private void printHelp() {
        System.out.println("addClass <class name>\n" +
                "addMethod <class Name> <method name>\n" +
                "addField <class Name> <field name>\n" +
                "addRelationship <From class Name> <to class name>\n");
        System.out.println("deleteClass <class name>\n" +
                "deleteMethod <class name> <method name>\n" +
                "deleteField <class name> <field name>\n" +
                "deleteRelationship <from class name> <to class name>\n");
        System.out.println("editClass <old class name> <new class name>\n" +
                "editMethod <class name> <old method name> <new method name>\n" +
                "editField <class name> <old field name> <new field name>\n");
        System.out.println("displayAllClasses\n" +
                "displayClass <class name>\n");
        System.out.println("save <file name>\n" +
                "load <file name>\n");
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

    private void printList() {
        List<UmlClass> tempList = MainManager.listUmlClasses();
        System.out.println("\nCurrent classes:");
        for (UmlClass tempClass : tempList) {
            System.out.print("[ ");
            System.out.println(tempClass.getName() + ": ");
            System.out.print("\t");
            System.out.println("Fields: ");
            for (Field f : tempClass.getFields()) {
                System.out.println("\t  " + f.getName());
            }
            System.out.print("\t");
            System.out.println("Methods: ");
            for (Method m : tempClass.getMethods()) {
                System.out.println("\t  " + m.getName());
            }
            System.out.print("\t");
            System.out.println("Relationship Points to: ");
            for (Relationship r : tempClass.getRelationships()) {
                System.out.println("\t  " + r.getTo().getName());
            }
            System.out.println("]\n");
        }
    }


    public void displayOneClass(String name) {
        UmlClass temp = MainManager.getUmlClass(name);
        System.out.print("[ ");
        System.out.println(temp.getName() + ": ");
        System.out.print("\t");
        System.out.println("Fields: ");
        for (Field f : temp.getFields()) {
            System.out.println("\t  " + f.getName());
        }
        System.out.print("\t");
        System.out.println("Methods: ");
        for (Method m : temp.getMethods()) {
            System.out.println("\t  " + m.getName());
        }
        System.out.print("\t");
        System.out.println("Relationship Points to: ");
        for (Relationship r : temp.getRelationships()) {
            System.out.println("\t  " + r.getTo().getName());
        }
        System.out.println("]\n");
    }
}
