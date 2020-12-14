import java.io.*;
import java.net.*;
import java.util.*;

public class MyServer {

    private final int SERVER_PORT = 2021;
    private boolean isConnect = true;

    public MyServer() {

    }

    public void startServer() {

        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            socket = serverSocket.accept();

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            Thread read = new Thread(() -> {
                while (true) {
                    try {
                        getMsg(inputStream);
                    } catch (IOException e) {
                        System.out.println("Клиент разорвал соединение");
                        isConnect = false;
                        return;
                    }
                }
            }, "read");
            read.start();

            while (isConnect) {
                sendMsg(outputStream);
            }

        } catch (IOException e) {
            System.out.println("Соединение разорвано");

        }
    }


    public void getMsg(DataInputStream inputStream) throws IOException{

        String messageFromUser = inputStream.readUTF();
        System.out.println(messageFromUser);

    }

    public void sendMsg(DataOutputStream outputStream) throws IOException{

        Scanner scanner = new Scanner(System.in);
        String msgFromUser = scanner.nextLine();
        outputStream.writeUTF(msgFromUser + "\n");
        outputStream.flush();

    }

}


class MyServerStart{

    public static void main(String[] args) {
        MyServer myServer = new MyServer();
        myServer.startServer();
    }
}