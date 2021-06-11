package Game.Threads;

import Game.Controller.GameController;

public class GameThreadServerListener extends Thread {
    GameController gameController;

    public GameThreadServerListener(GameController gameController){
        this.gameController = gameController;
    }

    @Override
    public void run() {
        boolean isRunning = true;

        while (isRunning){











        }
    }
}
