package Game.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Game.Listener.BoardPanelListener;
import Game.Listener.UserGamePanelListener;
import MainFrame.View.MainPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UserGamePanel extends JPanel implements ActionListener {
    MainPanel mainPanel;

    JLabel findingOpponentLbl;
    JLabel timerLbl;

    JButton readyBtn;
    JButton shuffleBtn;
    BoardPanel boardPanel;

    UserGamePanelListener userGamePanelListener;

    public UserGamePanel() throws IOException {

        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor03());
        this.setLayout(null);
        this.setBounds(0,0,(int)(frameConfig.getWidth()/2),(int)frameConfig.getHeight());
        this.setVisible(true);

        findingOpponentLbl = new JLabel();
        findingOpponentLbl.setBounds(20,20,150,20);
        findingOpponentLbl.setText("seeking for opponent ... ");
        findingOpponentLbl.setVisible(true);

        timerLbl = new JLabel();
        timerLbl.setBounds(250,20,150,20);
        timerLbl.setText("00:00");
        timerLbl.setVisible(true);

        shuffleBtn = new JButton("shuffle");
        shuffleBtn.setText("shuffle");
        shuffleBtn.setFocusable(false);
        shuffleBtn.setBounds(120,475,250,50);
        shuffleBtn.addActionListener(this);
        shuffleBtn.setEnabled(false);

        readyBtn = new JButton("ready");
        readyBtn.setText("ready");
        readyBtn.setFocusable(false);
        readyBtn.setBounds(425,30,100,50);
        readyBtn.addActionListener(this);
        readyBtn.setEnabled(false);

        boardPanel = new BoardPanel();
        boardPanel.setEnabled(false);

        this.add(findingOpponentLbl);
        this.add(timerLbl);
        this.add(shuffleBtn);
        this.add(readyBtn);
        this.add(boardPanel);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == readyBtn){

        }
        if(e.getSource() == shuffleBtn){

        }

    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getFindingOpponentLbl() {
        return findingOpponentLbl;
    }

    public JLabel getTimerLbl() {
        return timerLbl;
    }

    public JButton getReadyBtn() {
        return readyBtn;
    }

    public JButton getShuffleBtn() {
        return shuffleBtn;
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }


    public void setUserGamePanelListener(UserGamePanelListener userGamePanelListener) {
        this.userGamePanelListener = userGamePanelListener;
    }
}
