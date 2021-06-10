package MainMenu.Listener;

import Connection.Client.ClientConnection;
import Connection.Client.ClientPayLoad;
import Connection.Client.ClientRequest;
import Connection.Server.ServerRequest;
import MainFrame.View.MainPanel;
import MainMenu.Events.NewGameEvent;
import Utils.UserInfoHandler;

import java.io.IOException;
import java.util.HashMap;

public class NewGameListener {
    MainPanel mainPanel;

    public NewGameListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void listen(NewGameEvent newGameEvent) throws IOException {
        ClientConnection clientConnection = new ClientConnection();

        HashMap<String,String> userInfo = UserInfoHandler.loadInfo();

        ClientPayLoad clientPayLoad = new ClientPayLoad();
        clientPayLoad.getStringStringHashMap().put("username",userInfo.get("username"));

        ClientRequest clientRequest = new ClientRequest("newGame",
                clientPayLoad,userInfo.get("session"),"newGame",
                userInfo.get("username"),userInfo.get("password"));

        clientConnection.execute(clientRequest);
        mainPanel.addUserGamePanel();

    }

}
