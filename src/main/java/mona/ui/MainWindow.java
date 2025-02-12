package mona.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mona.Mona;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Mona mona;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image monaImage = new Image(this.getClass().getResourceAsStream("/images/MonaImage.png"));

    /**
     * Called automatically by JavaFX when the FXML file is loaded.
     * It sets up the scroll pane to scroll to the bottom whenever the dialog container's height changes.
     * It also adds the initial greeting message to the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getMonaDialog(Mona.greet(), monaImage)
        );
    }

    /** Injects the Mona instance */
    public void setMona(Mona m) {
        mona = m;
    }

    /**
     * Processes user input by creating dialog boxes for the user input and Mona's response.
     * Adds these dialog boxes to the dialog container and clears the user input field.
     * Exits the application if the user input is "bye".
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mona.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMonaDialog(response, monaImage)
        );
        userInput.clear();
    }
}
