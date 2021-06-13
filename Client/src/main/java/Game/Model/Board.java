package Game.Model;

import java.io.Serializable;

public class Board implements Serializable {
    String[][] board = new String[10][10];

    public Board(){
        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 10 ; j++) {
                board[i][j] = "+0";
            }
        }
    }

    public String[][] getBoard() {
        return board;
    }


}
