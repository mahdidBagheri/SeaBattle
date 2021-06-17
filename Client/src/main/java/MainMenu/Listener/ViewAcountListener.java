package MainMenu.Listener;

import Connection.Client.ClientConnection;
import Connection.Client.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerRequest;
import Connection.Utils.ClientWaitForInput;
import MainFrame.View.MainPanel;
import ScoreBoard.View.ScoreBoardView;
import Utils.UserInfoHandler;
import ViewAcount.View.ViewAcountView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class ViewAcountListener {
    MainPanel mainPanel;
    public ViewAcountListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void listen() throws IOException, CouldNotConnectToServerException, ClassNotFoundException {

        HashMap<String,String> userInfo =   UserInfoHandler.loadInfo();


        ClientConnection clientConnection = new ClientConnection();

        ClientRequest clientRequest = new ClientRequest("ViewAcount",null,userInfo.get("session"),"ViewAcount",userInfo.get("username"),userInfo.get("session"));

        clientConnection.execute(clientRequest);

        ClientWaitForInput.waitForInput(clientConnection.getSocket());
        ObjectInputStream objectInputStream = new ObjectInputStream(clientConnection.getSocket().getInputStream());
        ServerRequest serverRequest = (ServerRequest) objectInputStream.readObject();


        ViewAcountView viewAcountView = new ViewAcountView(serverRequest.getPayLoad().getStringStringHashMap());
        mainPanel.addViewAcountView(viewAcountView);
    }
}
