package MainFrame.View;

import Config.FrameConfig.FrameConfig;
import Interfaces.NextPanelListener;
import MainFrame.Listener.MainFrameListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrameView extends JFrame {
    private static MainFrameView instance = null;

    private MainPanel mainPanel;


    public MainFrameView() throws IOException {
        FrameConfig frameConfig = new FrameConfig();

        //this.setTitle("Welcome :)");
        this.setVisible(true);
        this.setSize(new Dimension(frameConfig.getWidth(),frameConfig.getHeight()));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLocation(frameConfig.getXcoordinate(),frameConfig.getYcoordinate());
        instance = this;

        NextPanelListener nextPanelListener = new NextPanelListener() {
            @Override
            public void listen(String string) {
                if(string.equals("Exit")){
                    instance.dispose();
                }
            }
        };

        mainPanel = new MainPanel();

        mainPanel.setNextPanelListener(nextPanelListener);
        this.add(mainPanel);
    }
}
