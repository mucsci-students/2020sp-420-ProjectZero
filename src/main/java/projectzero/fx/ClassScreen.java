package projectzero.fx;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projectzero.core.UmlClassManager;

import java.io.IOException;

public class ClassScreen extends Stage {
    private final String TITLE = "Class Mod Screen";

    public ClassScreen(UmlClassManager mainManager) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/class-screen-layout.fxml"));
            VBox root = loader.load();
            ((ClassScreenController)loader.getController()).setUMLClassManager(mainManager);
            Scene mainScene = new Scene(root);
            mainScene.getStylesheets().add(getClass().getResource("/css/mainStyle.css").toExternalForm());
            this.setScene(mainScene);
            this.setTitle(TITLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
