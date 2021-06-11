package Game.Controller;

import Config.NetWorkConfig.NetworkConfig;
import Connection.Client.ClientConnection;
import Connection.Client.ClientRequest;
import Game.Threads.GameThreadServerExecuter;
import Game.Threads.GameThreadServerListener;
import Game.View.OpponentGamePanel;
import Game.View.UserGamePanel;
import Utils.UserInfoHandler;

import java.io.IOException;
import java.util.HashMap;

public class GameController {
    UserGamePanel userGamePanel;
    OpponentGamePanel opponentGamePanel;
    GameThreadServerListener gameThreadServerListener;

    ClientConnection clientConnection;

    public GameController() throws IOException {
        GameThreadServerListener gameThreadServerListener = new GameThreadServerListener(this);
        this.gameThreadServerListener = gameThreadServerListener;
        gameThreadServerListener.start();

        clientConnection = new ClientConnection();
    }

    public void setUserGamePanel(UserGamePanel userGamePanel) {
        this.userGamePanel = userGamePanel;
    }

    public void AnswerCheckConnectionFromServer() throws IOException {
        HashMap<String,String> userInfo = UserInfoHandler.loadInfo();

        ClientRequest clientRequest = new ClientRequest("newGame", null,
                userInfo.get("session"),"true",userInfo.get("username"),userInfo.get("password"));

        clientConnection.execute(clientRequest);
    }

    public ClientConnection getClientConnection() {
        return clientConnection;
    }
}
