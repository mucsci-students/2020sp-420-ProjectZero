package projectzero.cli;

import projectzero.Main;
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
            determineCommand(inputLine);
        }
    }

    private void determineCommand(String inputLine){
        if(inputLine.equals("displayAllClasses")){
            printList();
        }else if(inputLine.equals("help")){
            printHelp();
        }else {
            String command = inputLine.substring(0, inputLine.indexOf(" "));
            String arguments = inputLine.substring(inputLine.indexOf(" "), inputLine.length());
            if (!Validation.isValidMenuInput(command)) {
                System.out.println("Not a valid command.");
            } else {
                switch(command){
                    case "addClass":
                        addClass(arguments);
                }
            }
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
        System.out.print("[ ");
        for (UmlClass tempClass : tempList) {
            System.out.print(tempClass.getName() + " ");
        }
        System.out.println("]\n");
    }
}
