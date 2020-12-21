import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Button enter;
    @FXML
    public TextArea chatTextArea;
    @FXML
    public TextField sendTextField;
    @FXML
    public ListView<String> nickNames = new ListView<>();
    @FXML
    private Text userTextField;

    @FXML
    private Button authButton;

    private boolean isConnect = true;
    private boolean isAuth = false;
    public static Client client;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            nickNames.setItems(FXCollections.observableArrayList(NetChat.userList));
        try {
            client = new Client(this);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void clickOnSendButton(ActionEvent actionEvent) {
        Thread t = new Thread(() -> {
            try {
               client.sendMessage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "Поток в котором клиент отправляет сообщение");
        t.start();
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        Platform.exit();
        client.getSocket().close();
    }


    public TextArea getChatTextArea() {
        return chatTextArea;
    }

    public void setChatTextArea(TextArea chatTextArea) {
        this.chatTextArea = chatTextArea;
    }


    public TextField getSendTextField() {
        return sendTextField;
    }

    public void setSendTextField(TextField sendTextField) {
        this.sendTextField = sendTextField;
    }

    public Text getUserTextField() {
        return userTextField;
    }

    public void setUserTextField(Text userTextField) {
        this.userTextField = userTextField;
    }


    public Button getAuthButton() {
        return authButton;
    }

    public void setAuthButton(Button authButton) {
        this.authButton = authButton;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }




    public boolean isAuth() {
        return isAuth;
    }
}



