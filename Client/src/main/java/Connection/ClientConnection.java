package Connection;

import Connection.Exceptions.CouldNotConnectToServerException;
import Interfaces.Constants;

import java.io.*;
import java.net.Socket;

public class ClientConnection {
    Socket socket;

    public ClientConnection() throws IOException {
        this.socket = new Socket("localhost", Constants.portNumber);
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


    public void execute(ClientRequest clientRequest) {

    }

}
