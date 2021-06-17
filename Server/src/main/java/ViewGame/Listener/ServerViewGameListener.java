package ViewGame.Listener;

import Connection.Server.ServerConnection;
import Connection.Server.ServerPayLoad;
import Connection.Server.ServerRequest;
import Game.Model.OnlineGames;

import java.io.IOException;

public class ServerViewGameListener {
    ServerConnection serverConnection;
    OnlineGames onlineGames;

    public ServerViewGameListener(ServerConnection serverConnection, OnlineGames onlineGames) {
        this.serverConnection = serverConnection;
        this.onlineGames = onlineGames;
    }

    public void listen() throws IOException {
        ServerPayLoad serverPayLoad = new ServerPayLoad();
        for(int i = 0; i < onlineGames.getOnlineGames().size() ; i++ ){
            String username1 = onlineGames.getOnlineGames().get(i).getPlayer1().getUser().getUsername();
            String username2 = onlineGames.getOnlineGames().get(i).getPlayer2().getUser().getUsername();

            serverPayLoad.getStringStringHashMap().put(username1 + " VS " + username2 ,Integer.toString(i));
        }

        ServerRequest serverRequest = new ServerRequest(null,"gameView",serverPayLoad);
        serverConnection.execute(serverRequest);
    }

}
