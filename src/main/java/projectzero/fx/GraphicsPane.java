package projectzero.fx;

import javafx.scene.layout.StackPane;
import projectzero.core.Field;
import projectzero.core.Method;
import projectzero.core.UmlClass;

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
            testClass.addMethod(new Method("methodName"));
            ClassNode testClassNode = new ClassNode(testClass);
            this.getChildren().add(testClassNode);
        } catch (Exception e) {

        }
    }
}
