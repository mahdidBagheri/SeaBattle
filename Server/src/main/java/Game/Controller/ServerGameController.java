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
    public static ServerGameController instance = null;

    Player player1;
    Player player2;

    boolean continueWaitingForUserToJoin = true;

    Player turn;
    double timeLeft = 30;
    boolean isFinished = false;

    public ServerGameController(ServerConnection serverConnection,User user) throws SQLException, NotAvailableUserException {
        this.player1 = new Player();
        this.player1.setUser(user);
        this.player1.setConnection(serverConnection);
        instance = this;

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

            if(turn == player1){
                gameData = new GameData(selfPlayer.getUser(),selfPlayer.getBoard(),opponent.getUser(),opponent.getBoard(),"true",(int)timeLeft);
            }
            else {
                gameData = new GameData(selfPlayer.getUser(),selfPlayer.getBoard(),opponent.getUser(),opponent.getBoard(),"false",(int)timeLeft);
            }
        }
        else if(player == player2){
            Player selfPlayer = player2;
            Player opponent = new Player();
            User userOpponent = new User();
            userOpponent.setUsername(player1.getUser().getUsername());
            opponent.setUser(userOpponent);


            modifyBoardDataForOpponent(opponent, player1.getBoard());
            if(turn == player2){
                gameData = new GameData(selfPlayer.getUser(),selfPlayer.getBoard(),opponent.getUser(),opponent.getBoard(),"true",(int)timeLeft);
            }
            else {
                gameData = new GameData(selfPlayer.getUser(),selfPlayer.getBoard(),opponent.getUser(),opponent.getBoard(),"false",(int)timeLeft);
            }

        }
        else {
            System.out.println("sth some where went wrong");
        }
        serverPayLoad.setGameData(gameData);
        serverPayLoad.getStringStringHashMap().put("Winner",Boolean.toString(player.isWinner()));
        ServerRequest serverRequest = new ServerRequest(player.getUser().getUsername(),command,serverPayLoad);
        player.getConnection().execute(serverRequest);
    }


    private void modifyBoardDataForOpponent(Player opponent, Board board) {
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 10 ; j++) {
                if(board.getBoard()[i][j].charAt(0) == '+'){
                    opponent.getBoard().getBoard()[i][j] = "+0";
                }
                if(board.getBoard()[i][j].charAt(0) == '-'){
                    opponent.getBoard().getBoard()[i][j] = board.getBoard()[i][j];
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
        boolean isUser1Available = connectionCheck(player1);
        boolean isUser2Available = connectionCheck(player2);
        if(isUser1Available && isUser2Available){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean connectionCheck(Player player) throws ClassNotFoundException, IOException, CouldNotConnectToServerException {
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


    public double getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(double timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void switchTurn(){
        if(turn == player1){
            turn = player2;
        }
        else if(turn == player2){
            turn = player1;
        }
    }

    public void increaseTimeLeftByShuffle(Player player) {
        if(player == player1){
            if(player1.getShuffleNum() >= player2.getShuffleNum()){
                timeLeft += 10;
            }

        }
        else if(player == player2){
            if(player2.getShuffleNum() >= player1.getShuffleNum()){
                timeLeft += 10;
            }
        }
    }

    public boolean hit(int x, int y, Player player) {
        BoardController boardController = null;
        if(player == player1){
            boardController = new BoardController(player2.getBoard());
        }
        else if(player == player2) {
            boardController = new BoardController(player1.getBoard());
        }

        boolean isAHit =  boardController.hit(x,y);

        isFinished = checkWinner(player1) || checkWinner(player2);
        System.out.println("is finished:" + isFinished);
        return isAHit;
    }

    private boolean checkWinner(Player player) {
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j <10 ; j++) {
                if(player.getBoard().getBoard()[i][j].charAt(1) != '0' && player.getBoard().getBoard()[i][j].charAt(0) == '+' ){
                    return false;
                }
            }
        }
        if(player == player1){
            player2.setWinner(true);
        }
        else {
            player2.setWinner(false);
        }
        return true;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void sendReadyMessage(Player player) throws IOException {
        ServerRequest serverRequest = new ServerRequest(player.getUser().getUsername(),"opponentReady",null);
        player.getConnection().execute(serverRequest);
    }

    public void connectionLostProtocol(Player player) throws IOException {
        player.setConnection(null);
        sendOpponentLostConnectionMessageToOtherPlayer(player);
        waitForPlayerToJoin(player);
    }

    private void waitForPlayerToJoin(Player player) {
        Thread waitToJoin = new Thread(new Runnable() {
            double timer = 60;
            @Override
            public void run() {
                while (timer > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                    if(player.getConnection() != null){
                        GameThreadClientListener gameThreadClientListener = new GameThreadClientListener(instance,player);
                        gameThreadClientListener.start();
                        break;
                    }
                }
                if(timer <= 0){
                    if(player == player1){
                        player2.setWinner(true);
                        isFinished = true;
                    }
                    else {
                        player1.setWinner(true);
                        isFinished = true;
                    }
                }
            }
        });
        waitToJoin.run();
    }

    private void sendOpponentLostConnectionMessageToOtherPlayer(Player player) throws IOException {
        if(player == player1){
            ServerRequest serverRequest = new ServerRequest(player2.getUser().getUsername(),"opponentConnectionLost",null);
            player2.getConnection().execute(serverRequest);
        }
        else {
            ServerRequest serverRequest = new ServerRequest(player1.getUser().getUsername(),"opponentConnectionLost",null);
            player1.getConnection().execute(serverRequest);
        }
    }


    public void reconnect(ServerConnection serverConnection, User user) {
        if(player1.getUser().getUsername().equals(user.getUsername())){
            player1.setConnection(serverConnection);
            user1StartListening();
        }
        else {
            player2.setConnection(serverConnection);
            user2StartListening();
        }
    }
}
