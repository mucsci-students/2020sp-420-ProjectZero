package projectzero.fx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import projectzero.core.*;
import projectzero.core.exceptions.InvalidNameException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClassScreenViewController implements Initializable {
    private final UmlClass existingUmlClass;
    private final UmlClassManager umlClassManager;

    private final ObservableList<Field> fields;
    private final ObservableList<Method> methods;
    private final ObservableList<Relationship> relationships;

    @FXML
    Button addFieldButton,
            addMethodButton,
            addRelationshipButton,
            removeFieldButton,
            removeMethodButton,
            removeRelationshipButton,
            applyButton,
            cancelButton;

    @FXML
    private GridPane gridPane;

    @FXML
    private TextField textBoxClassName,
            textBoxMethodName,
            textBoxMethodReturnType,
            textBoxMethodParamType,
            textBoxFieldName,
            textBoxFieldType;

    @FXML
    private ComboBox<String> comboMethodParameterTypes,
            comboRelationshipToName;

    @FXML
    private ComboBox<Relationship.Type> comboRelationshipType;

    @FXML
    private ListView<Method> methodDisplay;

    @FXML
    private ListView<Field> fieldDisplay;

    @FXML
    private ListView<Relationship> relationshipDisplay;

    public ClassScreenViewController(UmlClass umlClass, UmlClassManager umlClassManager) {
        this.existingUmlClass = umlClass;
        this.umlClassManager = umlClassManager;

        if (existingUmlClass == null) {
            this.fields = FXCollections.observableArrayList();
            this.methods = FXCollections.observableArrayList();
            this.relationships = FXCollections.observableArrayList();
        } else {
            this.fields = FXCollections.observableArrayList(existingUmlClass.getFields());
            this.methods = FXCollections.observableArrayList(existingUmlClass.getMethods());
            this.relationships = FXCollections.observableArrayList(existingUmlClass.getRelationships());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.addFieldButton.setOnAction(event -> handleOnAddFieldClick());
        this.addMethodButton.setOnAction(event -> handleOnAddMethodClick());
        this.addRelationshipButton.setOnAction(event -> handleOnAddRelationshipClick());
        this.removeFieldButton.setOnAction(event -> handleOnRemoveFieldClick());
        this.removeMethodButton.setOnAction(event -> handleOnRemoveMethodClick());
        this.removeRelationshipButton.setOnAction(event -> handleOnRemoveRelationshipClick());
        this.applyButton.setOnAction(event -> applyUMLClass());
        this.cancelButton.setOnAction(event -> handleOnCancelClick());
        this.textBoxMethodParamType.setOnAction(event -> this.addParamType());

        if (existingUmlClass != null) {
            this.textBoxClassName.setText(existingUmlClass.getName());
        }

        setupListViews();
        fillRelationships();
    }

    @FXML
    private void handleOnAddFieldClick() {
        try {
            Field.Builder builder = new Field.Builder();
            Field field = builder
                    .withName(textBoxFieldName.getText())
                    .withType(textBoxFieldType.getText())
                    .build();

            this.fields.add(field);

            textBoxFieldName.setStyle("-fx-text-box-border: #9A9A9A; -fx-focus-color: darkslateblue;");
            textBoxFieldName.setText("");
            textBoxFieldType.setText("");
        } catch (InvalidNameException ex) {
            textBoxFieldName.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
        }

    }

    @FXML
    private void handleOnAddMethodClick() {
        try {
            Method.Builder builder = new Method.Builder();
            Method method = builder
                    .withName(textBoxMethodName.getText())
                    .withType(textBoxMethodReturnType.getText())
                    .withParameterTypes(new ArrayList<>(comboMethodParameterTypes.getItems()))
                    .build();

            this.methods.add(method);

            textBoxMethodName.setStyle("-fx-text-box-border: #9A9A9A; -fx-focus-color: darkslateblue;");
            textBoxMethodName.setText("");
            textBoxMethodReturnType.setText("");
            comboMethodParameterTypes.getItems().removeAll(comboMethodParameterTypes.getItems());
        } catch (InvalidNameException ex) {
            textBoxMethodName.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
        }
    }

    @FXML
    private void handleOnAddRelationshipClick() {
        Relationship.Builder builder = new Relationship.Builder();
        Relationship relationship = builder
                .withTo(umlClassManager.getUmlClass(comboRelationshipToName.getSelectionModel().getSelectedItem()).getName())
                .withType(comboRelationshipType.getSelectionModel().getSelectedItem())
                .build();

        this.relationships.add(relationship);

        comboRelationshipToName.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleOnRemoveFieldClick() {
        this.fields.remove(fieldDisplay.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleOnRemoveMethodClick() {
        this.methods.remove(methodDisplay.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleOnRemoveRelationshipClick() {
        this.relationships.remove(relationshipDisplay.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void addParamType() {
        comboMethodParameterTypes.getItems().add(textBoxMethodParamType.getText());
        textBoxMethodParamType.setText("");
    }

    @FXML
    public void applyUMLClass() {
        try {
            UmlClass umlClass = new UmlClass(textBoxClassName.getText());
            this.fields.forEach(umlClass::addField);
            this.methods.forEach(umlClass::addMethod);
            this.relationships.forEach(umlClass::addRelationship);

            if (existingUmlClass == null) {
                umlClassManager.addUmlClass(umlClass);
            } else {
                umlClassManager.updateUmlClass(existingUmlClass.getName(), umlClass);
            }

            textBoxClassName.setStyle("-fx-text-box-border: #9A9A9A; -fx-focus-color: darkslateblue;");
            fieldDisplay.setStyle("-fx-border-color: #9A9A9A; -fx-focus-color: darkslateblue;");

            Stage stage = (Stage) (this.gridPane.getScene().getWindow());
            stage.close();
        } catch (InvalidNameException e) {
            textBoxClassName.setStyle(" -fx-text-box-border: #ff0000 ; -fx-focus-color: red ;");
        }
    }

    @FXML
    private void handleOnCancelClick() {
        Stage stage = (Stage) this.gridPane.getScene().getWindow();
        stage.close();
    }

    private void fillRelationships() {
        umlClassManager.listUmlClasses().forEach(umlClass -> comboRelationshipToName.getItems().add(umlClass.getName()));
    }

    private void setupListViews() {
        fieldDisplay.setItems(this.fields);
        methodDisplay.setItems(this.methods);
        relationshipDisplay.setItems(this.relationships);

        comboRelationshipType.getItems().addAll(Relationship.Type.values());

        setupMethodParamsCombo();
    }

    private void setupMethodParamsCombo() {
        comboMethodParameterTypes.setCellFactory(lv ->
                new ListCell<>() {
                    final HBox comboParamsLayout;

                    {
                        Label paramLabel = new Label();
                        paramLabel.textProperty().bind(itemProperty());
                        paramLabel.setMaxWidth(Double.POSITIVE_INFINITY);
                        paramLabel.setOnMouseClicked(event -> comboMethodParameterTypes.hide());

                        Button buttonParamTypeMinus = new Button("-");
                        buttonParamTypeMinus.setOnAction(event -> {
                            String paramType = getItem();
                            comboMethodParameterTypes.getItems().remove(paramType);
                        });
                        comboParamsLayout = new HBox(paramLabel, buttonParamTypeMinus);
                        HBox.setHgrow(paramLabel, Priority.ALWAYS);
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(comboParamsLayout);
                        }
                    }
                }
        );
        comboMethodParameterTypes.setSkin(new ComboBoxListViewSkin<String>(comboMethodParameterTypes) {

            @Override
            public void hide() {
                this.hideOnClickProperty().setValue(false);
            }

        });
    }
}
