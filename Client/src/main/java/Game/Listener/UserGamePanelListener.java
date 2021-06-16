package Game.Listener;

import Game.Controller.GameController;
import MainFrame.View.MainPanel;

import java.io.IOException;

public class UserGamePanelListener {
    GameController gameController;
    MainPanel mainPanel;

    public UserGamePanelListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }


    public void shuffle() throws IOException {
        gameController.shuffleBoard();
    }

    public void ready() throws IOException {
        gameController.ready();
    }

    public void back() throws IOException {
        gameController.getClientConnection().getSocket().close();
        gameController = null;
        mainPanel.addMainMenu();
    }
}
