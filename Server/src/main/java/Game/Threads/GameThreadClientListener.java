package Game.Threads;

import Connection.Client.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerConnection;
import Connection.Utils.ServerWaitForInput;
import User.Model.Player;
import User.Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;

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

                ObjectInputStream objectInputStream = new ObjectInputStream(player.getConnection().getSocket().getInputStream());
                ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();

                if(clientRequest.getSource().equals("Game")){
                    if(clientRequest.getCommand().equals("shuffle")){
                        player.setShuffle(true);
                    }
                }

            }
        } catch (CouldNotConnectToServerException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
