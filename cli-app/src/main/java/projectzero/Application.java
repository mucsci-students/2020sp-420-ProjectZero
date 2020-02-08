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
        MainMenu();
    }

    private void initMenuPrompt() {
        String input = "";
        boolean validInput = true;
        int[] validInputs = {1,2};
        do {
            System.out.println("Create or load a new file?");
            System.out.println("1: create a new project\n2: load a previous project");
            input = scan.next();
            validInput = Validation.isValidMenuInput(validInputs, input);
            if (validInput) {
                //does not collect file path name, skips to main menu
                if (Integer.parseInt(input) == 2) {
                    System.out.println("Enter the file path: ");
                    MainManager.load(scan.nextLine());
                }
            }
        } while (!validInput);

    }

    private void MainMenu() {
        int input;
        do {
            System.out.println(" 1. Display Class List \n 2. Add Class \n 3. Delete Class \n 4. Save Work \n 5. Quit");
            System.out.println("Enter number for option: ");
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
                    if (MainManager.save(filePath))
                        System.out.println("file was saved to" + filePath);
                    else
                        System.out.println("file was not saved to" + filePath);
                    break;
                default :
            }
        } while (input != 5);
    }
}
