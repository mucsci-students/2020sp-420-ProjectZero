package projectzero.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClassScreen extends Stage {
    private ListView<String> methodDisplay, fieldDisplay, relationshipDisplay;
    private ObservableList<String> methods, fields,relationships;
    private final double WIDTH=280,HEIGHT=500;
    private final String TITLE = "Class Mod Screen";

    public ClassScreen(){
        VBox root = new VBox();

        setupListViews();

        HBox classNameInfo = new HBox();
        classNameInfo.getChildren().addAll(new Label("Class name: "), new TextField());

        HBox methodInfo = new HBox();
        //Fix later to not accept whitespace stuff/error checking. Also add to classManager.
        Button methodAddButton = setupAddButton(methodInfo,methods);
        setupInfoField(methodInfo,"Method name: ", methodAddButton);

        HBox fieldInfo = new HBox();
        //Fix later to not accept whitespace stuff/error checking. Also add to classManager.
        Button fieldAddButton = setupAddButton(fieldInfo,fields);
        setupInfoField(fieldInfo,"Field name: ", fieldAddButton);

        HBox relationshipInfo = new HBox();
        ComboBox<String> cBox = new ComboBox<String>();
        cBox.getItems().addAll("Dude", "Pizza Guy", "Another dude");
        Button relationshipButton = setupAddButton(relationshipInfo,relationships, cBox);
        setupInfoField(relationshipInfo,"Relationships: ",cBox, relationshipButton);

        VBox displayInfo = new VBox();

        HBox hMethods = setupListViewWithMinus(methodDisplay,methods);
        HBox hFields = setupListViewWithMinus(fieldDisplay,fields);
        HBox hRelationships = setupListViewWithMinus(relationshipDisplay,relationships);

        displayInfo.getChildren().addAll(new Label("Methods:"),
                hMethods,new Label("Fields:"),hFields
                ,new Label("Relationships:"),hRelationships);

        HBox buttonChoices = new HBox();
        buttonChoices.getChildren().addAll(new Button("Apply"), new Button("Cancel"));

        root.getChildren().addAll(classNameInfo,methodInfo,fieldInfo,relationshipInfo,displayInfo,buttonChoices);
        Scene classScene = new Scene(root, WIDTH, HEIGHT);
        this.setScene(classScene);
        this.setTitle(TITLE);
    }

    private Button setupAddButton(HBox container, ObservableList<String> displayList){
        Button addButton = new Button("+");
        //Fix later to not accept whitespace stuff/error checking. Also add to classManager.
        addButton.setOnAction(e-> {
            displayList.add(((TextField)container.getChildren().get(1)).getText());
            ((TextField)container.getChildren().get(1)).setText("");
        });
        return addButton;
    }

    private Button setupAddButton(HBox container, ObservableList<String> displayList, ComboBox cBox){
        Button addButton = new Button("+");
        //Fix later to not accept whitespace stuff/error checking. Also add to classManager.
        addButton.setOnAction(e-> displayList.add((String)cBox.getSelectionModel().getSelectedItem()));
        return addButton;
    }

    private void setupInfoField(HBox container, String labelName, Button button){
        container.getChildren().addAll(new Label(labelName),new TextField(), button);
    }
    private void setupInfoField(HBox container, String labelName, ComboBox<String> cBox, Button button){
        container.getChildren().addAll(new Label(labelName),cBox,button);

    }

    private void setupListViews(){
        methodDisplay = new ListView<String>();
        fieldDisplay = new ListView<String>();
        relationshipDisplay = new ListView<String>();

        methods = FXCollections.observableArrayList();
        fields = FXCollections.observableArrayList();
        relationships = FXCollections.observableArrayList();

        methodDisplay.setItems(methods);
        fieldDisplay.setItems(fields);
        relationshipDisplay.setItems(relationships);
    }

    private HBox setupListViewWithMinus(ListView<String> display,ObservableList<String> displayList){
        HBox box = new HBox();
        Button minusButton = new Button("-");
        minusButton.setOnAction(e -> displayList.remove(display.getSelectionModel().getSelectedItem()));
        box.getChildren().addAll(display,minusButton);
        return box;
    }
}
