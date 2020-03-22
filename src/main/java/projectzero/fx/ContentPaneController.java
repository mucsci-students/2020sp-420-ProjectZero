package projectzero.fx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;
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
        new ClassScreen(mainManager).show();
    }

    public void editButtonClick(ActionEvent event){

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
}
