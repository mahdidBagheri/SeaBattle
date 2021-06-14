package Game.Threads;

import Game.Controller.BoardController;
import Game.Controller.ServerGameController;

import java.io.IOException;

public class ServerGameThread extends Thread {
    ServerGameController serverGameController;
    public ServerGameThread(ServerGameController serverGameController) {
        this.serverGameController = serverGameController;
    }

    @Override
    public void run() {
        try {
            startTimer(30);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void startTimer(int sec) throws InterruptedException, IOException {
        while ( sec > 0){

            Thread.sleep(100);
            if(serverGameController.getPlayer1().isReady() && serverGameController.getPlayer2().isReady()){
                break;
            }
            if(serverGameController.getPlayer1().isReady()){

            }
            if(serverGameController.getPlayer2().isReady()){

            }
            if(serverGameController.getPlayer1().isShuffle()){
                serverGameController.getPlayer1().setShuffle(false);
                BoardController boardController = new BoardController(serverGameController.getPlayer1().getBoard());
                boardController.shuffle();
                boardController.saveBoard();
                serverGameController.sendGameData();
            }
            if(serverGameController.getPlayer2().isShuffle()){
                serverGameController.getPlayer2().setShuffle(false);
                BoardController boardController = new BoardController(serverGameController.getPlayer2().getBoard());
                boardController.shuffle();
                //TODO implement saveBoard
                boardController.saveBoard();
                serverGameController.sendGameData();
            }
            if(serverGameController.getPlayer1().isBack()){

            }
            if(serverGameController.getPlayer2().isBack()){

            }



        }
    }
}
