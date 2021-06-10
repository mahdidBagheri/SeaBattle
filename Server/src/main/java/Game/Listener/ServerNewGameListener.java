package Game.Listener;

import Connection.Client.ClientRequest;
import Connection.Server.ServerConnection;
import User.Controller.UserController;
import User.Model.User;

import java.sql.SQLException;

public class ServerNewGameListener {
    ServerConnection serverConnection;

    public ServerNewGameListener(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public void listen(ClientRequest clientRequest) throws SQLException {
        insertIntoOnlineUsers(clientRequest.getUsername());
        checkIfThereIsAnyMatch();
    }

    private void insertIntoOnlineUsers(String username) throws SQLException {
        User mainUser = new User();
        UserController mainUserController = new UserController(mainUser);

        String sql = String.format("insert into \"UsersTable\" insert (\"UserUUID\") values ('%s')",mainUser.getUsername());
        serverConnection.getConnectionToDataBase().executeUpdate(sql);
    }

    public void
}
