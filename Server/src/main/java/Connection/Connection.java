package Connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private ConnectionToDataBase connectionToDataBase;

    public Connection(Socket socket){
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

    }
}
