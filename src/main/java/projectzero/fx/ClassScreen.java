package projectzero.fx;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClassScreen extends Stage {
    private TextArea methodDisplay, fieldDisplay, relationshipDisplay;
    private final double WIDTH=280,HEIGHT=500;
    private final String TITLE = "Class Mod Screen";
    public ClassScreen(){
        VBox root = new VBox();
        methodDisplay = new TextArea();
        fieldDisplay = new TextArea();
        relationshipDisplay = new TextArea();

        HBox classNameInfo = new HBox();
        classNameInfo.getChildren().addAll(new Label("Class name: "), new TextField());

        HBox methodInfo = new HBox();
        //Fix later to not accept whitespace stuff/error checking. Also add to classManager.
        Button methodAddButton = setupAddButton(methodInfo,methodDisplay);
        setupInfoField(methodInfo,"Method name: ", methodAddButton);

        HBox fieldInfo = new HBox();
        //Fix later to not accept whitespace stuff/error checking. Also add to classManager.
        Button fieldAddButton = setupAddButton(fieldInfo,fieldDisplay);
        setupInfoField(fieldInfo,"Field name: ", fieldAddButton);

        HBox relationshipInfo = new HBox();
        ComboBox<String> cBox = new ComboBox<String>();
        cBox.getItems().addAll("Dude", "Pizza Guy", "Another dude");
        Button relationshipButton = setupAddButton(relationshipInfo,relationshipDisplay, cBox);
        setupInfoField(relationshipInfo,"Relationships: ",cBox, relationshipButton);

        VBox displayInfo = new VBox();

        displayInfo.getChildren().addAll(new Label("Methods:"),
                methodDisplay,new Label("Fields:"),fieldDisplay
                ,new Label("Relationships:"),relationshipDisplay);

        HBox buttonChoices = new HBox();
        buttonChoices.getChildren().addAll(new Button("Apply"), new Button("Cancel"));

        root.getChildren().addAll(classNameInfo,methodInfo,fieldInfo,relationshipInfo,displayInfo,buttonChoices);
        Scene classScene = new Scene(root, WIDTH, HEIGHT);
        this.setScene(classScene);
        this.setTitle(TITLE);
    }

    private Button setupAddButton(HBox container, TextArea display){
        Button addButton = new Button("+");
        //Fix later to not accept whitespace stuff/error checking. Also add to classManager.
        addButton.setOnAction(e-> {
            display.appendText(((TextField)container.getChildren().get(1)).getText()
                    + "\n");
            ((TextField)container.getChildren().get(1)).setText("");
        });
        return addButton;
    }

    private Button setupAddButton(HBox container, TextArea display, ComboBox cBox){
        Button addButton = new Button("+");
        //Fix later to not accept whitespace stuff/error checking. Also add to classManager.
        addButton.setOnAction(e-> {
            display.appendText(cBox.getSelectionModel().getSelectedItem()
                    + "\n");
        });
        return addButton;
    }

    private void setupInfoField(HBox container, String labelName, Button button){
        container.getChildren().addAll(new Label(labelName),new TextField(), button);
    }
    private void setupInfoField(HBox container, String labelName, ComboBox<String> cBox, Button button){
        container.getChildren().addAll(new Label(labelName),cBox,button);

    }
}
