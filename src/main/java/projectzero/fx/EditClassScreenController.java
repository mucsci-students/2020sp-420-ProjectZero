package projectzero.fx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import projectzero.core.UmlClass;
import projectzero.core.exceptions.InvalidNameException;

import java.net.URL;
import java.util.ResourceBundle;

public class EditClassScreenController extends ClassScreenController implements Initializable {
    private String originalClassName;

    public EditClassScreenController(String editedUmlClassName){
        this.originalClassName = editedUmlClassName;
    }

    @Override
    public void applyUMLClass(ActionEvent event) {
        try {
            UmlClass originalUMLClass = new UmlClass(textBoxClassName.getText());
            fields.forEach(field -> originalUMLClass.addField(field));
            methods.forEach(method -> originalUMLClass.addMethod(method));
            relationships.forEach(relationship -> originalUMLClass.addRelationship(relationship));
            mainManager.updateUmlClass(originalClassName,originalUMLClass);
        } catch (InvalidNameException e) {
            e.printStackTrace();
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
        originalUMLClass.getFields().forEach(field -> fields.add(field));
        originalUMLClass.getMethods().forEach(method -> methods.add(method));
        originalUMLClass.getRelationships().forEach(relationship -> relationships.add(relationship));

    }
}
