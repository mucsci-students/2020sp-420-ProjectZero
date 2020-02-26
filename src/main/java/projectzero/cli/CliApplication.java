package projectzero.cli;

import projectzero.Main;
import projectzero.core.Field;
import projectzero.core.Method;
import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;

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
                }
            }
        }
    }

    private void addField(String s) {
        String className = s.substring(0, s.indexOf(" "));
        String field = s.substring(s.indexOf(" ") + 1);

        if(Validation.isValidClass(MainManager, className)){
            MainManager.getUmlClass(className).addField(new Field(field));
            System.out.println("The field " + field + " was added to " + className);
        }
    }

    private void addMethod(String s) {
        String className = s.substring(0, s.indexOf(" "));
        String method = s.substring(s.indexOf(" ") + 1);


         if(Validation.isValidClass(MainManager, className)){
             MainManager.getUmlClass(className).addMethod(new Method(method));
             System.out.println(method + " was added to " + className);
         }
         else{
             System.out.println("Invalid class name");
         }

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
        if(!Validation.isValidName(name)){
            System.out.println("Not a valid class name.");
        }
        else if(!MainManager.addUmlClass(new UmlClass(name))){
            System.out.println("Class already exists");
        }
        else{
            System.out.println(name + " " +  "was added");
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
            for(String field: tempClass.getFieldMap().keySet()){
                System.out.println("\t  " + field);
            }
            System.out.print("\t");
            System.out.println("Methods: ");
            for(String method: tempClass.getMethodMap().keySet()){
                System.out.println("\t  " + method);
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
            for(String field: temp.getFieldMap().keySet()){
                System.out.println("\t  " + field);
            }
            System.out.print("\t");
            System.out.println("Methods: ");
            for(String method: temp.getMethodMap().keySet()){
                System.out.println("\t  " + method);
            }
            System.out.println("]\n");
        }
}
