package Game.Threads;

import Connection.Client.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerConnection;
import Connection.Utils.ServerWaitForInput;
import Game.Controller.BoardController;
import Game.Controller.ServerGameController;
import User.Model.Player;
import User.Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;

public class GameThreadClientListener extends Thread {
    ServerGameController serverGameController;
    Player player;

    public GameThreadClientListener(ServerGameController serverGameController, Player player) {
        this.serverGameController = serverGameController;
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
                        player.decreaseShuffleNum();
                        serverGameController.increaseTimeLeftByShuffle(player);
                    }
                    else if(clientRequest.getCommand().equals("hit")){
                        int x = Integer.parseInt(clientRequest.getClientPayLoad().getStringStringHashMap().get("X"));
                        int y = Integer.parseInt(clientRequest.getClientPayLoad().getStringStringHashMap().get("Y"));
                        serverGameController.hit(x,y,player);
                        //serverGameController.switchTurn();
                        serverGameController.sendGameData("GameData");
                    }
                }

            }
        } catch (CouldNotConnectToServerException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
