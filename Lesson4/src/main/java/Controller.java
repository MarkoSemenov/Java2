import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Controller {

    @FXML
    public TextArea chatTextArea;
    @FXML
    public TextField sendTextField;

    @FXML
    public static Text userTextField;
    private boolean isConnect = true;
    private final ClientSocket clientSocket = new ClientSocket();

    public void clickOnSendButton(ActionEvent actionEvent) {

        Thread t = new Thread(() -> {
            try {
                clientSocket.sendMessageToServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        t.start();

    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
        try {
            clientSocket.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void textTyped(KeyEvent keyEvent) {

//        if (sendTextField.getText().length() == 10) {
//            sendTextField.setText(sendTextField.getText() + "\n");
//        }
    }


    public TextArea getChatTextArea() {
        return chatTextArea;
    }

    public void setChatTextArea(TextArea chatTextArea) {
        this.chatTextArea = chatTextArea;
    }


    public class ClientSocket {

        private DataInputStream inputMessage;
        private DataOutputStream outputMessage;
        private Socket socket;


        public ClientSocket() {

            try {
                connectServer();
            } catch (IOException e) {
                System.out.println("Сервер не доступен");
                e.printStackTrace();
            }

        }

        public void connectServer() throws IOException {
            socket = new Socket("localhost", 2021);
            System.out.println(socket.isConnected());
            Thread readThread = new Thread(() -> {
                while (true) {
                    try {
                        getMessageFromServer();
                    } catch (IOException e) {
                        System.out.println("Соединение разорвано");
                        isConnect = false;
                        return;
                    }
                }
            });
            readThread.start();
        }

        public void getMessageFromServer() throws IOException {
            inputMessage = new DataInputStream(socket.getInputStream());
            String getMsg = inputMessage.readUTF();
            chatTextArea.appendText("Сервер: " + getMsg);
        }

        public void sendMessageToServer() throws IOException {
            if (isConnect) {
                outputMessage = new DataOutputStream(socket.getOutputStream());
                String msgFromServer = sendTextField.getText();
                outputMessage.writeUTF(msgFromServer);
                outputMessage.flush();
            }
            chatTextArea.appendText(sendTextField.getText() + "\n");
            sendTextField.clear();

        }

    }
}



