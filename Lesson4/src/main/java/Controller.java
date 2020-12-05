import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class Controller {

    @FXML
    TextArea chatTextArea;
    @FXML
    TextField sendTextField;
    @FXML
    Button sendButton;

    public void cliclOnExititem(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void clickOnSendButton(ActionEvent actionEvent) {
        if(!sendTextField.getText().isEmpty()) {
        chatTextArea.appendText(sendTextField.getText() + "\n");
        sendTextField.clear();
        }
    }


    public void textTyped(KeyEvent keyEvent) {

        if (keyEvent.isControlDown()) {
            sendTextField.appendText("\n");
        }
    }
}
