package projectzero;

import projectzero.cli.CliApplication;

public class Main {
    public static void main(String[] args) {
        String version = "1";

        if (args.length > 1) {
            version = args[0];
        }

        if (args.equals("0")) {
            CliApplication cliApplication = new CliApplication();
            cliApplication.run();
        } else {
           System.out.println("FX APP");
        }
    }
}
