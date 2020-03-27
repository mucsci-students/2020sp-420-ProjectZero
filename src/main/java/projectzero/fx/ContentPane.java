package projectzero.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class ContentPane extends Stage{
    public static final int WIDTH = 720;
    public static final int HEIGHT = 480;
    private final String TITLE = "FX Application";
    public ContentPane(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/content-pane-layout.fxml"));
            VBox mainLayout = loader.<VBox>load();
            Scene mainScene = new Scene(mainLayout,WIDTH,HEIGHT);
            mainScene.getStylesheets().add(getClass().getResource("/css/mainStyle.css").toExternalForm());
            mainScene.getStylesheets().add(getClass().getResource("/css/contentScreenStyle.css").toExternalForm());
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
