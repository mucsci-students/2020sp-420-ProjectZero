package projectzero.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import projectzero.core.Field;
import projectzero.core.Method;
import projectzero.core.UmlClass;
import java.io.IOException;

public class ClassNode extends Pane{
    private UmlClass umlClass;
    private Label classLabel;
    private VBox fieldBox,methodBox,mainLayout;

    public ClassNode(UmlClass umlClass, ContentPaneController controller){
        this.umlClass = umlClass;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/class-node-layout.fxml"));
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initlizeFields();
        classLabel.setText(this.umlClass.getName());

        for(Field field: this.umlClass.getFields()){
            fieldBox.getChildren().add(new Label(field.getName()));
        }

        for(Method method: this.umlClass.getMethods()){
            methodBox.getChildren().add(new Label(method.getName()));
        }

        this.setOnMouseDragged(event -> {
            Node tempNode = (Node)event.getSource();
            this.setTranslateX(getTranslateX(tempNode,event.getX()));
            this.setTranslateY(getTranslateY(tempNode,event.getY()));
        });

        this.setOnMouseReleased(event -> controller.setSelectedUMLClass(this.umlClass));
    }

    private void initlizeFields() {
        mainLayout = (VBox)this.getChildren().get(0);
        classLabel = (Label) mainLayout.getChildren().get(0);
        fieldBox = (VBox)mainLayout.getChildren().get(2);
        methodBox = (VBox)mainLayout.getChildren().get(4);
    }
    public UmlClass getUmlClass(){
            return this.umlClass;
    }

    private double getTranslateY(Node node,double mouseY){
        return node.getTranslateY() + mouseY - getHalfHeight();
    }

    private double getTranslateX(Node node,double mouseX){
        return node.getTranslateX() + mouseX - getHalfWidth();
    }
    private double getHalfWidth(){
        return this.getWidth() / 2;
    }
    private double getHalfHeight(){
        return this.getHeight() / 2;
    }


}