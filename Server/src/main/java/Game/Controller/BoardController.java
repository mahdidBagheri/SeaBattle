package Game.Controller;

import Game.Model.Board;

import java.util.Random;

public class BoardController {
    Board board;

    public BoardController(Board board){
        this.board = board;
    }

    public void readBoard(){

    }

    public void hit(int x, int y){

    }

    public void shuffle(){
        clear();
        ///BattleShip
        locateShip('a',4);

        //Cruiser
        locateShip('b',3);
        locateShip('c',3);

        //Destroyer
        locateShip('d',2);
        locateShip('e',2);
        locateShip('f',2);

        //Frigate
        locateShip('g',1);
        locateShip('h',1);
        locateShip('i',1);
        locateShip('j',1);

        int a = 0;

    }

    private void clear() {
        for (int i = 0; i <10 ; i++) {
            for (int j = 0; j <10 ; j++) {
                board.getBoard()[i][j] = "+0";
            }
        }
    }

    private void locateShip(Character symbol, int length) {
        Random random = new Random();
        int orientation = 0;
        int x = 0;
        int y = 0;
        do {
            orientation = random.nextInt(2);
            if (orientation == 0) {
                //horizantal
                x = random.nextInt(9 - length + 1);
                y = random.nextInt(9);
            } else if (orientation == 1) {
                //vertical
                x = random.nextInt(9);
                y = random.nextInt(9 - length + 1);
            }
        }while (!isPossible(orientation, x, y, length));

        for (int i = 0; i <length ; i++) {
            if(orientation == 0){
                board.getBoard()[y][x+i] = "+" + symbol;
            }
            else if(orientation == 1){
                board.getBoard()[y+i][x] = "+" + symbol;
            }
        }


    }



    private boolean isPossible(int orientation, int x, int y, int length) {
        if(orientation == 0){
            for (int i = -1; i <length+1 ; i++) {
                for (int j = -1; j <= +1; j++) {
                    if(x+i >= 0 &&x+i<=9 && j+y>=0 && j+y<=9 ){
                        if(!board.getBoard()[y + j][x + i].equals("+0")){
                            return false;
                        }
                    }
                }

            }
        }
        else if(orientation == 1){
            for (int i = -1; i <length+1 ; i++) {
                for (int j = -1; j <= +1; j++) {
                    if(x + j >= 0 &&x + j<=9 && y + i>=0 && y + i<=9 ){
                        if(!board.getBoard()[y + i][x + j].equals("+0")){
                            return false;
                        }
                    }
                }

            }
        }
        return true;
    }

    public void printBoard(){
        for (int i = 0; i <10 ; i++) {
            System.out.println("\n");
            for (int j = 0; j <10 ; j++) {
                System.out.print(board.getBoard()[i][j] + "   ");
            }
        }
        System.out.println("\n");
    }


    public void saveBoard() {

    }
}
