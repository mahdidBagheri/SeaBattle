import Connection.ClientThread;
import Interfaces.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        ServerSocket serverSocket;

        while (true) {
            try {
                serverSocket = new ServerSocket(Constants.portNumber);
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
