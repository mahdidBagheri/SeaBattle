package Connection.Server;

import Connection.Client.ClientConnection;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Utils.ClientWaitForInput;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerListener {
    ClientConnection clientConnection;

    public ServerListener(ClientConnection clientConnection){
        this.clientConnection = clientConnection;
    }

    public ServerRequest listen() throws IOException, CouldNotConnectToServerException, ClassNotFoundException {
        ClientWaitForInput.waitForInput(clientConnection.getSocket());
        ObjectInputStream objectInputStream = new ObjectInputStream(clientConnection.getSocket().getInputStream());

        return (ServerRequest) objectInputStream.readObject();
    }
}
