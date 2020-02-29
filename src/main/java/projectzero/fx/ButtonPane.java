package projectzero.fx;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ButtonPane extends HBox {
    public static final double BUTTONWIDTH = 200;
    public static final double BUTTONHEIGHT = 100;

    private Button addClassButton;
    private Button editClassButton;
    private Button deleteClassButton;

    public ButtonPane(){
        initializeButtons();
    }

    private void initializeButtons(){
        addClassButton = new Button("Add Class");
        addClassButton.setMaxWidth(Double.MAX_VALUE);
        addClassButton.setOnAction(e -> new ClassScreen().show());

        editClassButton = new Button("Edit Class");
        editClassButton.setMaxWidth(Double.MAX_VALUE);

        deleteClassButton = new Button("Delete Class");
        deleteClassButton.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(addClassButton, Priority.ALWAYS);
        HBox.setHgrow(editClassButton, Priority.ALWAYS);
        HBox.setHgrow(deleteClassButton, Priority.ALWAYS);

        this.getChildren().addAll(addClassButton,editClassButton,deleteClassButton);
    }
}
