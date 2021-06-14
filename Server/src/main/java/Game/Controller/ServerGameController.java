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

    Player turn;
    double timeLeft;



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

    public void initialize() throws NotAvailableUserException, IOException, CouldNotConnectToServerException, ClassNotFoundException, SQLException, InterruptedException {
        boolean isBothAreAvailable = checkUsersAvailability();
        if(!isBothAreAvailable){
            throw new NotAvailableUserException("User not available");
        }

        turn = player1;
        timeLeft = 30;
        insertGameToDataBase();
        shuffleBoards();

        sendGameData("FirstGameData");
        int a = 0;

    }

    public void sendGameData(String command) throws IOException {
        sendGameDataToUser(player1, command);
        sendGameDataToUser(player2, command);

    }

    private void sendGameDataToUser(Player player, String command) throws IOException {
        ServerPayLoad serverPayLoad = new ServerPayLoad();
        GameData gameData = null;
        if(player == player1){
            Player selfPlayer = player1;
            Player opponent = new Player();
            User userOpponent = new User();
            userOpponent.setUsername(player2.getUser().getUsername());
            opponent.setUser(userOpponent);

            modifyBoardDataForOpponent(opponent, player2.getBoard());

            gameData = new GameData(selfPlayer.getUser(),selfPlayer.getBoard(),opponent.getUser(),opponent.getBoard(),turn.getUser().getUsername(),(int)timeLeft);
        }
        else if(player == player2){
            Player selfPlayer = player2;
            Player opponent = new Player();
            User userOpponent = new User();
            userOpponent.setUsername(player1.getUser().getUsername());
            opponent.setUser(userOpponent);

            modifyBoardDataForOpponent(opponent, player1.getBoard());

            gameData = new GameData(selfPlayer.getUser(),selfPlayer.getBoard(),opponent.getUser(),opponent.getBoard(),turn.getUser().getUsername(),(int)timeLeft);

        }
        else {
            System.out.println("sth some where went wrong");
        }
        serverPayLoad.setGameData(gameData);

        serverPayLoad.setGameData(gameData);
        ServerRequest serverRequest = new ServerRequest(player.getUser().getUsername(),command,serverPayLoad);
        player.getConnection().execute(serverRequest);
    }


    private void modifyBoardDataForOpponent(Player opponent, Board board) {
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 10 ; j++) {
                if(board.getBoard()[i][j].charAt(0) == '+'){
                    opponent.getBoard().getBoard()[i][j] = "+0";
                }
            }
        }
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
        GameThreadClientListener gameThreadClientListener = new GameThreadClientListener(this,player1);
        gameThreadClientListener.run();
    }

    public void user2StartListening() {
        GameThreadClientListener gameThreadClientListener = new GameThreadClientListener(this,player2);
        gameThreadClientListener.run();
    }

    public Player getTurn() {
        return turn;
    }

    public void sendGameStartMessage(Player player) throws IOException {
        ServerPayLoad payLoad = new ServerPayLoad();
        payLoad.getStringStringHashMap().put("turn",turn.getUser().getUsername().equals(player.getUser().getUsername()) ? "true" : "false");
        ServerRequest serverRequest = new ServerRequest(player.getUser().getUsername(),"GameStarted",payLoad);
        player.getConnection().execute(serverRequest);
    }

    public double getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
    }
}
