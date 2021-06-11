package Connection.Server;

import Connection.Client.ClientRequest;
import Connection.DataCaseConnection.ConnectionToDataBase;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Utils.ServerWaitForInput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerConnection {
    private Socket socket;
    private ConnectionToDataBase connectionToDataBase;

    public ServerConnection(Socket socket){
        this.socket = socket;
        this.connectionToDataBase = new ConnectionToDataBase();
    }

    public Socket getSocket() {
        return socket;
    }

    public ConnectionToDataBase getConnectionToDataBase() {
        return connectionToDataBase;
    }

    public void execute(ServerRequest serverRequest) throws IOException {
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(serverRequest);
        oos.flush();
        os.flush();
    }

    public boolean executeBoolean(ServerRequest serverRequest) throws IOException,
            CouldNotConnectToServerException, ClassNotFoundException {

        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(serverRequest);
        os.flush();
        oos.flush();

        ServerWaitForInput.waitForInput(socket);
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();


        System.out.println(serverRequest.getCommand());
        if (clientRequest.getCommand().equals("true")) {
            return true;
        } else {
            return false;
        }

    }
}
