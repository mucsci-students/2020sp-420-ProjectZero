package projectzero.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class ClassScreenController implements Initializable {
    private ObservableList<String> methods, fields, relationships;
    @FXML
    private TextField textBoxMethods;
    @FXML
    private TextField textBoxFields;
    @FXML
    private ComboBox<String> comboRelationships;
    @FXML
    private ListView<String> methodDisplay, fieldDisplay, relationshipDisplay;

    public void addMethod(ActionEvent e){
        methods.add(textBoxMethods.getText());
        textBoxMethods.setText("");
    }

    public void addField(ActionEvent e){
        fields.add(textBoxFields.getText());
        textBoxFields.setText("");
    }

    public void addRelationship(ActionEvent e){
        relationships.add(comboRelationships.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupListViews();
    }
    public void minusMethod(ActionEvent e){
        methods.remove(methodDisplay.getSelectionModel().getSelectedItem());
    }
    public void minusField(ActionEvent e){
        fields.remove(fieldDisplay.getSelectionModel().getSelectedItem());
    }
    public void minusRelationship(ActionEvent e){
        relationships.remove(relationshipDisplay.getSelectionModel().getSelectedItem());
    }

    private void setupListViews(){
        methods = FXCollections.observableArrayList();
        fields = FXCollections.observableArrayList();
        relationships = FXCollections.observableArrayList();

        methodDisplay.setItems(methods);
        fieldDisplay.setItems(fields);
        relationshipDisplay.setItems(relationships);
    }
}
