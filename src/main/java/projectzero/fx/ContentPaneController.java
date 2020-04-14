package projectzero.fx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentPaneController implements Initializable, Observer<UmlClass>{
    private UmlClassManager mainManager;
    private String selectedUMLClass;
    private Stage mainMindow;
    @FXML
    private StackPane graphicsPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainManager = new UmlClassManager();
        selectedUMLClass = "";
        mainManager.register(this);
    }
    public void addButtonClick(ActionEvent event){
        ClassScreen classScreen = new ClassScreen(mainManager, "");
        classScreen.initOwner(mainMindow);
        classScreen.initModality(Modality.APPLICATION_MODAL);
        classScreen.show();
    }
//try this.
    public void editButtonClick(ActionEvent event){
        if(!selectedUMLClass.equals("")) {
            ClassScreen classScreen = new ClassScreen(mainManager, selectedUMLClass);
            classScreen.initOwner(mainMindow);
            classScreen.initModality(Modality.APPLICATION_MODAL);
            classScreen.show();
        }
    }

    public void deleteButtonClick(ActionEvent event){
        if(!selectedUMLClass.equals("")){
            mainManager.deleteUmlClass(selectedUMLClass);
        }
    }

    @Override
    public void update(UmlClass umlClass) {
        //Delete or add occured.
        if(mainManager.listUmlClasses().size() != graphicsPane.getChildren().size()) {
            if (mainManager.getUmlClass(umlClass.getName()) == null) {
                ClassNode tempNode = getClassNode(umlClass.getName());
                graphicsPane.getChildren().remove((Node)tempNode);
            }
            else{
                graphicsPane.getChildren().add(new ClassNode(umlClass,this));
            }
        }
        else{
            graphicsPane.getChildren().remove((Node)getClassNode(selectedUMLClass));
            graphicsPane.getChildren().add(new ClassNode(umlClass,this));
        }
        selectedUMLClass = "";
    }
    public void setSelectedUMLClass(String umlClassName){
        selectedUMLClass = umlClassName;
    }

    public void handleOnSaveClick(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Yaml", "*.yaml","*.yml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showSaveDialog(null);
        if(file == null)
            return;
        else{
            try {
                mainManager.save(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleOnLoadClick(){}

    public ClassNode getClassNode(String umlClassName){
        ClassNode tempNode = null;
        ObservableList<Node> classNodeList = graphicsPane.getChildren();

        for(Node node: classNodeList){
            tempNode = (ClassNode) node;
            if(tempNode.getClass().getName().equals(umlClassName)){
                break;
            }
        }
        return tempNode;
    }

    public StackPane getGraphicsPane(){
        return graphicsPane;
    }

    public void setMainWindow(Stage stage){
        this.mainMindow = stage;
    }
}
