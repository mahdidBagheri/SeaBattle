package Game.Listener;

import Game.Controller.GameController;
import MainFrame.View.MainPanel;

public class UserGamePanelListener {
    GameController gameController;
    MainPanel mainPanel;

    public UserGamePanelListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }


    public void listen(int x, int y) {
        gameController.hit(x,y);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
