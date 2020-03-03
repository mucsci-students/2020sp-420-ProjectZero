package projectzero.fx;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import projectzero.core.UmlClassManager;
import projectzero.core.UmlClassYamlMapper;

import java.io.IOException;

public class ContentPane extends VBox {
    private ButtonPane buttonPane;
    private GraphicsPane graphicsPane;
    private UmlClassManager mainManager;

    public ContentPane(){
        setupPanes();
        mainManager = new UmlClassManager();
    }
    public ContentPane(UmlClassYamlMapper umlClassYamlMapper){
        setupPanes();
        mainManager = new UmlClassManager(umlClassYamlMapper);
    }

    private void setupPanes(){
        buttonPane = new ButtonPane();
        graphicsPane = new GraphicsPane();
        this.getChildren().addAll(buttonPane,graphicsPane);
    }
}
