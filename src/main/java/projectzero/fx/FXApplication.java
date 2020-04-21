package projectzero.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projectzero.core.UmlClassManager;
import projectzero.fx.controllers.ParentViewController;

import java.io.IOException;

public class FXApplication extends Application {
    UmlClassManager umlClassManager;

    public FXApplication() {
        this.umlClassManager = new UmlClassManager();
    }

    public FXApplication(UmlClassManager umlClassManager) {
        this.umlClassManager = umlClassManager;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ParentView.fxml"));

        ParentViewController parentViewController = new ParentViewController(this.umlClassManager);
        fxmlLoader.setController(parentViewController);

        Parent parent = fxmlLoader.load();

        Scene scene = new Scene(parent, 720, 480);
        scene.getStylesheets().add(getClass().getResource("/css/Parent.css").toExternalForm());

        stage.setTitle("UML Editor");
        stage.setScene(scene);

        stage.show();
    }
}
