package projectzero.fx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import projectzero.core.UmlClass;

import java.net.URL;
import java.util.ResourceBundle;

public class EditClassScreenController extends ClassScreenController implements Initializable {
    private UmlClass editedUmlClass;
    public EditClassScreenController(UmlClass editedUmlClass){
        this.editedUmlClass = editedUmlClass;
    }
    @Override
    public void applyUMLClass(ActionEvent event) {
        //vvvv Can't change edited UMLClass name vvvv
        methods.forEach(method -> editedUmlClass.addMethod(method));
        mainManager.updateUmlClass(editedUmlClass.getName(),editedUmlClass);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location,resources);
        fillGUIComponents();
    }

    private void fillGUIComponents(){
        this.textBoxClassName.setText(this.editedUmlClass.getName());
        this.editedUmlClass.getFields().forEach(field -> fields.add(field));
        this.editedUmlClass.getMethods().forEach(method -> methods.add(method));
        this.editedUmlClass.getRelationships().forEach(relationship -> relationships.add(relationship));

    }
}
