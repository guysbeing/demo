import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class server {
    public static Map<Integer, DataOutputStream> userOutputStreams = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            int userNumber = 1; // 计数器，用于用户编号

            while (true) {
                Socket socket = serverSocket.accept();
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                // 将用户编号和输出流存储到Map中
                userOutputStreams.put(userNumber, out);

                // 创建一个新的线程来处理客户端的连接
                ServerSocketThread socketThread = new ServerSocketThread(socket, userNumber);
                socketThread.start();
                userNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将消息从发送方转发给接收方
    public static void sendMessageToUser(int senderUserNumber, int receiverUserNumber, String message) {
        DataOutputStream receiverOutputStream = userOutputStreams.get(receiverUserNumber);

        try {
            receiverOutputStream.writeUTF("User " + senderUserNumber + " says: " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerSocketThread extends Thread {
    private Socket socket;
    private int userNumber;

    private int otherNumber;
    ServerSocketThread(Socket socket, int userNumber) {
        this.socket = socket;
        this.userNumber = userNumber;
        this.otherNumber= userNumber%2+1;
        System.out.println(otherNumber);
    }

    public void run() {
        try (DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner scan = new Scanner(System.in)) {

            while (true) {
                // 从客户端接收消息
                String message = in.readUTF();
                System.out.println("User " + userNumber + ": " + message);

                // 将消息转发给其他用户
                server.sendMessageToUser(userNumber, otherNumber, message);

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}