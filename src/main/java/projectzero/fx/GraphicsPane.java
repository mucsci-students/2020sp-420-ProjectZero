package projectzero.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import projectzero.core.Field;
import projectzero.core.Method;
import projectzero.core.UmlClass;
import projectzero.core.exceptions.InvalidNameException;

import java.io.IOException;

public class GraphicsPane extends StackPane{
    public GraphicsPane(){
        this.setPrefSize(200,200);
        try {
            UmlClass testClass = new UmlClass("testName");
            testClass.addField(new Field("fieldName"));
            testClass.addMethod(new Method("methodName"));
            ClassNode newNode = new ClassNode(testClass);
            this.getChildren().add(newNode.getDisplayPane());

        }
        catch(InvalidNameException e){
            e.printStackTrace();
        }
    }
}
