package Game.Listener;

import Game.Controller.GameController;
import MainFrame.View.MainPanel;

public class UserGamePanelListener {
    GameController gameController;
    MainPanel mainPanel;

    public UserGamePanelListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }


}
