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

public class ClassScreenController implements Initializable {
    private ObservableList<String> methods, fields, relationships;
    private UmlClassManager mainManager;
    @FXML
    private TextField textBoxClass;
    @FXML
    private TextField textBoxMethods;
    @FXML
    private TextField textBoxFields;
    @FXML
    private ComboBox<String> comboRelationships;
    @FXML
    private ListView<String> methodDisplay, fieldDisplay, relationshipDisplay;

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
    public void applyUMLClass(ActionEvent event){
        try {
            UmlClass umlClass = new UmlClass(textBoxClass.getText());
            for(String method: methods)
                umlClass.addMethod(new Method(method));
            for(String field: fields)
                umlClass.addField((new Field(field)));
            for(String relationship: relationships)
                umlClass.addRelationship(new Relationship(mainManager.getUmlClass(relationship)));
            if(mainManager.addUmlClass(umlClass) == null)
                updateComboBoxRelationships(umlClass);
                textBoxClass.setStyle("-fx-text-box-border: #9A9A9A; -fx-focus-color: darkslateblue;");
                fieldDisplay.setStyle("-fx-border-color: #9A9A9A; -fx-focus-color: darkslateblue;");
            resetData();
        } catch (InvalidNameException e) {
            textBoxClass.setStyle(" -fx-text-box-border: red ; -fx-focus-color: red ;");
        }
         catch(DuplicateAttributeException e){
            fieldDisplay.setStyle(" -fx-border-color: red ; -fx-focus-color: red ;");
         }


    }

    public void setUMLClassManager(UmlClassManager mainManager){
        this.mainManager = mainManager;
        fillRelationships();
    }

    private void fillRelationships(){
        mainManager.listUmlClasses().forEach(umlClass -> comboRelationships.getItems().add(umlClass.getName()));
    }

    private void updateComboBoxRelationships(UmlClass umlClass){
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

    private void resetData(){
        textBoxClass.setText("");
        textBoxMethods.setText("");
        textBoxFields.setText("");
        comboRelationships.getSelectionModel().clearSelection();
        methods.removeAll(methods);
        fields.removeAll(fields);
        relationships.removeAll(relationships);
    }
}
