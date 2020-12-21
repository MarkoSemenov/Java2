import javafx.collections.FXCollections;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private DataInputStream inputMessage;
    private DataOutputStream outputMessage;
    private Socket socket;
    private final Controller controller;
    private boolean isConnect = true;
    public volatile static boolean authorization = false;
    public String nick;


    public Client(Controller controller) throws IOException {

        this.controller = controller;

        try {
            socket = new Socket("localhost", 2022);
            outputMessage = new DataOutputStream(socket.getOutputStream());
            inputMessage = new DataInputStream(socket.getInputStream());
            connectServer();

        } catch (IOException e) {
            System.out.println("Сервер не доступен");
            e.printStackTrace();
        }

    }


    public void connectServer() throws IOException {
        System.out.println("Соединение установлено: " + socket.isConnected());
        Thread readThread = new Thread(() -> {
            while (true) {
                try {
                    if (!authorization) {
                        authentication();
                    }

                    getMessage();



                } catch (IOException e) {
                    System.out.println("Соединение разорвано");
                    isConnect = false;
                    return;
                }
            }
        });
        readThread.start();

    }

    public synchronized void getMessage() throws IOException {
        String getMsg = inputMessage.readUTF();
        if (getMsg.startsWith("/clients ")) {
            String[] getNicksFromServer = getMsg.split(" ");
            controller.list = FXCollections.observableArrayList(getNicksFromServer);
            controller.list.remove(0);
            controller.nickNames.setItems(controller.list);
        } else {
            controller.chatTextArea.appendText(getMsg);
        }
    }


    public void sendMessage() throws IOException {
        if (isConnect) {
            String msgToServer = controller.sendTextField.getText();
            outputMessage.writeUTF(msgToServer);
            controller.sendTextField.clear();
        }
    }

    public void authentication() throws IOException {

        while (true) {
            String str = inputMessage.readUTF();
            if (str.startsWith("Success")) {
                setAuthorization(true);
                return;
            }
        }
    }


    public Client getClient() {
        return this;
    }

    public String getNick() {
        return nick;
    }


    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    public DataInputStream getInputMessage() {
        return inputMessage;
    }

    public void setInputMessage(DataInputStream inputMessage) {
        this.inputMessage = inputMessage;
    }

    public DataOutputStream getOutputMessage() {
        return outputMessage;
    }

    public void setOutputMessage(DataOutputStream outputMessage) {
        this.outputMessage = outputMessage;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }


    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization(boolean authorization) {
        Client.authorization = authorization;
    }


}