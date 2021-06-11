package Game.Threads;

import Game.Controller.GameController;

public class GameThreadServerExecuter extends Thread {
    GameController gameController;
    public GameThreadServerExecuter(GameController gameController) {
        this.gameController = gameController;
    }


}
