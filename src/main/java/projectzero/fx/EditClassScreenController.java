package projectzero.fx;

import javafx.event.ActionEvent;
import projectzero.core.UmlClass;

// vvvv Still in progress vvvv
public class EditClassScreenController extends ClassScreenController{/*
    private UmlClass editedUmlClass;
    public EditClassScreenController(UmlClass editedUmlClass){
        this.editedUmlClass = editedUmlClass;
        fillGUIComponents();
    }*/
    @Override
    public void applyUMLClass(ActionEvent event) {

    }

/*    private void fillGUIComponents(){
        this.textBoxClass.setText(this.editedUmlClass.getName());
        this.editedUmlClass.getFields().forEach(field -> fields.add(field.getName()));
        this.editedUmlClass.getMethods().forEach(method -> methods.add(method.getName()));
        this.editedUmlClass.getRelationships().forEach(relationship -> relationships.add(relationship.getTo().getName()));

    }*/
}
