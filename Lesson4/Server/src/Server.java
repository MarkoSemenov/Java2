import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Server {

    private int SERVER_PORT;
    private boolean isConnect = true;
    private final Scanner scanner = new Scanner(System.in);
    public static LinkedList<Connection> allConnections = new LinkedList<>();
    public List<String> names = new ArrayList<>();

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
                    new Connection(socket, this);
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


    public synchronized void addUsers(Connection o) throws IOException {
//        allConnections.add(new Connection(socket, this));
        allConnections.add(o);
    }

    public synchronized void removeUsers(Connection o) {
        allConnections.remove(o);
//        allConnections.removeIf(s -> !s.isConnectSocket());

    }

    public synchronized void broadcastMsg(String msg) throws IOException {
        for (Connection c : allConnections) {
            c.sendMsg(msg);
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (Connection o : allConnections) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void sndPersonalMsg(Connection from, String nickTo, String msg) throws IOException {
        for (Connection o : allConnections) {
            if (o.gtName().equals(nickTo)) {
                o.sendMsg(from.gtName() + ": " + msg);
                from.sendMsg(from.gtName() + ": " + msg);
                return;
            }
        }
        from.sendMsg("Участника " + nickTo + " нет в чате");
    }

    private void sendMsgToUsers() {
        while (isConnect) {
            String msgToUsers = scanner.nextLine();
            for (Connection connection : allConnections) {
                try {
                    connection.getOutputStream().writeUTF("Сервер:" + msgToUsers + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }


    public LinkedList<Connection> getAllConnections() {
        return allConnections;
    }

    public void setAllConnections(LinkedList<Connection> allConnections) {
        this.allConnections = allConnections;
    }

    public static void main(String[] args) {
        new Server(2022).startServer();
    }

}

