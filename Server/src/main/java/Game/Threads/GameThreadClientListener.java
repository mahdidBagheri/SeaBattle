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
        while (!serverGameController.isFinished()) {
            try {
                ServerWaitForInput serverWaitForInput = new ServerWaitForInput();
                serverWaitForInput.waitForInput(player.getConnection().getSocket());


                ObjectInputStream objectInputStream = new ObjectInputStream(player.getConnection().getSocket().getInputStream());
                ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();

                if (clientRequest.getSource().equals("Game")) {
                    if (clientRequest.getCommand().equals("shuffle")) {
                        serverGameController.increaseTimeLeftByShuffle(player);
                        player.decreaseShuffleNum();
                        player.setShuffle(true);
                    } else if (clientRequest.getCommand().equals("hit")) {
                        player.setHited(true);
                        int x = Integer.parseInt(clientRequest.getClientPayLoad().getStringStringHashMap().get("X"));
                        int y = Integer.parseInt(clientRequest.getClientPayLoad().getStringStringHashMap().get("Y"));
                        boolean isAHit = serverGameController.hit(x, y, player);
                        if (!isAHit) {
                            serverGameController.switchTurn();
                        }
                        serverGameController.setTimeLeft(25);
                        synchronized (serverGameController) {
                            serverGameController.notifyAll();
                        }
                        serverGameController.sendGameData("GameData");
                    } else if (clientRequest.getCommand().equals("ready")) {
                        player.setReady(true);
                    }
                }

            }  catch (CouldNotConnectToServerException e) {
                try {
                    boolean player1Connection = serverGameController.connectionCheck(player);
                    if (!player1Connection) {
                        serverGameController.connectionLostProtocol(serverGameController.getPlayer1());
                    }
                    else {
                        continue;
                    }

                } catch (CouldNotConnectToServerException | ClassNotFoundException | IOException couldNotConnectToServerException) {
                    couldNotConnectToServerException.printStackTrace();
                    break;
                }
                e.printStackTrace();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            synchronized (serverGameController) {
                try {
                    serverGameController.wait();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }

        }
    }
}

