package Game.Listener;

import Game.Controller.GameController;

public class UserGamePanelListener {
    GameController gameController;

    public UserGamePanelListener(GameController gameController) {
        this.gameController = gameController;
    }

    public void listen(int x, int y) {
        gameController.hit(x,y);
    }
}
