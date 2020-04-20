package projectzero.fx.controllers;

import javafx.collections.MapChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ParentViewController implements Initializable {
    private final UmlClassManager umlClassManager;
    private UmlClass selectedUmlClass;

    @FXML
    private Pane pane;

    @FXML
    private MenuItem fileSaveMenuItem;

    @FXML
    private MenuItem fileLoadMenuItem;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    public ParentViewController(UmlClassManager umlClassManager) {
        this.umlClassManager = umlClassManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.fileSaveMenuItem.setOnAction(event -> this.handleOnSaveClick());
        this.fileLoadMenuItem.setOnAction(event -> this.handleOnLoadClick());
        this.addButton.setOnAction(event -> this.handleOnAddClick());
        this.editButton.setOnAction(event -> this.handleOnEditClick());
        this.deleteButton.setOnAction(event -> this.handleDeleteClick());

        umlClassManager.getUmlClassMap().addListener((MapChangeListener<String, UmlClass>) change -> {
            if (change.wasAdded()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UmlClassNodeView.fxml"));

                    UmlClassNodeViewController umlClassNodeViewController = new UmlClassNodeViewController(change.getValueAdded());
                    fxmlLoader.setController(umlClassNodeViewController);

                    VBox umlClassNodeVBox = fxmlLoader.load();
                    umlClassNodeVBox.setId(change.getKey());
                    umlClassNodeVBox.getStylesheets().add(getClass().getResource("/css/UmlClassNode.css").toExternalForm());

                    umlClassNodeVBox.setTranslateX(change.getValueAdded().getX());
                    umlClassNodeVBox.setTranslateY(change.getValueAdded().getY());

                    umlClassNodeVBox.setOnMouseClicked(event -> this.setSelectedUMLClass(this.umlClassManager.getUmlClass(umlClassNodeVBox.getId())));
                    pane.getChildren().add(umlClassNodeVBox);
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                    System.exit(0);
                }
            } else if (change.wasRemoved()) {
                Node node = pane.getChildren().stream().filter(n -> n.getId().equals(change.getKey())).findFirst().get();
                pane.getChildren().remove(node);
            }
        });
    }

    @FXML
    private void handleOnSaveClick() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Yaml", "*.yaml", "*.yml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(null);

        if (file == null)
            return;

        try {
            umlClassManager.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOnLoadClick() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Yaml", "*.yaml", "*.yml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(null);

        if (file == null)
            return;

        try {
            umlClassManager.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOnAddClick() {
        loadClassScreenView(null);
    }

    @FXML
    private void handleOnEditClick() {
        if (selectedUmlClass != null) {
            this.loadClassScreenView(selectedUmlClass);
        }
    }

    @FXML
    private void handleDeleteClick() {
        if (selectedUmlClass != null) {
            umlClassManager.deleteUmlClass(selectedUmlClass.getName());
        }
    }

    private void setSelectedUMLClass(UmlClass umlClass) {
        selectedUmlClass = umlClass;
    }

    private void loadClassScreenView(UmlClass umlClass) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ClassScreenView.fxml"));

            ClassScreenViewController classScreenViewController = new ClassScreenViewController(umlClass, this.umlClassManager);
            fxmlLoader.setController(classScreenViewController);

            GridPane gridPane = fxmlLoader.load();

            Scene scene = new Scene(gridPane);
            scene.getStylesheets().add(getClass().getResource("/css/ClassScreen.css").toExternalForm());

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initOwner(pane.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException exception) {
            System.out.println("Could not load ClassScreenView");
            System.exit(0);
        }
    }
}
