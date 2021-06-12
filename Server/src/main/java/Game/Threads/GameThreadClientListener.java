package Game.Threads;

import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerConnection;
import Connection.Utils.ServerWaitForInput;
import User.Model.User;

public class GameThreadClientListener extends Thread {
    User user;
    ServerConnection userConnection;

    public GameThreadClientListener(User user, ServerConnection userConnection) {
        this.user = user;
        this.userConnection = userConnection;
    }

    @Override
    public void run() {
        boolean isRunning = true;

        try {
            while (isRunning) {
                ServerWaitForInput serverWaitForInput = new ServerWaitForInput();
                serverWaitForInput.waitForInput(userConnection.getSocket());
            }
        } catch (CouldNotConnectToServerException e) {
            e.printStackTrace();
        }

    }

}
