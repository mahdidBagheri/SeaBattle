package User.Model;

import Connection.Server.ServerConnection;
import Game.Model.Board;

public class Player {
    User user;
    ServerConnection connection;
    Board board = new Board();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServerConnection getConnection() {
        return connection;
    }

    public void setConnection(ServerConnection connection) {
        this.connection = connection;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
