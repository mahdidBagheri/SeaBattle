package Game.Listener;

import Connection.Client.ClientRequest;
import Connection.Client.ClientThread;
import Connection.Server.ServerConnection;
import Game.Controller.ServerGameController;
import Game.Exceptions.NotAvailableUserException;
import Game.Model.OnlineGames;
import User.Controller.UserController;
import User.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerGameListener {
    ServerConnection serverConnection;
    OnlineGames onlineGames;

    public ServerGameListener(ServerConnection serverConnection, OnlineGames onlineGames) {
        this.serverConnection = serverConnection;
        this.onlineGames = onlineGames;

    }

    public void listen(ClientRequest clientRequest) throws SQLException, NotAvailableUserException, InterruptedException {
        User user = new User();
        UserController userController = new UserController(user);
        userController.readUserDataByUsername(clientRequest.getUsername());

        if(clientRequest.getCommand().equals("newGame")){
            ServerGameController serverGameController = checkForGameWaitingForUserToJoin();
            if(serverGameController != null){
                serverGameController.joinGame(serverConnection,user);
            }
            else {
                ServerGameController newServerGameController = new ServerGameController(serverConnection,user);
                onlineGames.addOnlineGame(newServerGameController);
                boolean shouldStart = serverGameController.waitForOtherUserToJoin();
                if(shouldStart){
                    serverGameController.initialize();
                }
                else {
                    System.out.println("could not find any user");
                }
            }
        }
    }

    private ServerGameController checkForGameWaitingForUserToJoin() {
        for (ServerGameController serverGameController : onlineGames.getOnlineGames()) {
            if(serverGameController.getUser2() == null){
                return serverGameController;
            }
        }
        return null;
    }




}
