package projectzero.fx.controllers;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    }
}