package ServerAcountView.Listener;

import Connection.Client.ClientRequest;
import Connection.Server.ServerConnection;
import Connection.Server.ServerPayLoad;
import Connection.Server.ServerRequest;
import ServerAcountView.Controller.AcountViewController;
import ServerScoreBoard.Controller.ServerScoreBoardController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class AcountViewListener {
    ServerConnection serverConnection;
    public AcountViewListener(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public void listen(ClientRequest clientRequest) throws SQLException, IOException {
        if(clientRequest.getCommand().equals("ViewAcount")){
            AcountViewController acountViewController = new AcountViewController();
            HashMap<String,String> scoreBoard = acountViewController.readUserData(clientRequest.getUsername());
            ServerPayLoad serverPayLoad = new ServerPayLoad();
            serverPayLoad.setStringStringHashMap(scoreBoard);

            ServerRequest serverRequest = new ServerRequest(clientRequest.getUsername(),"ScoreBoard",serverPayLoad);

            serverConnection.execute(serverRequest);
        }
    }
}
