package ServerScoreBoard.Listener;

import Connection.Client.ClientRequest;
import Connection.Server.ServerConnection;
import Connection.Server.ServerPayLoad;
import Connection.Server.ServerRequest;
import ServerScoreBoard.Controller.ServerScoreBoardController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class ServerScoreBoardListener {
    ServerConnection serverConnection;

    public ServerScoreBoardListener(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public void listen(ClientRequest clientRequest) throws SQLException, IOException {
        if(clientRequest.getCommand().equals("ScoreBoard")){
            ServerScoreBoardController serverScoreBoardController = new ServerScoreBoardController();
            HashMap<String,String> scoreBoard = serverScoreBoardController.readScoreBoard();
            ServerPayLoad serverPayLoad = new ServerPayLoad();
            serverPayLoad.setStringStringHashMap(scoreBoard);

            ServerRequest serverRequest = new ServerRequest(clientRequest.getUsername(),"ScoreBoard",serverPayLoad);

            serverConnection.execute(serverRequest);
        }
    }
}
