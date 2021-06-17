package ViewGame.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Game.View.BoardPanel;
import MainFrame.View.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class GameViewPanel extends JPanel implements ActionListener {
    MainPanel mainPanel;

    HashMap<String, String> onlineGames;

    JComboBox<String> onlineGamesCombo;
    JButton viewGame;

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


        this.add(onlineGamesCombo);
        this.add(viewGame);

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

    }
}
