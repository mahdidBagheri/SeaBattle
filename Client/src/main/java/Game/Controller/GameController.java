package Game.Controller;

import Game.Threads.GameThreadServerExecuter;
import Game.Threads.GameThreadServerListener;
import Game.View.OpponentGamePanel;
import Game.View.UserGamePanel;

public class GameController {
    UserGamePanel userGamePanel;
    OpponentGamePanel opponentGamePanel;
    GameThreadServerListener gameThreadServerListener;
    GameThreadServerExecuter gameThreadServerExecuter;

    public GameController() {
        GameThreadServerListener gameThreadServerListener = new GameThreadServerListener();
        this.gameThreadServerListener = gameThreadServerListener;
        gameThreadServerListener.start();

        GameThreadServerExecuter gameThreadServerExecuter = new GameThreadServerExecuter();
        this.gameThreadServerExecuter = gameThreadServerExecuter;
        gameThreadServerExecuter.start();
    }

    public void setUserGamePanel(UserGamePanel userGamePanel) {
        this.userGamePanel = userGamePanel;
    }
}
