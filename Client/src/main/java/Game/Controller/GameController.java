package Game.Controller;

import Config.NetWorkConfig.NetworkConfig;
import Connection.Client.ClientConnection;
import Connection.Client.ClientRequest;
import Game.Listener.UserGamePanelListener;
import Game.Model.Board;
import Game.Model.GameData;
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


    }

    public void setUserGamePanel(UserGamePanel userGamePanel) {
        this.userGamePanel = userGamePanel;
        this.userGamePanel.setUserGamePanelListener(new UserGamePanelListener(this));
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

    public void setClientConnection(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    public void applyGameData(GameData gameData) {

        printBoard(gameData.getBoard1());
        printBoard(gameData.getBoard2());



    }

    public void printBoard(Board board){
        for (int i = 0; i < 10; i++) {
            System.out.println("\n");
            for (int j = 0; j < 10 ; j++) {
                System.out.print(board.getBoard()[i][j] + "   ");
            }
        }
        System.out.println("\n");
    }

    public void opponentFound() {
        userGamePanel.getFindingOpponentLbl().setText("Opponent found!");
        userGamePanel.getTimerLbl().setText("00:30");
        userGamePanel.getReadyBtn().setEnabled(true);
        userGamePanel.getBoardPanel().setEnabled(true);
        userGamePanel.getShuffleBtn().setEnabled(true);
        userGamePanel.repaint();
    }


    public void hit(int x, int y) {
        System.out.println(x + " ,  " + y);
    }
}
