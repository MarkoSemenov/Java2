import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;


class Connection extends Thread {

    private final Server server;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    User user = new User("Mark", "log1", "pas1");
    User user1 = new User("Bill", "log2", "pas2");
    User user2 = new User("Ann", "log3", "pas3");
    private final List<User> base = List.of(user, user1, user2);
    private volatile boolean isConnectSocket = true;
    private volatile boolean authorization = false;
    private String name;

    public Connection(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        if (!authorization) {
            authentication();
        }
        System.out.println(name + " авторизован");
        start();
    }


    public void run() {

        while (isConnectSocket) {
            try {
                getMessage();
            } catch (IOException e) {
                System.out.println("Клиент разорвал соединение");
                server.removeUsers(this);
                isConnectSocket = false;
                return;
            }

        }

    }


    public String gtName() {
        return name;
    }


    public void authentication() {

        while (true) {
            try {
                String auth = inputStream.readUTF();
                if (auth.startsWith("/auth")) {
                    String[] s = auth.split(",");
                    System.out.println(Arrays.toString(s));
                    for (User client : base) {
                        if ((client.getLogin().equals(s[1])) && (client.getPassword().equals(s[2]))) {
                            name = client.getNickName();
                            if (!server.isNickBusy(name)) {
                                sendMsg("Success" + " " + name);
                                server.broadcastMsg(name + " зашел в чат...");
                                server.addUsers(this);
                                authorization = true;
                                return;
                            } else {
                            sendMsg("denied");
                            }
                        } else outputStream.writeUTF("denied");
                    }
                }

            } catch (IOException e) {
                System.out.println("Соединение прервано");
                e.printStackTrace();
                return;

            }
        }
    }


    public void getMessage() throws IOException {
        String messageFromUser = inputStream.readUTF();
        if (messageFromUser.startsWith("/w")) {
            String[] str = messageFromUser.split(" ");
            String nick = str[1];
            String msg = messageFromUser.substring(4 + nick.length());
            server.sndPersonalMsg(this, nick, msg);
        } else {
            System.out.println(name + ": " + messageFromUser);
            server.broadcastMsg(name + ": " + messageFromUser);
        }

    }


//    public void getMsg(DataInputStream inputStream) throws IOException {
//        String messageFromUser = inputStream.readUTF();
//        System.out.println(currentThread().getName() + ": " + messageFromUser);
//    }

    public void sendMsg(String msgToUsers) throws IOException {
        if (isConnectSocket) {
            outputStream.writeUTF(msgToUsers + "\n");
        }
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

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }


}