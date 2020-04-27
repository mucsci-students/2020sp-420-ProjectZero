package projectzero.fx.controllers;

import javafx.collections.MapChangeListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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

        this.loadExistingUmlClasses();
        this.addListenerToUmlClassMap();
    }

    @FXML
    private void handleOnSaveClick() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter yamlExtensionFilter = new FileChooser.ExtensionFilter("Yaml", "*.yaml", "*.yml");
        FileChooser.ExtensionFilter pngExtensionFilter = new FileChooser.ExtensionFilter("Png", "*.png");
        fileChooser.getExtensionFilters().addAll(yamlExtensionFilter, pngExtensionFilter);

        File file = fileChooser.showSaveDialog(null);

        if (file == null)
            return;

        String fileName = file.getName();
        String extension;

        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return;
        }

        if (extension.equals("yaml") || extension.equals("yml")) {
            try {
                umlClassManager.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveAsPng(this.pane, file);
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
        if (selectedUmlClass == null) {
            this.pane.getChildren().stream().filter(node -> node.getId().equals(umlClass.getName())).findFirst().ifPresent(node -> {
                node.getStyleClass().add("selected");
            });
        } else {
            this.pane.getChildren().stream().filter(node -> node.getId().equals(selectedUmlClass.getName())).findFirst().ifPresent(node -> {
                node.getStyleClass().remove("selected");
            });
            this.pane.getChildren().stream().filter(node -> node.getId().equals(umlClass.getName())).findFirst().ifPresent(node -> {
                node.getStyleClass().add("selected");
            });
        }
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

    @FXML
    public void saveAsPng(Node node, File file) {
        WritableImage image = node.snapshot(new SnapshotParameters(), null);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
        }
    }

    private void loadExistingUmlClasses() {
        this.umlClassManager.getUmlClassMap().values().forEach(umlClass -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UmlClassNodeView.fxml"));

                UmlClassNodeViewController umlClassNodeViewController = new UmlClassNodeViewController(umlClass);
                fxmlLoader.setController(umlClassNodeViewController);

                VBox umlClassNodeVBox = fxmlLoader.load();
                umlClassNodeVBox.setId(umlClass.getName());
                umlClassNodeVBox.getStylesheets().add(getClass().getResource("/css/UmlClassNode.css").toExternalForm());

                umlClassNodeVBox.setOnMouseClicked(event -> this.setSelectedUMLClass(this.umlClassManager.getUmlClass(umlClassNodeVBox.getId())));

                pane.getChildren().add(umlClassNodeVBox);
            } catch (IOException ioException) {
                System.out.println(ioException.getMessage());
                System.exit(0);
            }
        });
    }

    private void addListenerToUmlClassMap() {
        this.umlClassManager.getUmlClassMap().addListener((MapChangeListener<String, UmlClass>) change -> {
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

                    umlClassNodeVBox.setOnMouseDragged(event -> {
                        Node node = (Node) event.getSource();

                        double newX = node.getTranslateX() + event.getX() - umlClassNodeVBox.getWidth() / 2;
                        double newY = node.getTranslateY() + event.getY() - umlClassNodeVBox.getHeight() / 2;

                        umlClassNodeVBox.setTranslateX(newX);
                        umlClassNodeVBox.setTranslateY(newY);

                        change.getValueAdded().setX(newX);
                        change.getValueAdded().setY(newY);
                    });

                    pane.getChildren().add(umlClassNodeVBox);

                    change.getValueAdded().getRelationships().forEach(relationship -> {
                        Line line = new Line();

                        line.setId("line_" + change.getKey() + "_" + relationship.getTo());

                        line.startXProperty().bind(change.getValueAdded().xProperty());
                        line.startYProperty().bind(change.getValueAdded().yProperty());

                        line.endXProperty().bind(umlClassManager.getUmlClass(relationship.getTo()).xProperty());
                        line.endYProperty().bind(umlClassManager.getUmlClass(relationship.getTo()).yProperty());

                        this.pane.getChildren().add(line);

                        line.toBack();
                    });
                } catch (IOException ioException) {
                    System.out.println(ioException.getMessage());
                    System.exit(0);
                }
            } else if (change.wasRemoved()) {
                // Remove Class Node
                pane.getChildren().stream().filter(n -> n.getId().equals(change.getKey())).findFirst().ifPresent(value -> pane.getChildren().remove(value));

                // Remove Lines
                List<Node> nodes = pane.getChildren().stream().filter(n -> n.getId().contains("line") && n.getId().contains(change.getKey())).collect(Collectors.toList());
                pane.getChildren().removeAll(nodes);
            }
        });
    }
}
