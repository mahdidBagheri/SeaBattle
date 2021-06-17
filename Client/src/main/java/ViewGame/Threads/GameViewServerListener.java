package ViewGame.Threads;

import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerRequest;
import Connection.Utils.ClientWaitForInput;
import ViewGame.Controller.GameViewController;

import java.io.IOException;
import java.io.ObjectInputStream;

public class GameViewServerListener extends Thread {
    GameViewController gameViewController;

    public GameViewServerListener(GameViewController gameViewController) {
        this.gameViewController = gameViewController;
    }

    @Override
    public void run() {
        try {
            while (gameViewController != null) {
                ClientWaitForInput.waitForInput(gameViewController.getClientConnection().getSocket());
                ObjectInputStream objectInputStream = new ObjectInputStream(gameViewController.getClientConnection().getSocket().getInputStream());
                ServerRequest serverRequest = (ServerRequest) objectInputStream.readObject();
                gameViewController.applyGameData(serverRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CouldNotConnectToServerException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
