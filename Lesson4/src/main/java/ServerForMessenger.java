import java.io.*;
import java.net.*;
import java.util.*;

public class ServerForMessenger {

    private int SERVER_PORT = 2021;
    private boolean isConnect = true;
    private Scanner scanner = new Scanner(System.in);
    private LinkedList<Server> allUsers = new LinkedList<>();
    private Thread tread = new Thread();

    public ServerForMessenger() {
//        this.SERVER_PORT = SERVER_PORT;

    }

    public void startServer() {
        System.out.println(Thread.currentThread().getName());
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            Thread sendMsgTo = new Thread(this::sendMsgToUsers);
            sendMsgTo.start();

            while (isConnect) {
                try {
                    Socket socket = serverSocket.accept();
                    allUsers.add(new Server(socket));

                } catch (IOException e) {
                    e.printStackTrace();
                    isConnect = false;
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Соединение разорвано");
        }
    }

    private void sendMsgToUsers() {
        while (isConnect) {
            String msgToUsers = scanner.nextLine();
            for (Server s : allUsers) {
                try {
                    s.sendMsg(s.getOutputStream(), msgToUsers);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }


    public LinkedList<Server> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(LinkedList<Server> allUsers) {
        this.allUsers = allUsers;
    }

}


class Server extends Thread {

    private Socket socket;
    private boolean isConnect = true;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Server(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        start();
    }


    public void run() {

        while (isConnect) {
            try {
                getMsg(inputStream);
            } catch (IOException e) {
                System.out.println("Клиент разорвал соединение");
                isConnect = false;
                return;
            }
        }

    }

    public void getMsg(DataInputStream inputStream) throws IOException {

        String messageFromUser = inputStream.readUTF();
        System.out.println(currentThread().getName() + ": " + messageFromUser);

    }

    public void sendMsg(DataOutputStream outputStream, String msgToUsers) throws IOException {

        outputStream.writeUTF(msgToUsers + "\n");

    }


    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

}


class ServerApp {

    public static void main(String[] args) {
        ServerForMessenger serverForMessenger = new ServerForMessenger();
        serverForMessenger.startServer();
    }

}