package projectzero;

import java.util.Scanner;

public class Application {
    private UMLClassManager MainManager;
    private Scanner scan = new Scanner(System.in);
    public Application() {
        MainManager = new UMLClassManager();


    }

    public void run() {
        initMenuPrompt();
        int input = scan.nextInt();
        if (input == 2) {
            System.out.println("Enter the file path: ");
            //handle bad case later
            MainManager.loadWork(scan.nextLine());
        }


        do {
            MainMenu();
            input = scan.nextInt();
            String className;
            switch (input) {
                case 1:
                    System.out.println(MainManager.getClassList());
                    break;
                case 2:
                    System.out.println("Enter the class name: ");
                    className = scan.nextLine();
                    MainManager.addClass(new UMLClass(className));
                    System.out.println(className + "was added");
                    break;
                case 3:
                    System.out.println("Enter the class name: ");
                    className = scan.nextLine();
                    MainManager.deleteClass(new UMLClass(className));
                    System.out.println(className + "was deleted");
                    break;
                case 4:
                    System.out.println("Enter file path to save to: ");
                    String filePath = scan.nextLine();
                     if (MainManager.saveWork(filePath))
                         System.out.println( "file was saved to" + filePath);
                     else
                         System.out.println( "Error: file was not saved to" + filePath);
                     break;
            }
        } while (input != 5);
    }

    private void initMenuPrompt(){
        System.out.println("Create or load a new file?");
        System.out.println("1: create a new project\n2: load a previous project");
    }


    private void MainMenu() {
        System.out.println(" 1. Display Class List \n 2. Add Class \n 3. Delete Class \n 4. Save Work \n 5. Quit");
        System.out.println("Enter number for option: ");

    }
}
