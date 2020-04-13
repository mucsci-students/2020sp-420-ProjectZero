package projectzero.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import projectzero.core.UmlClassManager;

import java.io.IOException;

public class ClassScreen extends Stage {
    private String TITLE;

    public ClassScreen(UmlClassManager mainManager, String editableClass) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/class-screen-layout.fxml"));
            setScreenController(loader,editableClass);
            ((ClassScreenController)loader.getController()).setUMLClassManager(mainManager);
            GridPane root = loader.load();
            Scene mainScene = new Scene(root);
            mainScene.getStylesheets().add(getClass().getResource("/css/Parent.css").toExternalForm());
            mainScene.getStylesheets().add(getClass().getResource("/css/class-screen.css").toExternalForm());
            this.setScene(mainScene);
            this.setTitle(TITLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setScreenController(FXMLLoader loader,String editableClassName) {
            if(editableClassName.equals("")) {
                loader.setController(new AddClassScreenController());
                TITLE = "Add UMLClass Screen";
            }
            else {
                loader.setController(new EditClassScreenController(editableClassName));
                TITLE = "Edit UMLClass Screen";
            }
    }
}
