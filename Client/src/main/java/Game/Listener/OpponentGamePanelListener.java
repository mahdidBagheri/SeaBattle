package Game.Listener;

import Game.Controller.GameController;

import java.io.IOException;

public class OpponentGamePanelListener {
    GameController gameController;
    public OpponentGamePanelListener(GameController gameController) {
        this.gameController = gameController;
    }

    public void listen(int x, int y) throws IOException {
        gameController.hit(x,y);
    }
}
