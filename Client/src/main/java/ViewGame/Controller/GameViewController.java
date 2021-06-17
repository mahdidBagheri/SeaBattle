package ViewGame.Controller;

import Connection.Client.ClientConnection;
import Connection.Client.ClientPayLoad;
import Connection.Client.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerRequest;
import Connection.Utils.ClientWaitForInput;
import Game.Graphics.*;
import Game.Model.Board;
import Game.Model.GameData;
import Game.Threads.GameThreadServerListener;
import Game.View.BoardPanel;
import Game.View.OpponentGamePanel;
import Game.View.UserGamePanel;
import Utils.UserInfoHandler;
import ViewGame.Threads.GameViewServerListener;
import ViewGame.View.GameViewPanel;

import java.io.IOException;
import java.util.HashMap;

public class GameViewController {

    ClientConnection clientConnection;
    GameViewPanel gameViewPanel;

    Board userBoard;
    Board opponentBoard;

    BoardPanel userBoardPanel;
    BoardPanel opponentBoardPanel;

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

    public void applyGameData(GameData gameData) {
        //printBoard(gameData.getBoard1());

        //TODO change this part !
        this.userBoard = gameData.getBoard1();
        this.opponentBoard = gameData.getBoard2();
        retriveBoard();

        int a = 0;
    }

    public void retriveBoard() {
        clearBoard();
        retriveHits();
        userBoardPanel.repaint();
        opponentBoardPanel.repaint();
    }

    private void clearBoard() {
        userBoardPanel.clear();
        opponentBoardPanel.clear();
    }

    private void retriveHits() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (userBoard.getBoard()[i][j].charAt(0) == '-' && userBoard.getBoard()[i][j].charAt(1) != '0') {
                    userBoardPanel.addCrosses(new Cross(j * 35, i * 35));
                } else if (userBoard.getBoard()[i][j].charAt(0) == '-' && userBoard.getBoard()[i][j].charAt(1) == '0') {
                    userBoardPanel.addBomb(new Bomb(j * 35, i * 35));
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (opponentBoard.getBoard()[i][j].charAt(0) == '-' && opponentBoard.getBoard()[i][j].charAt(1) != '0') {
                    opponentBoardPanel.addCrosses(new Cross(j * 35, i * 35));
                } else if (opponentBoard.getBoard()[i][j].charAt(0) == '-' && opponentBoard.getBoard()[i][j].charAt(1) == '0') {
                    opponentBoardPanel.addBomb(new Bomb(j * 35, i * 35));
                }
            }
        }

    }


    public void addPanels() throws IOException {
        gameViewPanel.clear();
        this.userBoardPanel = gameViewPanel.addUserBoardPanel();
        this.opponentBoardPanel = gameViewPanel.addOpponentBoardPanel();
        int a = 0;
    }
}
