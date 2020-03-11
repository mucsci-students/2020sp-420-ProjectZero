package projectzero.fx;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;
import projectzero.core.UmlClassYamlMapper;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ContentPane extends Stage{
    public static final int WIDTH = 720;
    public static final int HEIGHT = 480;
    private final String TITLE = "FX Application";
    public ContentPane(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/content-pane-layout.fxml"));
            VBox mainLayout;
            mainLayout = loader.<VBox>load();
            Scene mainScene = new Scene(mainLayout,WIDTH,HEIGHT);
            mainScene.getStylesheets().add(getClass().getResource("/css/mainScreenStyle.css").toExternalForm());
            this.setScene(mainScene);
            this.setTitle(TITLE);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    /*public ContentPane(UmlClassYamlMapper umlClassYamlMapper){
        mainManager = new UmlClassManager(umlClassYamlMapper);
        List<UmlClass> classList = mainManager.listUmlClasses();
    }*/
}
