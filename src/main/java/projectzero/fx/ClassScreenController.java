package projectzero.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import projectzero.core.*;
import projectzero.core.exceptions.DuplicateAttributeException;
import projectzero.core.exceptions.InvalidNameException;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class ClassScreenController implements Initializable {
    protected ObservableList<String> methods, fields, relationships;
    protected UmlClassManager mainManager;
    @FXML
    protected TextField textBoxClass;
    @FXML
    protected TextField textBoxMethods;
    @FXML
    protected TextField textBoxFields;
    @FXML
    protected ComboBox<String> comboRelationships;
    @FXML
    protected ListView<String> methodDisplay, fieldDisplay, relationshipDisplay;

    public void addMethod(ActionEvent e) {
        methods.add(textBoxMethods.getText());
        textBoxMethods.setText("");
    }

    public void addField(ActionEvent e) {
        fields.add(textBoxFields.getText());
        textBoxFields.setText("");
    }

    public void addRelationship(ActionEvent e) {
        relationships.add(comboRelationships.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupListViews();
    }

    public void minusMethod(ActionEvent e) {
        methods.remove(methodDisplay.getSelectionModel().getSelectedItem());
    }

    public void minusField(ActionEvent e) {
        fields.remove(fieldDisplay.getSelectionModel().getSelectedItem());
    }

    public void minusRelationship(ActionEvent e) {
        relationships.remove(relationshipDisplay.getSelectionModel().getSelectedItem());
    }
    public abstract void applyUMLClass(ActionEvent event);

    public void setUMLClassManager(UmlClassManager mainManager){
        this.mainManager = mainManager;
        fillRelationships();
    }

    protected void fillRelationships(){
        mainManager.listUmlClasses().forEach(umlClass -> comboRelationships.getItems().add(umlClass.getName()));
    }

    protected void updateComboBoxRelationships(UmlClass umlClass){
        comboRelationships.getItems().add(umlClass.getName());
    }

    private void setupListViews() {
        methods = FXCollections.observableArrayList();
        fields = FXCollections.observableArrayList();
        relationships = FXCollections.observableArrayList();

        methodDisplay.setItems(methods);
        fieldDisplay.setItems(fields);
        relationshipDisplay.setItems(relationships);
    }

    protected void resetData(){
        textBoxClass.setText("");
        textBoxMethods.setText("");
        textBoxFields.setText("");
        comboRelationships.getSelectionModel().clearSelection();
        methods.removeAll(methods);
        fields.removeAll(fields);
        relationships.removeAll(relationships);
    }
}
