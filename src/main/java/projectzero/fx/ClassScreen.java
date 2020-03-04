package projectzero.fx;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ClassScreen extends Stage {
    private final String TITLE = "Class Mod Screen";

    public ClassScreen(FXMLLoader loader) {
        loader.setLocation(getClass().getResource("/fxml/class-screen-layout.fxml"));
        try {
            VBox root = loader.load();
            Scene mainScene = new Scene(root);
            mainScene.getStylesheets().add(getClass().getResource("/css/mainStyle.css").toExternalForm());
            this.setScene(mainScene);
            this.setTitle(TITLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
