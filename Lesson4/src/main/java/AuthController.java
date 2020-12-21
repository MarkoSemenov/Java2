import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthController {
    Alert alert;
    @FXML
    public TextField login;
    @FXML
    public PasswordField password;

    Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isInputCorrect() {
        return ((login.getText().length() > 0) || (password.getText().length() > 0));
    }

    public boolean isAuthOk() {
        return Controller.client.isAuthorization();
    }

    public void entrance(ActionEvent event) {
        alert = new Alert(Alert.AlertType.WARNING);

        try {
            if (isInputCorrect()) {
                Controller.client.getOutputMessage().writeUTF("/auth," + this.login.getText().trim() + "," + this.password.getText().trim());
                login.clear();
                password.clear();
                if (isAuthOk()) {
                    stage.close();
                    NetChat.startPrimary();
                }

            } else {
                alert.initOwner(stage);
                alert.setHeaderText("Поля не заполнены");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
