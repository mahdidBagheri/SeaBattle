package Game.Threads;

import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerConnection;
import Connection.Utils.ServerWaitForInput;
import User.Model.Player;
import User.Model.User;

public class GameThreadClientListener extends Thread {
    Player player;

    public GameThreadClientListener(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        boolean isRunning = true;

        try {
            while (isRunning) {
                ServerWaitForInput serverWaitForInput = new ServerWaitForInput();
                serverWaitForInput.waitForInput(player.getConnection().getSocket());
            }
        } catch (CouldNotConnectToServerException e) {
            e.printStackTrace();
        }

    }

}
