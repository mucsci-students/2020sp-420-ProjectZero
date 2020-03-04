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

public class ClassNode{
    private UmlClass data;
    private Pane pane;
    private Label classLabel;
    private VBox fieldBox,methodBox,mainLayout;

    public ClassNode(UmlClass data) {
        this.data = data;
        try {
            pane  = FXMLLoader.<Pane>load(getClass().getResource("/fxml/class-node-layout.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initlizeFields();
        classLabel.setText(this.data.getName());

        for(Field field: this.data.getFields()){
            fieldBox.getChildren().add(new Label(field.getName()));
        }

        for(Method method: this.data.getMethods()){
            methodBox.getChildren().add(new Label(method.getName()));
        }

        pane.setOnMouseDragged(event -> {
            Node tempNode = (Node)event.getSource();
            pane.setTranslateX(getTranslateX(tempNode,event.getX()));
            pane.setTranslateY(getTranslateY(tempNode,event.getY()));
        });

        pane.getStyleClass().add("classnode");
    }

    private void initlizeFields() {
        mainLayout = (VBox)pane.getChildren().get(0);
        classLabel = (Label) mainLayout.getChildren().get(0);
        fieldBox = (VBox)mainLayout.getChildren().get(2);
        methodBox = (VBox)mainLayout.getChildren().get(4);
    }

    public Pane getDisplayPane(){
        return pane;
    }

    private double getTranslateY(Node node,double mouseY){
        return node.getTranslateY() + mouseY - getHalfHeight();
    }

    private double getTranslateX(Node node,double mouseX){
        return node.getTranslateX() + mouseX - getHalfWidth();
    }
    private double getHalfWidth(){
        return pane.getWidth() / 2;
    }
    private double getHalfHeight(){
        return pane.getHeight() / 2;
    }


}