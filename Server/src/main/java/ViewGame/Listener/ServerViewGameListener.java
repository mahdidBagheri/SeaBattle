package ViewGame.Listener;

import Connection.Client.ClientRequest;
import Connection.Server.ServerConnection;
import Connection.Server.ServerPayLoad;
import Connection.Server.ServerRequest;
import Game.Controller.ServerGameController;
import Game.Model.OnlineGames;

import java.io.IOException;

public class ServerViewGameListener {
    ServerConnection serverConnection;
    OnlineGames onlineGames;

    public ServerViewGameListener(ServerConnection serverConnection, OnlineGames onlineGames) {
        this.serverConnection = serverConnection;
        this.onlineGames = onlineGames;
    }

    public void listen(ClientRequest clientRequest) throws IOException, InterruptedException {
        if(clientRequest.getCommand().equals("onlineGames")) {
            ServerPayLoad serverPayLoad = new ServerPayLoad();
            for (int i = 0; i < onlineGames.getOnlineGames().size(); i++) {
                if (onlineGames.getOnlineGames().get(i).getPlayer1() != null && onlineGames.getOnlineGames().get(i).getPlayer2() != null) {
                    String username1 = onlineGames.getOnlineGames().get(i).getPlayer1().getUser().getUsername();
                    String username2 = onlineGames.getOnlineGames().get(i).getPlayer2().getUser().getUsername();

                    serverPayLoad.getStringStringHashMap().put(username1 + " VS " + username2, onlineGames.getOnlineGames().get(i).getGameUUID());
                }
            }

            ServerRequest serverRequest = new ServerRequest(clientRequest.getUsername(), "gameView", serverPayLoad);
            serverConnection.execute(serverRequest);
        }
        else if(clientRequest.getCommand().equals("viewGame")){
            String gameUUID = clientRequest.getClientPayLoad().getStringStringHashMap().get("gameUUID");
            ServerGameController serverGameController = null;
            for (int i = 0; i < onlineGames.getOnlineGames().size() ; i++) {
                if(gameUUID.equals(onlineGames.getOnlineGames().get(i).getGameUUID())){
                    serverGameController = onlineGames.getOnlineGames().get(i);
                    break;
                }
            }
            assert serverGameController != null;
            serverGameController.sendDataToViewr(serverConnection);



        }
    }

}
