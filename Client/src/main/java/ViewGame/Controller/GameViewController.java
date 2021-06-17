package ViewGame.Controller;

import Connection.Client.ClientConnection;
import Connection.Client.ClientPayLoad;
import Connection.Client.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerRequest;
import Connection.Utils.ClientWaitForInput;
import Game.Threads.GameThreadServerListener;
import Utils.UserInfoHandler;
import ViewGame.Threads.GameViewServerListener;
import ViewGame.View.GameViewPanel;

import java.io.IOException;
import java.util.HashMap;

public class GameViewController {
    ClientConnection clientConnection;
    GameViewPanel gameViewPanel;

    public GameViewController(GameViewPanel gameViewPanel) {
        this.gameViewPanel = gameViewPanel;
    }

    public void connectToServer(String gameUUID) throws IOException, CouldNotConnectToServerException {
        this.clientConnection = new ClientConnection();

        ClientPayLoad clientPayLoad = new ClientPayLoad();
        clientPayLoad.getStringStringHashMap().put("gameUUID",gameUUID);
        HashMap<String,String> info =  UserInfoHandler.loadInfo();
        ClientRequest clientRequest = new ClientRequest("viewGame",clientPayLoad,info.get("session"),"viewGame",info.get("username"),info.get("password"));

        clientConnection.execute(clientRequest);

        GameViewServerListener gameViewServerListener = new GameViewServerListener(this);
        gameViewServerListener.start();
    }

    public ClientConnection getClientConnection() {
        return clientConnection;
    }

    public void applyGameData(ServerRequest serverRequest) {
        int a = 0;
    }

    public void addPanels() throws IOException {
        gameViewPanel.clear();
        gameViewPanel.addUserBoardPanel();
        gameViewPanel.addOpponentBoardPanel();
        int a = 0;
    }
}
