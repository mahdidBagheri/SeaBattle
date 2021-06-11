package Game.Listener;

import Connection.Client.ClientRequest;
import Connection.Server.ServerConnection;
import User.Controller.UserController;
import User.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerNewGameListener {
    ServerConnection serverConnection;

    public ServerNewGameListener(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public void listen(ClientRequest clientRequest) throws SQLException {
        insertIntoOnlineUsers(clientRequest.getUsername());
        boolean thereIsMatch = checkIfThereIsAnyMatch();
        if(thereIsMatch){
            startNewGame();
        }
    }

    private void startNewGame() throws SQLException {
        String[] userUUIDs = findUsers();
        boolean isUser1Available = informNewGameToUser(userUUIDs[0]);
        boolean isUser2Available = informNewGameToUser(userUUIDs[1]);
        if(isUser1Available && isUser2Available){

        }
        else {

        }
    }

    private void insertIntoOnlineUsers(String username) throws SQLException {
        User mainUser = new User();
        UserController mainUserController = new UserController(mainUser);

        String sql = String.format("insert into \"UsersTable\" insert (\"UserUUID\") values ('%s')",mainUser.getUsername());
        serverConnection.getConnectionToDataBase().executeUpdate(sql);
    }

    public boolean checkIfThereIsAnyMatch() throws SQLException {
        String sql = String.format("select \"UserUUID\" from \"OnlineUsers\";");
        ResultSet rs = serverConnection.getConnectionToDataBase().executeQuery(sql);
        int users = 0;
        if(rs != null){
            if(rs.next()){
                users++;
            }
            if(rs.next()){
                users++;
            }
        }
        if(users == 2){
            return true;
        }
        return false;
    }
    public String[] findUsers() throws SQLException {
        String sql = String.format("select \"UserUUID\" from \"OnlineUsers\";");
        ResultSet rs = serverConnection.getConnectionToDataBase().executeQuery(sql);
        String[] userUUIDs = new String[2];

        if(rs != null){
            if(rs.next()){
                userUUIDs[0] = rs.getString(1);
            }
            if(rs.next()){
                userUUIDs[1] = rs.getString(1);
            }
        }
        return userUUIDs;
    }
}
