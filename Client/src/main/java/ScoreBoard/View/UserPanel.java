package ScoreBoard.View;

import Config.ColorConfig.ColorConfig;

import javax.swing.*;
import java.io.IOException;

public class UserPanel extends JPanel {
    JLabel dataLbl;

    public UserPanel(String data) throws IOException {
        ColorConfig colorConfig = new ColorConfig();


        this.setBackground(colorConfig.getColor04());
        this.setLayout(null);
        this.setVisible(true);

        dataLbl = new JLabel();
        dataLbl.setText(data);
        dataLbl.setBounds(20,20,300,100);
        dataLbl.setEnabled(true);

        this.add(dataLbl);
    }

    public void setText(String s) {
        dataLbl.setText(s);
    }
}
