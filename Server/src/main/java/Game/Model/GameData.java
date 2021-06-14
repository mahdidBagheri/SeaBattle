package Game.Model;

import User.Model.User;

import java.io.Serializable;

public class GameData implements Serializable {
    User user1;
    Board board1;

    User user2;
    Board board2;

    String turn;
    int timeLeft;

    public GameData(User user1, Board board1, User user2, Board board2, String turn, int timeLeft) {
        this.user1 = user1;
        this.board1 = board1;
        this.user2 = user2;
        this.board2 = board2;
        this.turn = turn;
        this.timeLeft = timeLeft;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public Board getBoard1() {
        return board1;
    }

    public void setBoard1(Board board1) {
        this.board1 = board1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Board getBoard2() {
        return board2;
    }

    public void setBoard2(Board board2) {
        this.board2 = board2;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
