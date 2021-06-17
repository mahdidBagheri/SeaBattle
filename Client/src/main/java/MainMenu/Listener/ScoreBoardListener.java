package MainMenu.Listener;

import Connection.Client.ClientConnection;
import Connection.Client.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerRequest;
import Connection.Utils.ClientWaitForInput;
import MainFrame.View.MainPanel;
import MainMenu.Events.ScoreBoardEvent;
import ScoreBoard.View.ScoreBoardView;
import Utils.UserInfoHandler;
import ViewGame.View.GameViewPanel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class ScoreBoardListener {
    MainPanel mainPanel;
    public ScoreBoardListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void listen(ScoreBoardEvent scoreBoardEvent) throws IOException, CouldNotConnectToServerException, ClassNotFoundException {
        HashMap<String,String> userInfo =   UserInfoHandler.loadInfo();


        ClientConnection clientConnection = new ClientConnection();

        ClientRequest clientRequest = new ClientRequest("ScoreBoard",null,userInfo.get("session"),"ScoreBoard",userInfo.get("username"),userInfo.get("session"));

        clientConnection.execute(clientRequest);

        ClientWaitForInput.waitForInput(clientConnection.getSocket());
        ObjectInputStream objectInputStream = new ObjectInputStream(clientConnection.getSocket().getInputStream());
        ServerRequest serverRequest = (ServerRequest) objectInputStream.readObject();


        ScoreBoardView scoreBoardView = new ScoreBoardView(serverRequest.getPayLoad().getStringStringHashMap());
        mainPanel.addScoreBoardView(scoreBoardView);
    }
}
