package Game.Controller;

import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerConnection;
import Connection.Server.ServerRequest;
import Game.Exceptions.NotAvailableUserException;
import Interfaces.Constants;
import User.Model.User;

import java.io.IOException;
import java.sql.SQLException;

public class ServerGameController {
    User user1;
    ServerConnection user1Connection;

    User user2;
    ServerConnection user2Connection;

    boolean continueWaiting = true;


    public ServerGameController(ServerConnection serverConnection,User user) throws SQLException, NotAvailableUserException {
        this.user1 = user;
        this.user1Connection = serverConnection;

    }

    public boolean waitForOtherUserToJoin() throws InterruptedException {
        long start = System.currentTimeMillis();

        while (continueWaiting && user2 == null && System.currentTimeMillis() - start < Constants.timeWaitForUsersToJoin){

            Thread.sleep(100);
        }
        if(user2 != null){
            return true;
        }
        else {
            return false;
        }
    }

    public void initialize() throws NotAvailableUserException, IOException, CouldNotConnectToServerException, ClassNotFoundException {
        boolean isBothAreAvailable = checkUsersAvailability();
        if(!isBothAreAvailable){
            throw new NotAvailableUserException("User not available");
        }

        System.out.println("Create game!");
    }

    public boolean checkUsersAvailability() throws CouldNotConnectToServerException, IOException, ClassNotFoundException {
        boolean isUser1Available = informNewGameToUser(user1Connection, user1);
        boolean isUser2Available = informNewGameToUser(user2Connection, user2);
        if(isUser1Available && isUser2Available){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean informNewGameToUser(ServerConnection userConnection, User user) throws ClassNotFoundException, IOException, CouldNotConnectToServerException {
        ServerRequest serverRequest = new ServerRequest(user.getUsername(),"connectionCheck",null);
        return userConnection.executeBoolean(serverRequest);
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public void joinGame(ServerConnection serverConnection, User user) {
        this.user2 = user;
        this.user2Connection = serverConnection;
    }

    public void startGame() throws InterruptedException {
        Thread.sleep(120000);
    }
}
