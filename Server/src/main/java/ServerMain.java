import Connection.Client.ClientThread;
import Interfaces.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(Constants.portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

            try {
                Thread.sleep(100);


                System.out.println("waiting for client ...");
                Socket socket = serverSocket.accept();
                System.out.println("client connected");
                Thread clientThread = new ClientThread(socket);
                clientThread.start();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
