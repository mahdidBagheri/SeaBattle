package Game.Listener;

import Connection.Client.ClientRequest;
import Connection.Client.ClientThread;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerConnection;
import Game.Controller.ServerGameController;
import Game.Exceptions.NotAvailableUserException;
import Game.Model.OnlineGames;
import User.Controller.UserController;
import User.Model.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerGameListener {
    ServerConnection serverConnection;
    volatile OnlineGames onlineGames;

    public ServerGameListener(ServerConnection serverConnection, OnlineGames onlineGames) {
        this.serverConnection = serverConnection;
        this.onlineGames = onlineGames;

    }

    public void listen(ClientRequest clientRequest) throws SQLException, NotAvailableUserException, InterruptedException, ClassNotFoundException, IOException, CouldNotConnectToServerException {
        User user = new User();
        UserController userController = new UserController(user);
        userController.readUserDataByUsername(clientRequest.getUsername());

        if(clientRequest.getCommand().equals("newGame")){
            serverConnection.getSocket().setKeepAlive(true);
            ServerGameController joinServerGameController = JoinConnectionLostGameIfExist(clientRequest.getUsername());
            if(joinServerGameController != null){
                joinServerGameController.reconnect(serverConnection,user);
            }
            else {
                ServerGameController serverGameController = checkForGameWaitingForUserToJoin();
                if (serverGameController != null) {
                    serverGameController.joinGame(serverConnection, user);
                    synchronized (onlineGames) {
                        onlineGames.wait();
                    }
                    serverGameController.user2StartListening();

                } else {
                    ServerGameController newServerGameController = new ServerGameController(serverConnection, user);
                    onlineGames.addOnlineGame(newServerGameController);
                    boolean shouldStart = newServerGameController.waitForOtherUserToJoin();
                    if (shouldStart) {
                        newServerGameController.initialize();
                        synchronized (onlineGames) {
                            onlineGames.notify();
                        }
                        newServerGameController.startGame();
                        newServerGameController.user1StartListening();
                        if(newServerGameController.isFinished()){
                            onlineGames.removeOnlineGame(newServerGameController);
                            int a = 0;
                        }
                    } else {
                        System.out.println("could not find any user");
                    }
                }
            }
        }
    }

    private ServerGameController JoinConnectionLostGameIfExist(String userName) {
        for (ServerGameController serverGameController : onlineGames.getOnlineGames()) {
            if(serverGameController.getPlayer1() != null &&serverGameController.getPlayer1().getUser().getUsername().equals(userName)){
                return serverGameController;
            }
            else if(serverGameController.getPlayer2() != null && serverGameController.getPlayer2().getUser().getUsername().equals(userName)){
                return serverGameController;
            }
        }

        return null;
    }

    private ServerGameController checkForGameWaitingForUserToJoin() {
        for (ServerGameController serverGameController : onlineGames.getOnlineGames()) {
            if(serverGameController.getPlayer2() == null){
                return serverGameController;
            }
        }
        return null;
    }




}
