import Config.NetworkConfig.ServerNetworkConfig;
import Connection.Client.ClientThread;
import Game.Model.OnlineGames;
import Interfaces.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        OnlineGames onlineGames = new OnlineGames();

        try {
            serverSocket = new ServerSocket(Constants.portNumber);
        } catch (IOException e) {
            try {
                ServerNetworkConfig networkConfig = new ServerNetworkConfig();
                serverSocket = new ServerSocket(networkConfig.getPort());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }

        while (true) {

            try {
                Thread.sleep(100);


                System.out.println("waiting for client ...");
                Socket socket = serverSocket.accept();
                System.out.println("client connected");
                Thread clientThread = new ClientThread(socket, onlineGames);
                clientThread.start();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
