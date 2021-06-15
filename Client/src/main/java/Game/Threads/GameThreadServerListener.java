package Game.Threads;

import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerRequest;
import Connection.Utils.ClientWaitForInput;
import Game.Controller.GameController;

import java.io.IOException;
import java.io.ObjectInputStream;

public class GameThreadServerListener extends Thread {
    GameController gameController;

    public GameThreadServerListener(GameController gameController){
        this.gameController = gameController;
    }

    @Override
    public void run() {
        boolean isRunning = true;

        while (isRunning){
            try {
                ClientWaitForInput.waitForInput(gameController.getClientConnection().getSocket());
                ObjectInputStream objectInputStream = new ObjectInputStream(gameController.getClientConnection().getSocket().getInputStream());
                ServerRequest serverRequest = (ServerRequest) objectInputStream.readObject();

                if(serverRequest.getCommand().equals("connectionCheck")){
                    gameController.AnswerCheckConnectionFromServer();
                }
                else if(serverRequest.getCommand().equals("FirstGameData")){
                    gameController.applyGameData(serverRequest.getPayLoad().getGameData());
                    gameController.opponentFound();
                    gameController.timer(serverRequest.getPayLoad().getGameData().getTimeLeft());

                }
                else if(serverRequest.getCommand().equals("GameData")){
                    if(!gameController.isStarted()){
                        gameController.start();
                        gameController.addOpponentGamePanel();
                    }
                    gameController.applyGameData(serverRequest.getPayLoad().getGameData());
                    gameController.setTurn(serverRequest.getPayLoad().getGameData().getTurn());
                    gameController.timer(serverRequest.getPayLoad().getGameData().getTimeLeft());
                }
                else if(serverRequest.getCommand().equals("shuffle")){
                    gameController.applyGameData(serverRequest.getPayLoad().getGameData());
                    gameController.timer(serverRequest.getPayLoad().getGameData().getTimeLeft());
                }
                else if(serverRequest.getCommand().equals("GameFinished")){
                    gameController.setFinished(true);
                    if(serverRequest.getPayLoad().getStringStringHashMap().get("Winner").equals("true")){
                        gameController.setWinner(true);
                    }else{
                        gameController.setWinner(false);
                    }
                    gameController.getOpponentGamePanel().getBoardPanel().setVisible(true);
                    gameController.getOpponentGamePanel().getBoardPanel().removeMouseListener();
                    gameController.getOpponentGamePanel().repaint();
                    gameController.showWinnerDialog();
                    gameController.back();

                }
                else if(serverRequest.getCommand().equals("opponentReady")){
                    gameController.readyOpponent();
                }

            } catch (CouldNotConnectToServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}
