package projectzero.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projectzero.core.UmlClassManager;

import java.io.IOException;

public class FXApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ParentView.fxml"));

        Parent parent = fxmlLoader.load();
        ParentViewController parentViewController = fxmlLoader.getController();

        parentViewController.initialize(new UmlClassManager());

        Scene scene = new Scene(parent,720 ,480);
        scene.getStylesheets().add(getClass().getResource("/css/Parent.css").toExternalForm());

        stage.setTitle("UML Editor");
        stage.setScene(scene);

        stage.show();
    }
}
