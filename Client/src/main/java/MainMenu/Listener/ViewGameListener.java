package MainMenu.Listener;

import Config.NetWorkConfig.NetworkConfig;
import Connection.Client.ClientConnection;
import Connection.Client.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerRequest;
import Connection.Utils.ClientWaitForInput;
import MainFrame.View.MainPanel;
import MainMenu.Events.ViewGameEvent;
import Utils.UserInfoHandler;
import ViewGame.View.GameViewPanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class ViewGameListener {
    MainPanel mainPanel;

    public ViewGameListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }


    public void listen(ViewGameEvent viewGameEvent) throws IOException, CouldNotConnectToServerException, ClassNotFoundException {
        HashMap<String,String> userInfo =   UserInfoHandler.loadInfo();


        ClientConnection clientConnection = new ClientConnection();

        ClientRequest clientRequest = new ClientRequest("viewGame",null,userInfo.get("session"),"onlineGames",userInfo.get("username"),userInfo.get("password"));

        clientConnection.execute(clientRequest);

        ClientWaitForInput.waitForInput(clientConnection.getSocket());
        ObjectInputStream objectInputStream = new ObjectInputStream(clientConnection.getSocket().getInputStream());
        ServerRequest serverRequest = (ServerRequest) objectInputStream.readObject();


        GameViewPanel gameViewPanel = new GameViewPanel(serverRequest.getPayLoad().getStringStringHashMap());
        mainPanel.addViewGame(gameViewPanel);


    }

}
