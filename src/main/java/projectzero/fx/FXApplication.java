package projectzero.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FXApplication extends Application {
    public static final int WIDTH = 720;
    public static final int HEIGHT = 480;
    private final String TITLE = "FX Application";

    public FXApplication(){
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        Scene mainScene = new Scene(new ContentPane(), WIDTH,HEIGHT);
        mainScene.getStylesheets().add(getClass().getResource("/css/mainScreenStyle.css").toExternalForm());
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}
