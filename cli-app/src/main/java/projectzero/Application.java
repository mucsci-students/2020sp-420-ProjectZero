package projectzero;

import java.util.Scanner;

public class Application {
    private UMLClassManager MainManager;

    public Application() {
        MainManager = new UMLClassManager();


    }

    public void run() {
        initMenuPrompt();
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        if (input == 2) {
            System.out.println("Enter the file path: ");
            //handle bad case later
            MainManager.loadWork(scan.nextLine());

        }
        System.out.println("Press 'q' to quit");
        MainMenu();
        while (input != 'q')
        {
    }
    public void initMenuPrompt(){
        System.out.println("Create or load a new file?");
        System.out.println("1: create a new project\n2: load a previous project");
    }
}

    private void MainMenu() {
        
    }
}
