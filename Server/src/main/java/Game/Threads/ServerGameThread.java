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
            startTimer();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void startTimer() throws InterruptedException, IOException {
        while (!serverGameController.isFinished()) {
            while (serverGameController.getTimeLeft() > 0) {

                Thread.sleep(100);
                serverGameController.setTimeLeft(serverGameController.getTimeLeft() - 0.1);

                if(serverGameController.isFinished()){
                    break;
                }
                if (serverGameController.getPlayer1().isReady() && serverGameController.getPlayer2().isReady()) {
                    break;
                }
                if (serverGameController.getPlayer1().isReady() && !serverGameController.getPlayer2().isOpponentReadyMessageSent()) {
                    serverGameController.getPlayer2().setOpponentReadyMessageSent(true);
                    serverGameController.sendReadyMessage(serverGameController.getPlayer2());
                }
                if (serverGameController.getPlayer2().isReady() && !serverGameController.getPlayer1().isOpponentReadyMessageSent()) {
                    serverGameController.getPlayer1().setOpponentReadyMessageSent(true);
                    serverGameController.sendReadyMessage(serverGameController.getPlayer1());

                }
                if (serverGameController.getPlayer1().isShuffle()) {
                    serverGameController.getPlayer1().setShuffle(false);
                    BoardController boardController = new BoardController(serverGameController.getPlayer1().getBoard());
                    boardController.shuffle();
                    boardController.saveBoard();
                    serverGameController.sendGameData("shuffle");
                }
                if (serverGameController.getPlayer2().isShuffle()) {
                    serverGameController.getPlayer2().setShuffle(false);
                    BoardController boardController = new BoardController(serverGameController.getPlayer2().getBoard());
                    boardController.shuffle();
                    //TODO implement saveBoard
                    boardController.saveBoard();
                    serverGameController.sendGameData("shuffle");
                }
                if (serverGameController.getPlayer1().isBack()) {

                }
                if (serverGameController.getPlayer2().isBack()) {

                }
                if (serverGameController.getPlayer1().isHited()) {
                    serverGameController.getPlayer1().setHited(false);
                }
                if (serverGameController.getPlayer2().isHited()) {
                    serverGameController.getPlayer2().setHited(false);
                }


            }
            if (serverGameController.getTimeLeft() <= 0 || (serverGameController.getPlayer1().isReady() && serverGameController.getPlayer2().isReady())) {
                serverGameController.getPlayer1().setReady(false);
                serverGameController.getPlayer2().setReady(false);
                serverGameController.switchTurn();
                serverGameController.setTimeLeft(25);
                serverGameController.sendGameData("GameData");
            }
        }
        if(serverGameController.isFinished() && (serverGameController.getPlayer1().isWinner() || serverGameController.getPlayer2().isWinner() )){
            serverGameController.sendGameData("GameFinished");
            synchronized (serverGameController){
                serverGameController.notifyAll();
            }
        }
    }
}
