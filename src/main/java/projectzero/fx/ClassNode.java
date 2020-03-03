package projectzero.fx;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import projectzero.core.Method;
import projectzero.core.UmlClass;
import projectzero.core.Field;


import java.util.Map;

public class ClassNode extends AnchorPane {
    private UmlClass data;
    private static final double offsetX = -350.0;
    private static final double offsetY = -100.0;
    private static final double WIDTH = 100.0, HEIGHT = 80.0;
    public ClassNode(UmlClass data){
        this.setPrefSize(WIDTH, HEIGHT);
        this.setMaxSize(WIDTH, HEIGHT);
        this.data = data;
        VBox layout = new VBox();
        Label classLabel = new Label(this.data.getName());
        Map<String, Field> fieldMap = this.data.getFieldMap();
        VBox fieldBox = new VBox();
        for(Map.Entry<String,Field> mapValue: fieldMap.entrySet()){
            Label fieldLabel = new Label(mapValue.getValue().getName());
            fieldBox.getChildren().add(fieldLabel);
        }
        VBox methodBox = new VBox();
        Map<String, Method> methodMap = this.data.getMethodMap();

        for(Map.Entry<String,Method> mapValue: methodMap.entrySet()){
            Label methodLabel = new Label(mapValue.getValue().getName());
            methodBox.getChildren().add(methodLabel);
        }
        Separator fieldLine = new Separator(Orientation.HORIZONTAL);
        fieldLine.setMaxWidth(WIDTH);
        fieldLine.setPrefWidth(WIDTH);
        Separator methodLine = new Separator(Orientation.HORIZONTAL);
        methodLine.setMaxWidth(WIDTH);
        methodLine.setPrefWidth(WIDTH);
        layout.getChildren().addAll(classLabel, fieldLine, fieldBox, methodLine, methodBox);
        this.getChildren().add(layout);
        this.setStyle("-fx-background-color: blue;");
        this.setOnMouseDragged(event -> {
            this.setTranslateX(event.getSceneX() + offsetX);
            this.setTranslateY(event.getSceneY() + offsetY);
        });
    }
}
