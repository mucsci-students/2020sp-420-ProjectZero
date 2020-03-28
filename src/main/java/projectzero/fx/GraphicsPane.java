package projectzero.fx;

import javafx.scene.layout.StackPane;
import projectzero.core.Field;
import projectzero.core.Method;
import projectzero.core.UmlClass;

import java.util.ArrayList;

public class GraphicsPane extends StackPane {
    public GraphicsPane() {
        this.setPrefSize(200, 200);
        try {
            UmlClass testClass = new UmlClass("testName");
            testClass.addField(new Field.Builder()
                    .withName("field")
                    .withType("type")
                    .build()
            );
            testClass.addMethod(new Method.Builder()
                    .withName("method")
                    .withType("type")
                    .withParameterTypes(new ArrayList<>())
                    .build()
            );
            ClassNode testClassNode = new ClassNode(testClass);
            this.getChildren().add(testClassNode);
        } catch (Exception e) {

        }
    }
}
