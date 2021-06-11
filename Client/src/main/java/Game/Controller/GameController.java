package Game.Controller;

import Connection.Client.ClientConnection;
import Game.Threads.GameThreadServerExecuter;
import Game.Threads.GameThreadServerListener;
import Game.View.OpponentGamePanel;
import Game.View.UserGamePanel;

import java.io.IOException;

public class GameController {
    UserGamePanel userGamePanel;
    OpponentGamePanel opponentGamePanel;
    GameThreadServerListener gameThreadServerListener;
    GameThreadServerExecuter gameThreadServerExecuter;

    ClientConnection clientConnection;

    public GameController() throws IOException {
        GameThreadServerListener gameThreadServerListener = new GameThreadServerListener(this);
        this.gameThreadServerListener = gameThreadServerListener;
        gameThreadServerListener.start();

        GameThreadServerExecuter gameThreadServerExecuter = new GameThreadServerExecuter(this);
        this.gameThreadServerExecuter = gameThreadServerExecuter;
        gameThreadServerExecuter.start();

        clientConnection = new ClientConnection();
    }

    public void setUserGamePanel(UserGamePanel userGamePanel) {
        this.userGamePanel = userGamePanel;
    }
}
