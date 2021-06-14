package User.Model;

import Connection.Server.ServerConnection;
import Game.Model.Board;

public class Player {
    User user;
    ServerConnection connection;

    boolean back = false;
    boolean shuffle = false;
    boolean ready = false;

    Board board = new Board();

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
}
