package Game.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
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

    public UserGamePanel() throws IOException {


        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor03());
        this.setLayout(null);
        this.setBounds((int)(frameConfig.getWidth()/2),0,(int)(frameConfig.getWidth()/2),(int)frameConfig.getHeight());


        findingOpponentLbl = new JLabel();
        findingOpponentLbl.setBounds(20,20,150,20);
        findingOpponentLbl.setText("SingUp!");
        findingOpponentLbl.setVisible(true);

        timerLbl = new JLabel();
        timerLbl.setBounds(120,20,150,20);
        timerLbl.setText("SingUp!");
        timerLbl.setVisible(true);

        shuffleBtn = new JButton("shuffle");
        shuffleBtn.setText("shuffle");
        shuffleBtn.setFocusable(false);
        shuffleBtn.setBounds(120,400,250,50);
        shuffleBtn.addActionListener(this);

        readyBtn = new JButton("ready");
        readyBtn.setText("ready");
        readyBtn.setFocusable(false);
        readyBtn.setBounds(350,70,250,50);
        readyBtn.addActionListener(this);

        boardPanel = new BoardPanel();

        this.add(findingOpponentLbl);
        this.add(timerLbl);
        this.add(shuffleBtn);
        this.add(readyBtn);
        this.add(boardPanel);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }
}
