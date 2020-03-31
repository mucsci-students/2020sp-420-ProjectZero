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
            UmlClass umlClass = new UmlClass(textBoxClassName.getText());
            for(Method method: methods)
                umlClass.addMethod(method);
            for(Field field: fields)
                umlClass.addField(field);
            for(Relationship relationship: relationships)
                umlClass.addRelationship(relationship);

            if(mainManager.addUmlClass(umlClass) == null)
                updateComboBoxRelationships(umlClass);
            textBoxClassName.setStyle("-fx-text-box-border: #9A9A9A; -fx-focus-color: darkslateblue;");
            fieldDisplay.setStyle("-fx-border-color: #9A9A9A; -fx-focus-color: darkslateblue;");
            resetData();
        } catch (InvalidNameException e) {
            textBoxClassName.setStyle(" -fx-text-box-border: red ; -fx-focus-color: red ;");
        }
    }
}
