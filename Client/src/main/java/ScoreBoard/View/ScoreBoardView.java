package ScoreBoard.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import MainFrame.View.MainPanel;
import ViewGame.Listener.ViewGameBoardListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class ScoreBoardView extends JPanel implements ActionListener {
    public static ScoreBoardView instance = null;

    MainPanel mainPanel;
    HashMap<String, String> players;

    JButton upBtn;
    JButton downBtn;
    UserPanel userPanel;
    JButton backBtn;

    int idx = 0;

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public ScoreBoardView(HashMap<String, String> players) throws IOException {
        this.players = players;

        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor03());
        this.setLayout(null);
        this.setBounds(0,0,(int)(frameConfig.getWidth()),(int)frameConfig.getHeight());
        this.setVisible(true);

        upBtn = new JButton("up");
        upBtn.setText("up");
        upBtn.setFocusable(false);
        upBtn.setBounds(200,50,300,50);
        upBtn.addActionListener(this);
        upBtn.setEnabled(true);

        downBtn = new JButton("down");
        downBtn.setText("down");
        downBtn.setFocusable(false);
        downBtn.setBounds(200,500,300,50);
        downBtn.addActionListener(this);
        downBtn.setEnabled(true);

        userPanel = new UserPanel(players.get("0"));
        userPanel.setBounds(200,150,300,300);
        userPanel.setVisible(true);
        //initialize();

        backBtn = new JButton("back");
        backBtn.setText("back");
        backBtn.setFocusable(false);
        backBtn.setBounds(20,400,100,50);
        backBtn.addActionListener(this);
        backBtn.setEnabled(true);



        this.add(upBtn);
        this.add(downBtn);
        this.add(userPanel);
        this.add(backBtn);

        instance = this;


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == upBtn){

            if(idx >= 1){
                idx--;

                this.userPanel.setText(players.get(Integer.toString(idx)));
                this.add(userPanel);
                userPanel.repaint();
                repaint();

            }
        }
        else if(e.getSource() == downBtn){

            if(idx < players.size()-1){
                idx++;
                this.userPanel.setText(players.get(Integer.toString(idx)));
                this.add(userPanel);
                userPanel.repaint();
                repaint();

            }
        }
        else if(e.getSource() == backBtn){
            try {
                mainPanel.addMainMenu();
                repaint();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
