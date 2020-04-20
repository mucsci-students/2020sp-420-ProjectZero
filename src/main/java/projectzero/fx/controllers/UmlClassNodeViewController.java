package projectzero.fx.controllers;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import projectzero.core.Field;
import projectzero.core.Method;
import projectzero.core.UmlClass;

import java.net.URL;
import java.util.ResourceBundle;

public class UmlClassNodeViewController implements Initializable {
    private final UmlClass umlClass;
    private final ListProperty<Field> fieldListProperty = new SimpleListProperty<>();
    private final ListProperty<Method> methodListProperty = new SimpleListProperty<>();

    @FXML
    private Label classLabel;

    @FXML
    private VBox vBox;

    @FXML
    private ListView<Field> fieldListView;

    @FXML
    private ListView<Method> methodListView;

    public UmlClassNodeViewController(UmlClass umlClass) {
        this.umlClass = umlClass;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        classLabel.setText(this.umlClass.getName());

        fieldListView.itemsProperty().bind(fieldListProperty);
        fieldListProperty.set(umlClass.getFields());

        methodListView.itemsProperty().bind(methodListProperty);
        methodListProperty.set(umlClass.getMethods());

        vBox.setOnMouseDragged(event -> {
            Node tempNode = (Node) event.getSource();

            double newX = getTranslateX(tempNode, event.getX());
            double newY = getTranslateY(tempNode, event.getY());

            vBox.setTranslateX(newX);
            vBox.setTranslateY(newY);

            this.umlClass.setX(newX);
            this.umlClass.setY(newY);
        });
    }

    private double getTranslateY(Node node, double mouseY) {
        return node.getTranslateY() + mouseY - getHalfHeight();
    }

    private double getTranslateX(Node node, double mouseX) {
        return node.getTranslateX() + mouseX - getHalfWidth();
    }

    private double getHalfWidth() {
        return vBox.getWidth() / 2;
    }

    private double getHalfHeight() {
        return vBox.getHeight() / 2;
    }
}