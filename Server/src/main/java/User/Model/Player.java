package User.Model;

import Connection.Server.ServerConnection;
import Game.Model.Board;

public class Player {
    User user;
    ServerConnection connection;

    boolean back = false;
    boolean shuffle = false;
    boolean ready = false;

    int shuffleNum = 0;
    boolean isWinner= false;
    boolean isHited = false;
    boolean opponentReadyMessageSent = false;

    Board board = new Board();

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ServerConnection getConnection() {
        return connection;
    }

    public void setConnection(ServerConnection connection) {
        this.connection = connection;
    }

    public boolean isBack() {
        return back;
    }

    public void setBack(boolean back) {
        this.back = back;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public int getShuffleNum() {
        return shuffleNum;
    }

    public void setShuffleNum(int shuffleNum) {
        this.shuffleNum = shuffleNum;
    }

    public void decreaseShuffleNum(){
        shuffleNum--;
    }

    public boolean isHited() {
        return isHited;
    }

    public void setHited(boolean hited) {
        isHited = hited;
    }

    public boolean isOpponentReadyMessageSent() {
        return opponentReadyMessageSent;
    }

    public void setOpponentReadyMessageSent(boolean opponentReadyMessageSent) {
        this.opponentReadyMessageSent = opponentReadyMessageSent;
    }
}
