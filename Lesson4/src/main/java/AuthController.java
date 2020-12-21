import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthController {
    Alert alert;
    @FXML
    public TextField login;
    @FXML
    public PasswordField password;

//    NetChat netChat;
    Stage[] stage;


//    public void setNetChat(NetChat netChat) {
//        this.netChat = netChat;
//    }
    public void setStage (Stage ... stage) {
        this.stage = stage;
    }

    public boolean isInputCorrect (){
       return  ((login.getText().length() > 0) || (password.getText().length() > 0));
    }
    public boolean isAuthOk (){
        return Controller.client.isAuthorization();
    }

    public void entrance(ActionEvent event) {
        alert = new Alert(Alert.AlertType.WARNING);

            try {
                if (isInputCorrect()) {
                    Controller.client.getOutputMessage().writeUTF("/auth," + this.login.getText().trim() + "," + this.password.getText().trim());

                    login.clear();
                    password.clear();
                    if (isAuthOk()){
                        stage[0].close();
                        NetChat.startPrimary();
                    }

                }
                else {
                    alert.initOwner(stage[0]);
                    alert.setHeaderText("Ошибка авторизации");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


}
