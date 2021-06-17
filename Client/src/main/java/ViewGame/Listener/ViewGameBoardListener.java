package ViewGame.Listener;

import Connection.Client.ClientConnection;
import Connection.Client.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import ViewGame.Controller.GameViewController;
import ViewGame.View.GameViewPanel;

import java.io.IOException;

public class ViewGameBoardListener {

    GameViewPanel gameViewPanel;
    public ViewGameBoardListener(GameViewPanel gameViewPanel) {
        this.gameViewPanel = gameViewPanel;
    }

    public void listen(String gameUUID) throws IOException, CouldNotConnectToServerException {
        GameViewController gameViewController = new GameViewController(gameViewPanel);
        gameViewController.connectToServer(gameUUID);
        gameViewController.addPanels();


    }
}
