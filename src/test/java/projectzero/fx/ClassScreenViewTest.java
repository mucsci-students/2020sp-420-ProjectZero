package projectzero.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;
import projectzero.core.Field;
import projectzero.core.Method;
import projectzero.core.UmlClassManager;
import projectzero.fx.controllers.ClassScreenViewController;

import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class ClassScreenViewTest {
    ClassScreenViewController classScreenViewController;
    GridPane gridPane;
    UmlClassManager umlClassManager;

    @Start
    private void start(Stage stage) throws IOException {
        umlClassManager = new UmlClassManager();
        classScreenViewController = new ClassScreenViewController(null, umlClassManager);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClassScreenView.fxml"));
        loader.setController(classScreenViewController);

        gridPane = loader.load();

        stage.setScene(new Scene(gridPane));
        stage.show();
    }

    @Test
    void testGridPaneHasAll28Children() {
        Assertions.assertThat(gridPane.getChildren().size()).isEqualTo(28);
    }

    @Test
    void testAddEmptyClass(FxRobot robot) {
        robot.clickOn("#textBoxClassName");
        robot.write("Class");
        robot.clickOn("#applyButton");

        WaitForAsyncUtils.waitForFxEvents();

        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(1);
        Assertions.assertThat(umlClassManager.getUmlClass("Class")).isNotNull();
    }

    @Test
    void testAddBadClassName(FxRobot robot) {
        robot.clickOn("#textBoxClassName");
        robot.write("@Class");
        robot.clickOn("#applyButton");

        WaitForAsyncUtils.waitForFxEvents();

        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(0);
    }

    @Test
    void testAddField(FxRobot robot) {
        robot.clickOn("#textBoxClassName");
        robot.write("Class");

        robot.clickOn("#textBoxFieldName");
        robot.write("Field");
        robot.clickOn("#textBoxFieldType");
        robot.write("Type");
        robot.clickOn("#addFieldButton");

        robot.clickOn("#applyButton");

        WaitForAsyncUtils.waitForFxEvents();

        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(1);

        Field field = umlClassManager.getUmlClass("Class").getField("Field");
        Assertions.assertThat(field).isNotNull();
        Assertions.assertThat(field.getName()).isEqualTo("Field");
        Assertions.assertThat(field.getType()).isEqualTo("Type");

        // Assertions.assertThat(robot.lookup("#fieldDisplay").queryAs(ListView.class)).hasListCell(field);
    }

    @Test
    void testAddMethod(FxRobot robot) {
        robot.clickOn("#textBoxClassName");
        robot.write("Class");

        robot.clickOn("#textBoxMethodName");
        robot.write("Method");
        robot.clickOn("#textBoxMethodReturnType");
        robot.write("ReturnType");
        robot.clickOn("#textBoxMethodParamType");
        robot.write("ParamType");
        robot.type(KeyCode.ENTER);

        robot.clickOn("#addMethodButton");

        robot.clickOn("#applyButton");

        WaitForAsyncUtils.waitForFxEvents();

        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(1);

        Method method = umlClassManager.getUmlClass("Class").getMethod("Method");
        Assertions.assertThat(method).isNotNull();
        Assertions.assertThat(method.getName()).isEqualTo("Method");
        Assertions.assertThat(method.getType()).isEqualTo("ReturnType");
        Assertions.assertThat(method.getParameterTypes().size()).isEqualTo(1);
        Assertions.assertThat(method.getParameterTypes()).contains("ParamType");
    }
}
