package projectzero.fx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import projectzero.core.UmlClass;
import projectzero.core.exceptions.InvalidNameException;

import java.net.URL;
import java.util.ResourceBundle;

public class EditClassScreenController extends ClassScreenController implements Initializable {
    private final String originalClassName;

    public EditClassScreenController(String editedUmlClassName){
        this.originalClassName = editedUmlClassName;
    }

    @Override
    public void applyUMLClass(ActionEvent event) {
        try {
            UmlClass originalUMLClass = new UmlClass(textBoxClassName.getText());
            fields.forEach(originalUMLClass::addField);
            methods.forEach(originalUMLClass::addMethod);
            relationships.forEach(originalUMLClass::addRelationship);
            mainManager.updateUmlClass(originalClassName,originalUMLClass);
            textBoxClassName.setStyle("-fx-text-box-border: #9A9A9A; -fx-focus-color: darkslateblue;");
            fieldDisplay.setStyle("-fx-border-color: #9A9A9A; -fx-focus-color: darkslateblue;");
        } catch (InvalidNameException e) {
            textBoxClassName.setStyle(" -fx-text-box-border: red ; -fx-focus-color: red ;");
        }
        catch(NullPointerException e){
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location,resources);
        fillGUIComponents();
    }

    private void fillGUIComponents(){
        UmlClass originalUMLClass = mainManager.getUmlClass(originalClassName);
        this.textBoxClassName.setText(originalUMLClass.getName());
        fields.addAll(originalUMLClass.getFields());
        methods.addAll(originalUMLClass.getMethods());
        relationships.addAll(originalUMLClass.getRelationships());
    }
}
