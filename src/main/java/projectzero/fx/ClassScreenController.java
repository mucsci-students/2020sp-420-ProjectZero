package projectzero.fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import projectzero.core.*;
import projectzero.core.exceptions.InvalidNameException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class ClassScreenController implements Initializable {
    protected ObservableList<Method> methods;
    protected ObservableList<Field> fields;
    protected ObservableList<Relationship> relationships;

    protected UmlClassManager mainManager;
    @FXML
    protected TextField textBoxClassName,
                        textBoxMethodName,
                        textBoxMethodReturnType,
                        textBoxParamType,
                        textBoxFieldName,
                        textBoxFieldType;
    @FXML
    protected ComboBox<String>  comboMethodParameterTypes,
                                comboRelationshipToName;
    @FXML
    protected ComboBox<Relationship.Type> comboRelationshipType;
    @FXML
    protected ListView<Method>  methodDisplay;
    @FXML
    protected ListView<Field>   fieldDisplay;
    @FXML
    protected ListView<Relationship>   relationshipDisplay;

    public void addMethod(ActionEvent e) {
        try {
            methods.add(new Method.Builder().withName(textBoxMethodName.getText())
                            .withType(textBoxMethodReturnType.getText())
                            .withParameterTypes(new ArrayList<String>(comboMethodParameterTypes.getItems()))
                            .build());
            textBoxMethodName.setStyle("-fx-text-box-border: #9A9A9A; -fx-focus-color: darkslateblue;");
            textBoxMethodName.setText("");
            textBoxMethodReturnType.setText("");
            comboMethodParameterTypes.getItems().removeAll(comboMethodParameterTypes.getItems());
        } catch (InvalidNameException ex) {
            textBoxMethodName.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
        }
    }

    public void addField(ActionEvent e) {
        try {
            fields.add(new Field.Builder().withName(textBoxFieldName.getText())
                            .withType(textBoxFieldType.getText())
                            .build());
            textBoxFieldName.setStyle("-fx-text-box-border: #9A9A9A; -fx-focus-color: darkslateblue;");
            textBoxFieldName.setText("");
            textBoxFieldType.setText("");
        } catch (InvalidNameException ex) {
            textBoxFieldName.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
        }

    }

    public void addRelationship(ActionEvent e) {
        relationships.add(new Relationship.Builder()
                            .withTo(mainManager.getUmlClass(comboRelationshipToName.getSelectionModel().getSelectedItem()))
                            .withType(comboRelationshipType.getSelectionModel().getSelectedItem()).build());
        comboRelationshipToName.getSelectionModel().clearSelection();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupListViews();
        fillRelationships();
    }

    public void minusMethod(ActionEvent e) {
        methods.remove(methodDisplay.getSelectionModel().getSelectedItem());
    }

    public void minusField(ActionEvent e) {
        fields.remove(fieldDisplay.getSelectionModel().getSelectedItem());
    }

    public void minusRelationship(ActionEvent e) {
        relationships.remove(relationshipDisplay.getSelectionModel().getSelectedItem());
    }
    public void addParamType(ActionEvent e){
        comboMethodParameterTypes.getItems().add(textBoxParamType.getText());
        textBoxParamType.setText("");
    }
    public abstract void applyUMLClass(ActionEvent event);

    public void setUMLClassManager(UmlClassManager mainManager){
        this.mainManager = mainManager;
    }

    protected void fillRelationships(){
        mainManager.listUmlClasses().forEach(umlClass -> comboRelationshipToName.getItems().add(umlClass.getName()));
    }

    protected void updateComboBoxRelationships(UmlClass umlClass){
        comboRelationshipToName.getItems().add(umlClass.getName());
    }

    private void setupListViews() {
        methods = FXCollections.observableArrayList();
        fields = FXCollections.observableArrayList();
        relationships = FXCollections.observableArrayList();

        methodDisplay.setItems(methods);
        fieldDisplay.setItems(fields);
        relationshipDisplay.setItems(relationships);

        comboRelationshipType.getItems().addAll(Relationship.Type.values());

        setupMethodParamsCombo();
    }

    private void setupMethodParamsCombo(){
        comboMethodParameterTypes.setCellFactory(lv ->
            new ListCell<String>(){
                HBox comboParamsLayout;
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
                    comboParamsLayout = new HBox(paramLabel,buttonParamTypeMinus);
                    comboParamsLayout.setHgrow(paramLabel, Priority.ALWAYS);
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
    protected void resetData(){
        textBoxClassName.setText("");
        textBoxMethodName.setText("");
        textBoxMethodReturnType.setText("");
        textBoxParamType.setText("");
        textBoxFieldName.setText("");
        textBoxFieldType.setText("");
        comboMethodParameterTypes.getItems().removeAll(comboMethodParameterTypes.getItems());
        comboMethodParameterTypes.getSelectionModel().clearSelection();
        comboRelationshipToName.getSelectionModel().clearSelection();
        comboRelationshipType.getSelectionModel().clearSelection();
        methods.removeAll(methods);
        fields.removeAll(fields);
        relationships.removeAll(relationships);
    }
}
