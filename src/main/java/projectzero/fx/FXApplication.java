package projectzero.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXApplication extends Application {

    public FXApplication(){
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        ContentPane newStage = new ContentPane();
        newStage.show();
    }
}
