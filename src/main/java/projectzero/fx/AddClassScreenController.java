package projectzero.fx;

import javafx.event.ActionEvent;
import projectzero.core.Field;
import projectzero.core.Method;
import projectzero.core.Relationship;
import projectzero.core.UmlClass;
import projectzero.core.exceptions.DuplicateAttributeException;
import projectzero.core.exceptions.InvalidNameException;

public class AddClassScreenController  extends ClassScreenController{

    @Override
    public void applyUMLClass(ActionEvent event) {
        try {
            UmlClass umlClass = new UmlClass(textBoxClass.getText());
            for(String method: methods)
                umlClass.addMethod(new Method(method));
            for(String field: fields)
                umlClass.addField((new Field(field)));
            //Get rid of this code later. Because the relationships need to be
            //existing classes.
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
   /*     catch(DuplicateAttributeException e){
            fieldDisplay.setStyle(" -fx-border-color: red ; -fx-focus-color: red ;");
        }*/


    }
}
