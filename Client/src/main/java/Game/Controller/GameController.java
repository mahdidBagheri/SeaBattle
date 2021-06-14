package Game.Controller;

import Connection.Client.ClientConnection;
import Connection.Client.ClientRequest;
import Game.Graphics.*;
import Game.Listener.OpponentGamePanelListener;
import Game.Model.Board;
import Game.Model.GameData;
import Game.Threads.GameThreadServerListener;
import Game.View.OpponentGamePanel;
import Game.View.UserGamePanel;
import Utils.UserInfoHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class GameController {
    UserGamePanel userGamePanel;
    OpponentGamePanel opponentGamePanel;
    GameThreadServerListener gameThreadServerListener;

    ClientConnection clientConnection;

    Board board;
    Board opponentBoard;

    public void setGameThreadServerListener() {
        GameThreadServerListener gameThreadServerListener = new GameThreadServerListener(this);
        this.gameThreadServerListener = gameThreadServerListener;
        gameThreadServerListener.start();

    }

    public void setUserGamePanel(UserGamePanel userGamePanel) {
        this.userGamePanel = userGamePanel;
    }

    public void AnswerCheckConnectionFromServer() throws IOException {
        HashMap<String, String> userInfo = UserInfoHandler.loadInfo();

        ClientRequest clientRequest = new ClientRequest("newGame", null,
                userInfo.get("session"), "true", userInfo.get("username"), userInfo.get("password"));

        clientConnection.execute(clientRequest);
    }

    public ClientConnection getClientConnection() {
        return clientConnection;
    }

    public void setClientConnection(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    public void applyGameData(GameData gameData) throws IOException {
        printBoard(gameData.getBoard1());

        //TODO change this part !
            this.board = gameData.getBoard1();
            this.opponentBoard = gameData.getBoard2();

        retriveBoard();
    }

    public void printBoard(Board board) {
        for (int i = 0; i < 10; i++) {
            System.out.println("\n");
            for (int j = 0; j < 10; j++) {
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
        opponentGamePanel.setVisible(true);
        opponentGamePanel.getBoardPanel().setEnabled(true);
        userGamePanel.repaint();
        opponentGamePanel.repaint();
    }


    public void hit(int x, int y) {
        System.out.println(x + " ,  " + y);

    }

    public void addOpponentGamePanel() throws IOException {
        opponentGamePanel.setVisible(true);
        OpponentGamePanelListener opponentGamePanelListener = new OpponentGamePanelListener(this);
        opponentGamePanel.setOpponentGamePanelListener(opponentGamePanelListener);


    }

    public void setOpponentGamePanel(OpponentGamePanel opponentGamePanel) {
        this.opponentGamePanel = opponentGamePanel;
    }

    public void retriveBoard() {
        clearBoard();
        retriveShips();
    }

    private void clearBoard() {
        userGamePanel.getBoardPanel().clear();
    }

    private void retriveShips() {
        retriveShip('a');
        retriveShip('b');
        retriveShip('c');
        retriveShip('d');
        retriveShip('e');
        retriveShip('f');
        retriveShip('g');
        retriveShip('h');
        retriveShip('i');
        retriveShip('j');
    }

    private void retriveShip(char symbol) {
        int[] shipData = retriveShipData(symbol);
        if (symbol == 'a') {
            if (shipData[0] == 0) {
                userGamePanel.getBoardPanel().setHBattleShip(new HBattleShip(shipData[2], shipData[3]));
            } else if (shipData[0] == 1) {
                userGamePanel.getBoardPanel().setVBattleShip(new VBattleShip(shipData[2], shipData[3]));
            }
        } else if (symbol == 'b' || symbol == 'c') {
            if (shipData[0] == 0) {
                userGamePanel.getBoardPanel().setHCruiser(new HCruiser(shipData[2], shipData[3]));
            } else if (shipData[0] == 1) {
                userGamePanel.getBoardPanel().setVCruiser(new VCruiser(shipData[2], shipData[3]));
            }
        } else if (symbol == 'd' || symbol == 'e' || symbol == 'f') {
            if (shipData[0] == 0) {
                userGamePanel.getBoardPanel().setHDestroyer(new HDestroyer(shipData[2], shipData[3]));
            } else if (shipData[0] == 1) {
                userGamePanel.getBoardPanel().setVDestroyer(new VDestroyer(shipData[2], shipData[3]));
            }
        } else if (symbol == 'g' || symbol == 'h' || symbol == 'i' || symbol == 'j') {
            userGamePanel.getBoardPanel().setFrigate(new Frigate(shipData[2], shipData[3]));
        }
    }


    private int[] retriveShipData(char symbol) {
        int orientation = -1;
        int length = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.getBoard()[i][j].charAt(1) == symbol) {
                    x = j;
                    y = i;
                    if ( i + 1 <= 9 && board.getBoard()[i + 1][j].charAt(1) == symbol) {
                        orientation = 1;

                        length++;
                        int l = 0;
                        while ( i + l <= 9 && board.getBoard()[i + l][j].charAt(1) == symbol) {
                            length++;
                            l++;
                        }
                        break;
                    } else if (j + 1 <= 9 && board.getBoard()[i][j + 1].charAt(1) == symbol) {
                        orientation = 0;
                        length++;
                        int l = 0;
                        while (j + l <= 9 && board.getBoard()[i][j + l].charAt(1) == symbol ) {
                            length++;
                            l++;
                        }
                        break;
                    } else {
                        orientation = 0;
                        length++;
                        break;
                    }
                }
            }
            if (orientation != -1) {
                break;
            }
        }
        int[] data = new int[4];
        data[0] = orientation;
        data[1] = length;
        data[2] = x * 35;
        data[3] = y * 35;


        return data;
    }

    public void shuffleBoard() throws IOException {
        //TODO modify!
        ClientRequest clientRequest = new ClientRequest("Game",null,null,"shuffle",null,null);
        clientConnection.execute(clientRequest);
    }
}
