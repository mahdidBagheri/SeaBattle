package Game.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Game.Graphics.Background;
import Game.Graphics.Sprite;
import Game.Listener.BoardPanelListener;
import Game.Listener.OpponentGamePanelListener;
import Game.Listener.UserGamePanelListener;
import MainFrame.View.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class OpponentGamePanel extends JPanel {
    MainPanel mainPanel;

    JLabel turnLbl;
    JLabel opponentUserNameLbl;

    BoardPanel boardPanel;

    OpponentGamePanelListener opponentGamePanelListener;

    public OpponentGamePanel() throws IOException {

        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor03());
        this.setLayout(null);
        this.setBounds((int)(frameConfig.getWidth()/2),0,(int)(frameConfig.getWidth()/2),(int)frameConfig.getHeight());

        turnLbl = new JLabel();
        turnLbl.setBounds(250,20,150,20);
        turnLbl.setText("");
        turnLbl.setVisible(true);

        opponentUserNameLbl = new JLabel();
        opponentUserNameLbl.setBounds(100,20,150,20);
        opponentUserNameLbl.setText("");
        opponentUserNameLbl.setVisible(true);


        boardPanel = new BoardPanel(100,100);
        boardPanel.setEnabled(false);

        this.add(turnLbl);
        this.add(boardPanel);
        this.add(opponentUserNameLbl);



    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }


    public JLabel getTurnLbl() {
        return turnLbl;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public JLabel getOpponentUserNameLbl() {
        return opponentUserNameLbl;
    }

    public void setOpponentUserNameLbl(JLabel opponentUserNameLbl) {
        this.opponentUserNameLbl = opponentUserNameLbl;
    }

    public void setOpponentGamePanelListener(OpponentGamePanelListener opponentGamePanelListener) {
        this.opponentGamePanelListener = opponentGamePanelListener;
        BoardPanelListener boardPanelListener = new BoardPanelListener(opponentGamePanelListener);
        boardPanel.setBoardPanelListener(boardPanelListener);
    }
}

