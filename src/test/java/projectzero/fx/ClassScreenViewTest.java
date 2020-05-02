package projectzero.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
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
    Scene testScene;

    @Start
    private void start(Stage stage) throws IOException{
        umlClassManager = new UmlClassManager();
        classScreenViewController = new ClassScreenViewController(null, umlClassManager);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClassScreenView.fxml"));
        loader.setController(classScreenViewController);

        gridPane = loader.load();

        testScene = new Scene(gridPane);
        stage.setScene(testScene);
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

        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(1);
        Assertions.assertThat(umlClassManager.getUmlClass("Class")).isNotNull();
    }

    @Test
    void testEmptyClassBox(FxRobot robot){
        robot.clickOn("#applyButton");
        TextField textBoxClassName = (TextField) testScene.lookup("#textBoxClassName");
        Assertions.assertThat(textBoxClassName.getStyle()).isEqualTo("-fx-text-box-border: red ; -fx-focus-color: red ;");
        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(0);

    }

    @Test
    void testEmptyFieldBox(FxRobot robot){
        robot.clickOn("#textBoxClassName");
        robot.write("Class");
        robot.clickOn("#addFieldButton");

        TextField textBoxFieldName = (TextField) testScene.lookup("#textBoxFieldName");
        Assertions.assertThat(textBoxFieldName.getStyle()).isEqualTo("-fx-text-box-border: red ; -fx-focus-color: red ;");
        robot.clickOn("#applyButton");
        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(1);
        Assertions.assertThat(umlClassManager.getUmlClass("Class").getFields().size()).isEqualTo(0);
    }

    @Test
    void testEmptyMethodBox(FxRobot robot){
        robot.clickOn("#textBoxClassName");
        robot.write("Class");
        robot.clickOn("#addMethodButton");

        TextField textBoxMethodName = (TextField) testScene.lookup("#textBoxMethodName");
        Assertions.assertThat(textBoxMethodName.getStyle()).isEqualTo("-fx-text-box-border: red ; -fx-focus-color: red ;");
        robot.clickOn("#applyButton");

        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(1);
        Assertions.assertThat(umlClassManager.getUmlClass("Class").getMethods().size()).isEqualTo(0);
    }

    @Test
    void testAddBadClassName(FxRobot robot) {
        robot.clickOn("#textBoxClassName");
        robot.write("@Class");
        robot.clickOn("#applyButton");
        TextField textBoxClassName = (TextField) testScene.lookup("#textBoxClassName");

        Assertions.assertThat(textBoxClassName.getStyle()).isEqualTo("-fx-text-box-border: red ; -fx-focus-color: red ;");
        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(0);
    }

    @Test
    void testAddBadFieldName(FxRobot robot) {
        robot.clickOn("#textBoxFieldName");
        robot.write("@Field");
        robot.clickOn("#addFieldButton");
        TextField textBoxFieldName = (TextField) testScene.lookup("#textBoxFieldName");

        Assertions.assertThat(textBoxFieldName.getStyle()).isEqualTo("-fx-text-box-border: red ; -fx-focus-color: red ;");
    }

    @Test
    void testAddBadFieldNameThenGoodFieldName(FxRobot robot){
        robot.clickOn("#textBoxFieldName");
        robot.write("@Field");
        robot.clickOn("#addFieldButton");
        TextField textBoxFieldName = (TextField) testScene.lookup("#textBoxFieldName");

        Assertions.assertThat(textBoxFieldName.getStyle()).isEqualTo("-fx-text-box-border: red ; -fx-focus-color: red ;");

        robot.clickOn("#textBoxFieldName");
        textBoxFieldName.clear();
        robot.write("Field");
        robot.clickOn("#addFieldButton");

        Assertions.assertThat(textBoxFieldName.getStyle()).isEqualTo("-fx-text-box-border: #9A9A9A; -fx-focus-color: darkslateblue;");
        Assertions.assertThat(textBoxFieldName.getText()).isEqualTo("");
    }

    @Test
    void testAddBadMethodName(FxRobot robot) {
        robot.clickOn("#textBoxMethodName");
        robot.write("@Method");
        robot.clickOn("#addMethodButton");
        TextField textBoxClassName = (TextField) testScene.lookup("#textBoxMethodName");

        Assertions.assertThat(textBoxClassName.getStyle()).isEqualTo("-fx-text-box-border: red ; -fx-focus-color: red ;");
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

        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(1);

        Field field = umlClassManager.getUmlClass("Class").getField("Field");
        Assertions.assertThat(field).isNotNull();
        Assertions.assertThat(field.getName()).isEqualTo("Field");
        Assertions.assertThat(field.getType()).isEqualTo("Type");

        // Assertions.assertThat(robot.lookup("#fieldDisplay").queryAs(Lis
        // tView.class)).hasListCell(field);
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

        Assertions.assertThat(umlClassManager.listUmlClasses().size()).isEqualTo(1);

        Method method = umlClassManager.getUmlClass("Class").getMethod("Method");
        Assertions.assertThat(method).isNotNull();
        Assertions.assertThat(method.getName()).isEqualTo("Method");
        Assertions.assertThat(method.getType()).isEqualTo("ReturnType");
        Assertions.assertThat(method.getParameterTypes().size()).isEqualTo(1);
        Assertions.assertThat(method.getParameterTypes()).contains("ParamType");
    }
}
