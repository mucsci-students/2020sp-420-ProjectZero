package projectzero.fx;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projectzero.core.UmlClassManager;

import java.io.IOException;

public class ClassScreen extends Stage {
    private String TITLE;

    public ClassScreen(UmlClassManager mainManager, ClassScreenType type) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/class-screen-layout.fxml"));
            setScreenController(loader,type);
            ((ClassScreenController)loader.getController()).setUMLClassManager(mainManager);
            VBox root = loader.load();
            Scene mainScene = new Scene(root);
            mainScene.getStylesheets().add(getClass().getResource("/css/mainStyle.css").toExternalForm());
            mainScene.getStylesheets().add(getClass().getResource("/css/classScreenStyle.css").toExternalForm());
            this.setScene(mainScene);
            this.setTitle(TITLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setScreenController(FXMLLoader loader, ClassScreenType type) {
        switch(type){
            case ADDSCREEN:
                loader.setController(new AddClassScreenController());
                TITLE = "Add UMLClass Screen";
                break;
            case EDITSCREEN:
                //vvvv Not working yet vvvv
                loader.setController(new EditClassScreenController());
                TITLE = "Edit UMLClass Screen";
        }

    }
}
