package Connection.Client;

import Config.NetWorkConfig.NetworkConfig;
import Connection.Server.ServerRequest;
import Connection.Utils.ClientWaitForInput;
import Connection.Exceptions.CouldNotConnectToServerException;
import Interfaces.Constants;

import java.io.*;
import java.net.Socket;

public class ClientConnection {
    Socket socket;

    public ClientConnection() {
        try {
            this.socket = new Socket("localhost", Constants.portNumber);
        } catch (IOException e) {

            try {
                NetworkConfig networkConfig = new NetworkConfig();

                this.socket = new Socket("localhost", networkConfig.getPort());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        System.out.println(socket);
    }

    public boolean executeBoolean(ClientRequest clientRequest) throws IOException, ClassNotFoundException, CouldNotConnectToServerException {
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(clientRequest);
        os.flush();
        oos.flush();

        ClientWaitForInput.waitForInput(socket);
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        ServerRequest serverRequest = (ServerRequest) objectInputStream.readObject();


        System.out.println(serverRequest.getCommand());
        if (serverRequest.getCommand().equals("true")) {
            return true;
        } else {
            return false;
        }

    }


    public void execute(ClientRequest clientRequest) throws IOException {
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(clientRequest);
        os.flush();
        oos.flush();
    }

    public Socket getSocket() {
        return socket;
    }
}
