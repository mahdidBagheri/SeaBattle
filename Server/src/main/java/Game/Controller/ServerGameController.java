package Game.Controller;

import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerConnection;
import Connection.Server.ServerRequest;
import Game.Exceptions.NotAvailableUserException;
import Game.Threads.GameThreadClientListener;
import Game.Threads.ServerGameThread;
import Interfaces.Constants;
import User.Model.Player;
import User.Model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

public class ServerGameController {

    Player player1;
    Player player2;

    boolean continueWaitingForUserToJoin = true;


    public ServerGameController(ServerConnection serverConnection,User user) throws SQLException, NotAvailableUserException {
        this.player1 = new Player();
        this.player1.setUser(user);
        this.player1.setConnection(serverConnection);

    }

    public boolean waitForOtherUserToJoin() throws InterruptedException {
        long start = System.currentTimeMillis();

        while (continueWaitingForUserToJoin && player2 == null && System.currentTimeMillis() - start < Constants.timeWaitForUsersToJoin){

            Thread.sleep(100);
        }
        if(player2 != null){
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

        createTables();
        shuffleBoard();
        startTimer();


    }

    private void createTables() {

        String sql1 = String.format("create table \"")
    }

    public boolean checkUsersAvailability() throws CouldNotConnectToServerException, IOException, ClassNotFoundException {
        boolean isUser1Available = informNewGameToUser(player1);
        boolean isUser2Available = informNewGameToUser(player2);
        if(isUser1Available && isUser2Available){
            return true;
        }
        else {
            return false;
        }
    }

    private boolean informNewGameToUser(Player player) throws ClassNotFoundException, IOException, CouldNotConnectToServerException {
        ServerRequest serverRequest = new ServerRequest(player.getUser().getUsername(),"connectionCheck",null);
        return player.getConnection().executeBoolean(serverRequest);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void joinGame(ServerConnection serverConnection, User user) {
        this.player2 = new Player();
        player2.setUser(user);
        player2.setConnection(serverConnection);

    }

    public void startGame() {
        ServerGameThread serverGameThread = new ServerGameThread(this);
        serverGameThread.start();
    }

    public void user1StartListening() {
        GameThreadClientListener gameThreadClientListener = new GameThreadClientListener(player1);
        gameThreadClientListener.run();
    }

    public void user2StartListening() {
        GameThreadClientListener gameThreadClientListener = new GameThreadClientListener(player2);
        gameThreadClientListener.run();
    }


}
