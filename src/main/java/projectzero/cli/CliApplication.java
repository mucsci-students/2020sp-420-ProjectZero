package projectzero.cli;

import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;

import java.util.List;
import java.util.Scanner;

public class CliApplication {
    private UmlClassManager MainManager;
    private Scanner scan = new Scanner(System.in);

    public CliApplication() {
        MainManager = new UmlClassManager();
    }

    public void run() {
        initMenuPrompt();
        MainMenu();
    }

    private void initMenuPrompt() {
        String input = "";
        boolean validInput = true;
        boolean inInitMenu = true;
        int[] validInputs = {1, 2};
        do {
            System.out.println("Create or load a new file?");
            System.out.println("1: create a new project\n2: load a previous project");
            input = scan.next();
            System.out.println();
            validInput = Validation.isValidMenuInput(validInputs, input);
            if (validInput) {
                if (Integer.parseInt(input) == 1) {
                    inInitMenu = false;
                }
                //does not collect file path name, skips to main menu
                if (Integer.parseInt(input) == 2) {
                    System.out.println("Enter the file path followed by the desired name: ");
                    if (MainManager.load(scan.next())) {
                        System.out.println("\nFile retrieved successfully\n");
                        inInitMenu = false;
                    } else {
                        System.out.println("\nFailed to load file\n");
                        inInitMenu = true;
                    }

                }
            }
        } while (inInitMenu);

    }

    private void MainMenu() {
        String input;
        Scanner getName = new Scanner(System.in);
        boolean inMainMenu = true;
        boolean validInput = true;
        int[] validInputs = {1, 2, 3, 4, 5};
        do {
            System.out.println(" 1. Display Class List \n 2. Add Class \n 3. Delete Class \n 4. Save Work \n 5. Quit");
            System.out.println("Enter number for option:");
            input = scan.next();
            validInput = Validation.isValidMenuInput(validInputs, input);
            if (validInput) {
                String className;
                switch (Integer.parseInt(input)) {
                    case 1:
                        printList();
                        break;
                    case 2:
                        System.out.println("Enter the class name: ");
                        className = getName.nextLine();
                        Scanner nameInput = new Scanner(className);
                        if (!Validation.isValidName(nameInput)) {
                            System.out.println("Invalid Class: spaces can't be used\n");
                        } else {
                            MainManager.addUmlClass(new UmlClass(className));
                            System.out.println(className + " was added\n");
                        }
                        break;
                    case 3:
                        System.out.println("Enter the class name: ");
                        className = scan.next();
                        if (MainManager.deleteUmlClass(className)) {
                            System.out.println(className + " was deleted\n");
                        } else {
                            System.out.println("Class not found\n");
                        }
                        break;
                    case 4:
                        System.out.println("Enter file path to save to followed by desired file name: ");
                        String filePath = scan.next();
                        if (MainManager.save(filePath))
                            System.out.println("File was saved to" + filePath + "\n");
                        else
                            System.out.println("File was not saved to" + filePath + "\n");
                        break;
                    case 5:
                        inMainMenu = false;
                    default:
                }
            }
        } while (inMainMenu);
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
