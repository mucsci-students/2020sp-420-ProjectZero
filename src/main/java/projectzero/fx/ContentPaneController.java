package projectzero.fx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContentPaneController implements Initializable, Observer<UmlClass>{
    private UmlClassManager mainManager;
    private UmlClass selectedUMLClass;
    @FXML
    private StackPane graphicsPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainManager = new UmlClassManager();
        selectedUMLClass = null;
        mainManager.register(this);
    }
    public void addButtonClick(ActionEvent event){
        new ClassScreen(mainManager, ClassScreenType.ADDSCREEN).show();
    }

    public void editButtonClick(ActionEvent event){
        new ClassScreen(mainManager,ClassScreenType.EDITSCREEN).show();
    }

    public void deleteButtonClick(ActionEvent event){
        if(selectedUMLClass != null){
            mainManager.deleteUmlClass(selectedUMLClass.getName());
            selectedUMLClass = null;
        }
    }

    @Override
    public void update(UmlClass umlClass) {
        //Delete or add occured.
        if(mainManager.listUmlClasses().size() != graphicsPane.getChildren().size()) {
            if (mainManager.getUmlClass(umlClass.getName()) == null) {
                ClassNode tempNode = null;
                ObservableList<Node> classNodeList = graphicsPane.getChildren();

                for(Node node: classNodeList){
                    tempNode = (ClassNode) node;
                    if(tempNode.getClass().getName().equals(umlClass.getName())){
                        break;
                    }
                }
                classNodeList.remove((Node)tempNode);
            }
            else{
                graphicsPane.getChildren().add(new ClassNode(umlClass,this));
            }
        }
        else{//Edit occured. Not implemented yet.

        }
    }
    public void setSelectedUMLClass(UmlClass umlClass){
        selectedUMLClass = umlClass;
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
    public void handleOnLoadClick(){

    }
}
