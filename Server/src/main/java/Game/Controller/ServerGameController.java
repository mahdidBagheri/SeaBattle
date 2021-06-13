package Game.Controller;

import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerConnection;
import Connection.Server.ServerPayLoad;
import Connection.Server.ServerRequest;
import Game.Exceptions.NotAvailableUserException;
import Game.Model.Board;
import Game.Model.GameData;
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

    GameData gameData;

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

    public void initialize() throws NotAvailableUserException, IOException, CouldNotConnectToServerException, ClassNotFoundException, SQLException {
        boolean isBothAreAvailable = checkUsersAvailability();
        if(!isBothAreAvailable){
            throw new NotAvailableUserException("User not available");
        }

        insertGameToDataBase();
        shuffleBoards();

        sendGameData();
        startTimer();
        int a = 0;

    }

    private void startTimer() {
    }

    private void sendGameData() throws IOException {
        updateGameData();
        ServerPayLoad serverPayLoad = new ServerPayLoad();
        serverPayLoad.setGameData(gameData);
        ServerRequest serverRequest = new ServerRequest(player1.getUser().getUsername(),"GameData",serverPayLoad);
        player1.getConnection().execute(serverRequest);
        player2.getConnection().execute(serverRequest);

    }

    private void updateGameData() {
        this.gameData = new GameData(player1.getUser(), player1.getBoard(), player2.getUser(), player2.getBoard(), player1.getUser(),30);
    }

    private void shuffleBoards() {
        shuffleBoard(player1);
        shuffleBoard(player2);
    }

    private void shuffleBoard(Player player) {
        BoardController boardController = new BoardController(player.getBoard());
        boardController.shuffle();
    }

    private void insertGameToDataBase() throws SQLException {
        String board1 = createBoard(player1);
        String board2 = createBoard(player2);


        String sql3 = String.format("insert into \"GamesTable\"(\"UUID\",\"Player1\",\"Player2\",\"board1\",\"board2\")" +
                        " values (uuid_generate_v4(),'%s','%s','%s','%s');",player1.getUser().getUuid(),
                        player2.getUser().getUuid(),board1,board2);
        player1.getConnection().getConnectionToDataBase().executeUpdate(sql3);


    }

    public String createBoard(Player player) throws SQLException {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        String createBoard1 = String.format("create table \"board%s\" (\"Cells\" VARCHAR ( 5 ) NOT NULL);",uuidString);
        player.getConnection().getConnectionToDataBase().executeUpdate(createBoard1);

        String insertToTable1;
        for(int i = 0; i < 100; i++){
            insertToTable1 = String.format("insert into \"board%s\" (\"Cells\") values ('+0');",uuidString);
            player.getConnection().getConnectionToDataBase().executeUpdate(insertToTable1);
        }

        return "board" + uuidString;
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
