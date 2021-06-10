package ServerLogin.Listener;

import Connection.Client.ClientRequest;
import Connection.Server.ServerConnection;
import ServerLogin.Controller.ServerLoginController;

import java.io.IOException;
import java.sql.SQLException;

public class ServerLoginListener {
    ServerConnection serverConnection;
    public ServerLoginListener(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public void listen(ClientRequest clientRequest) throws SQLException, IOException {
        if(clientRequest.getCommand().equals("matchUsernameAndPassword")){
            ServerLoginController serverLoginController = new ServerLoginController(serverConnection);
            serverLoginController.matchUsernameAndPassword(clientRequest);
        }
        else if(clientRequest.getCommand().equals("login")){
            ServerLoginController serverLoginController = new ServerLoginController(serverConnection);
            serverLoginController.login(clientRequest);
        }

    }
}
