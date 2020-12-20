import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private int SERVER_PORT;
    private boolean isConnect = true;
    private final Scanner scanner = new Scanner(System.in);
    private LinkedList<Connection> allUsers = new LinkedList<>();

    public Server(int SERVER_PORT) {
        this.SERVER_PORT = SERVER_PORT;

    }

    public void startServer() {

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            Thread sendMsgTo = new Thread(this::sendMsgToUsers);
            sendMsgTo.start();

            while (isConnect) {
                try {
                    Socket socket = serverSocket.accept();
                    addUsers(socket);
                    Thread t = new Thread(this::removeUsers);
                    t.start();
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

    public synchronized void addUsers(Socket socket) throws IOException {
        allUsers.add(new Connection(socket));
    }

    public synchronized void removeUsers() {
        allUsers.removeIf(s -> !s.isConnectSocket());

    }

    private void sendMsgToUsers() {
        while (isConnect) {
            String msgToUsers = scanner.nextLine();
            for (Connection s : allUsers) {
                try {
                    s.sendMsg(s.getOutputStream(), msgToUsers);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }


    public LinkedList<Connection> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(LinkedList<Connection> allUsers) {
        this.allUsers = allUsers;
    }

}


class Connection extends Thread {

    private Socket socket;
    private boolean isConnectSocket = true;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        start();
    }


    public void run() {

        while (isConnectSocket) {
            try {
                getMsg(inputStream);
            } catch (IOException e) {
                System.out.println("Клиент разорвал соединение");
                currentThread().interrupt();
                isConnectSocket = false;
                return;
            }

        }

    }

    public void getMsg(DataInputStream inputStream) throws IOException {

        String messageFromUser = inputStream.readUTF();
        System.out.println(currentThread().getName() + ": " + messageFromUser);

    }

    public void sendMsg(DataOutputStream outputStream, String msgToUsers) throws IOException {
        if (isConnectSocket) {
            outputStream.writeUTF(msgToUsers + "\n");
        }
    }


    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public boolean isConnectSocket() {
        return isConnectSocket;
    }

    public void setConnectSocket(boolean connectSocket) {
        isConnectSocket = connectSocket;
    }
}


class ServerApp {

    public static void main(String[] args) {
        Server server = new Server(2021);
        server.startServer();
    }

}