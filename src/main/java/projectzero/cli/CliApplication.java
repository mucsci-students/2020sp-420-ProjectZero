package projectzero.cli;

import projectzero.Main;
import projectzero.core.*;
import projectzero.core.exceptions.InvalidNameException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
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

        while(true){
            inputLine = input.nextLine();

            if(inputLine.length() > 1) {
                determineCommand(inputLine);
            }
            else{
                System.out.println("Not a command");
            }
        }
    }

    private void determineCommand(String inputLine){
        if(inputLine.equals("displayAllClasses")){
            printList();
        }
        else if(inputLine.equals("help")){
            printHelp();
        }
        else {
            String command = inputLine.substring(0, inputLine.indexOf(" "));
            String arguments = inputLine.substring(inputLine.indexOf(" ") + 1);
            if (!Validation.isValidMenuInput(command)) {
                System.out.println("Not a valid command.");
            } else {
                switch(command){
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
                }
            }
        }
    }

    private void addRelationships(String arguments) {
        String from = arguments.substring(0, arguments.indexOf(" "));
        String to = arguments.substring(arguments.indexOf(" ") + 1);

       try{
           if(!MainManager.getUmlClass(from).addRelationship(new Relationship(MainManager.getUmlClass(to)))){
               System.out.println("Relationship can not be made.");
           }
           else{
               System.out.println("Relationship added From " + from + " to " + to + ".");
           }
       }catch (NullPointerException e){
           System.out.println("Class does not exist.");
       }

    }

    private void deleteField(String arguments) {
        String className = arguments.substring(0, arguments.indexOf(" "));
        String field = arguments.substring(arguments.indexOf(" ") + 1);
        UmlClass temp = MainManager.getUmlClass(className);

        try {
            for (Field m : temp.getFields()) {
                if (m.getName().equals(m)) {
                    temp.deleteField(m);
                    break;
                }
            }
        }catch (NullPointerException e){
            System.out.println("Field not found");
        }

        System.out.println("Field " + field + " has been deleted from " + className + ".");

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
        }catch (NullPointerException e){
            System.out.println("Method not found.");
        }
        System.out.println("Method " + method + " has been deleted from " + className + ".");

    }

    private void deleteClass(String arguments) {
        try{
            MainManager.deleteUmlClass(arguments);
        }catch (NullPointerException e){
            System.out.println("Class not found.");
        }
        System.out.println(arguments + " was deleted." );

    }

    private void addField(String s) {
        String className = s.substring(0, s.indexOf(" "));
        String field = s.substring(s.indexOf(" ") + 1);

            try{
                MainManager.getUmlClass(className).addField(new Field(field));
            }catch (Exception e){
                System.out.println();
            }
            System.out.println("The field " + field + " was added to " + className);
    }

    private void addMethod(String s) {
        String className = s.substring(0, s.indexOf(" "));
        String method = s.substring(s.indexOf(" ") + 1);


             try{
                 MainManager.getUmlClass(className).addMethod(new Method(method));
             }catch (Exception e){
                 System.out.println("Invalid class name");
             }

             System.out.println(method + " was added to " + className);
    }

    private void printHelp() {
        File help = new File("src/main/java/projectzero/cli/helpMenu");
        try{
            Scanner fileRead = new Scanner(help);
            while(fileRead.hasNext()){
                System.out.println(fileRead.nextLine());
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void addClass(String name){

        try{
            MainManager.addUmlClass(new UmlClass(name));
        }catch (InvalidNameException e){
                System.out.println("Not a valid class name.");
            }
        catch (NullPointerException e){
            System.out.println("Class already exists.");
        }

        System.out.println(name + " " +  "was added");
    }

    private void printList() {
        List<UmlClass> tempList = MainManager.listUmlClasses();
        System.out.println("\nCurrent classes:");
        for (UmlClass tempClass : tempList) {
            System.out.print("[ ");
            System.out.println(tempClass.getName() + ": ");
            System.out.print("\t");
            System.out.println("Fields: ");
            for(Field f: tempClass.getFields()){
                System.out.println("\t  " + f.getName());
            }
            System.out.print("\t");
            System.out.println("Methods: ");
            for(Method m: tempClass.getMethods()){
                System.out.println("\t  " + m.getName());
            }
            System.out.print("\t");
            System.out.println("Relationships Points to: ");
            for(Relationship r: tempClass.getRelationships()){
                System.out.println("\t  " + r.getTo().getName());
            }
            System.out.println("]\n");
        }
    }


    public void displayOneClass(String name){
        UmlClass temp = MainManager.getUmlClass(name);
        System.out.print("[ ");
        System.out.println(temp.getName() + ": ");
        System.out.print("\t");
        System.out.println("Fields: ");
            for(Field f: temp.getFields()){
                System.out.println("\t  " + f.getName());
            }
            System.out.print("\t");
            System.out.println("Methods: ");
            for(Method m : temp.getMethods()){
                System.out.println("\t  " + m.getName());
            }
        System.out.print("\t");
        System.out.println("Relationships Points to: ");
        for(Relationship r: temp.getRelationships()){
            System.out.println("\t  " + r.getTo().getName());
        }
            System.out.println("]\n");
        }
}
