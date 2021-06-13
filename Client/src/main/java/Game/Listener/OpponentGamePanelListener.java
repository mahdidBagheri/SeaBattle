package Game.Listener;

import Game.Controller.GameController;

public class OpponentGamePanelListener {
    GameController gameController;
    public OpponentGamePanelListener(GameController gameController) {
        this.gameController = gameController;
    }

    public void listen(int x, int y) {
        gameController.hit(x,y);
    }
}
