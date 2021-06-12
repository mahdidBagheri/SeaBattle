package Game.Threads;

import Game.Controller.ServerGameController;

public class ServerGameThread extends Thread {
    ServerGameController serverGameController;
    public ServerGameThread(ServerGameController serverGameController) {
        this.serverGameController = serverGameController;
    }

    @Override
    public void run() {

    }
}
