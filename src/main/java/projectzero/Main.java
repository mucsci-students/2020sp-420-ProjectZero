package projectzero;

import javafx.application.Application;
import projectzero.cli.CliApplication;
import projectzero.fx.FXApplication;

public class Main {
    public static void main(String args[]) {
        String version = "1";

        if (args.length > 0) {
            version = args[0];
        }

        if (version.equals("0")) {
            CliApplication cliApplication = new CliApplication();
            cliApplication.run();
        } else {
            Application.launch(FXApplication.class,args);
        }
    }
}
