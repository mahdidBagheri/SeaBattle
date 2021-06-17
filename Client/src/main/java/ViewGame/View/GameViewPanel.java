package ViewGame.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Connection.Exceptions.CouldNotConnectToServerException;
import Game.Model.Board;
import Game.View.BoardPanel;
import Game.View.OpponentGamePanel;
import Game.View.UserGamePanel;
import MainFrame.View.MainPanel;
import MainMenu.Listener.ViewGameListener;
import ViewGame.Listener.ViewGameBoardListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class GameViewPanel extends JPanel implements ActionListener {
    public static GameViewPanel gameViewPanel = null;
    MainPanel mainPanel;

    HashMap<String, String> onlineGames;

    JComboBox<String> onlineGamesCombo;
    JButton viewGame;

    ViewGameBoardListener viewGameBoardListener;

    BoardPanel userBoardPanel;
    BoardPanel opponentBoardPanel;


    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public GameViewPanel(HashMap<String, String> onlineGames) throws IOException {
        this.onlineGames = onlineGames;

        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor03());
        this.setLayout(null);
        this.setBounds(0,0,(int)(frameConfig.getWidth()),(int)frameConfig.getHeight());
        this.setVisible(true);

        onlineGamesCombo = new JComboBox<>();
        onlineGamesCombo.setBounds(10,10,250,50);

        viewGame = new JButton("viewGame");
        viewGame.setText("viewGame");
        viewGame.setFocusable(false);
        viewGame.setBounds(15,475,100,50);
        viewGame.addActionListener(this);
        viewGame.setEnabled(true);

        viewGameBoardListener = new ViewGameBoardListener(this);

        this.add(onlineGamesCombo);
        this.add(viewGame);

        gameViewPanel = this;

        initialize(onlineGames);

    }

    public void initialize(HashMap<String, String> onlineGames){
        for (String game : onlineGames.keySet()){
            onlineGamesCombo.addItem(game);
        }
        repaint();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == viewGame){
            String selectedGame = (String)onlineGamesCombo.getSelectedItem();
            String gameUUID = onlineGames.get(selectedGame);
            try {
                viewGameBoardListener.listen(gameUUID);
            } catch (IOException | CouldNotConnectToServerException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void clear() {
        if(userBoardPanel != null){
            remove(userBoardPanel);
        }
        if(opponentBoardPanel != null){
            remove(opponentBoardPanel);
        }
    }

    public BoardPanel addUserBoardPanel() throws IOException {
        this.userBoardPanel = new BoardPanel(100,100);
        this.add(userBoardPanel);
        revalidate();
        repaint();
        return userBoardPanel;
    }

    public BoardPanel addOpponentBoardPanel() throws IOException {
        this.opponentBoardPanel = new BoardPanel(600,100);
        this.add(opponentBoardPanel);
        revalidate();
        repaint();
        return opponentBoardPanel;
    }
}
