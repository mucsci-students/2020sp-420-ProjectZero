package projectzero.fx;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class FXApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException{
        ContentPane newStage = new ContentPane();
        newStage.show();
    }
}
