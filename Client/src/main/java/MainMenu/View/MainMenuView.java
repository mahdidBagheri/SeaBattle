package MainMenu.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Interfaces.NextPanelListener;
import MainFrame.View.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainMenuView extends JPanel implements ActionListener {
    NextPanelListener prevPanelListener;
    MainPanel mainPanel;

    JButton newGame;
    JButton viewOtherGames;
    JButton viewAcount;
    JButton logout;

    public MainMenuView(MainPanel mainPanel) throws IOException {
        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor02());
        this.setLayout(null);
        this.setBounds(0,0,(int)(frameConfig.getWidth()),(int)frameConfig.getHeight());

        newGame = new JButton("newGame");
        newGame.setText("newGame");
        newGame.setFocusable(false);
        newGame.setBounds(200,20,110,50);
        newGame.addActionListener(this);

        this.add(newGame);
    }

    public NextPanelListener getPrevPanelListener() {
        return prevPanelListener;
    }

    public void setPrevPanelListener(NextPanelListener prevPanelListener) {
        this.prevPanelListener = prevPanelListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
