package MainFrame.View;

import MainFrame.Listener.MainFrameListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {
    MainFrameListener mainFrameListener;

    public MainPanel() {
        this.setLayout(null);


    }

    public void setMainFrameListener(MainFrameListener mainFrameListener) {
        this.mainFrameListener = mainFrameListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
