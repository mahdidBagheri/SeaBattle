import Connection.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        ServerSocket serverSocket;

        while (true) {
            try {
                serverSocket = new ServerSocket(9000);
                System.out.println("waiting for client ...");
                Socket socket = serverSocket.accept();
                System.out.println("client connected");
                Thread clientThread = new ClientThread(socket);
                clientThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
