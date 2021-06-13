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
                else if(serverRequest.getCommand().equals("GameData")){
                    gameController.addOpponentGamePanel();
                    gameController.applyGameData(serverRequest.getPayLoad().getGameData());
                    gameController.opponentFound();
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
