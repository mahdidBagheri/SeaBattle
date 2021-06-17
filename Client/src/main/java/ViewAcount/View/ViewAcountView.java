package ViewAcount.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import MainFrame.View.MainPanel;
import ScoreBoard.View.ScoreBoardView;
import ScoreBoard.View.UserPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class ViewAcountView extends JPanel implements ActionListener {
    public static ViewAcountView instance = null;

    MainPanel mainPanel;
    HashMap<String, String> players;

    JLabel userLabel;
    JButton backBtn;

    int idx = 0;

    public void setMainPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public ViewAcountView(HashMap<String, String> user) throws IOException {


        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor03());
        this.setLayout(null);
        this.setBounds(0,0,(int)(frameConfig.getWidth()),(int)frameConfig.getHeight());
        this.setVisible(true);


        backBtn = new JButton("back");
        backBtn.setText("back");
        backBtn.setFocusable(false);
        backBtn.setBounds(20,400,100,50);
        backBtn.addActionListener(this);
        backBtn.setEnabled(true);

        userLabel = new JLabel();
        userLabel.setBounds(100,100,500,500);
        userLabel.setVisible(true);
        userLabel.setText(user.get("0"));

        this.add(userLabel);
        this.add(backBtn);

        instance = this;


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == backBtn){
            try {
                mainPanel.addMainMenu();
                repaint();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
