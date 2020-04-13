package projectzero.fx;

import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import projectzero.core.UmlClass;
import projectzero.core.UmlClassManager;

import java.io.File;
import java.io.IOException;

public class ParentViewController {
    private UmlClassManager umlClassManager;
    private UmlClass selectedUmlClass;
    @FXML
    private Pane pane;

    public void initialize(UmlClassManager umlClassManager) {
        this.umlClassManager = umlClassManager;

        umlClassManager.getUmlClassMap().addListener((MapChangeListener<String, UmlClass>) change -> {
            if (change.wasAdded()) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UmlClassNodeView.fxml"));

                    UmlClassNodeViewController umlClassNodeViewController = new UmlClassNodeViewController(change.getValueAdded());
                    fxmlLoader.setController(umlClassNodeViewController);

                    VBox umlClassNodeVBox = fxmlLoader.load();
                    umlClassNodeVBox.setId(change.getKey());
                    umlClassNodeVBox.getStylesheets().add(getClass().getResource("/css/UmlClassNode.css").toExternalForm());

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

    public void addButtonClick() {
        new ClassScreen(umlClassManager, "").show();
    }

    public void editButtonClick(ActionEvent event) {
        if (selectedUmlClass != null)
            new ClassScreen(umlClassManager, selectedUmlClass.getName()).show();
    }

    public void deleteButtonClick(ActionEvent event) {
        if (selectedUmlClass != null) {
            umlClassManager.deleteUmlClass(selectedUmlClass.getName());
        }
    }

    public void setSelectedUMLClass(UmlClass umlClass) {
        selectedUmlClass = umlClass;
    }

    public void handleOnSaveClick() {
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

    public void handleOnLoadClick() {
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
}
