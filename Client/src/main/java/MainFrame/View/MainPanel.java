package MainFrame.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import LogIn.Listener.LoginListener;
import LogIn.View.LoginPanelView;
import MainFrame.Listener.MainFrameListener;
import Signup.Listener.SignupListener;
import Signup.View.SignUpPanelView;

import javax.swing.*;
import java.io.IOException;

public class MainPanel extends JPanel {
    MainFrameListener mainFrameListener;

    public MainPanel() throws IOException {
        this.setLayout(null);

        FrameConfig frameConfig = new FrameConfig();
        ColorConfig colorConfig = new ColorConfig();

        this.setVisible(true);
        this.setBounds(0,0,frameConfig.getWidth(),frameConfig.getHeight());
        this.setBackground(colorConfig.getColor01());

        initialize();
    }

    private void initialize() throws IOException {
        SignUpPanelView signUpPanelView = new SignUpPanelView();
        signUpPanelView.setSignupListener(new SignupListener(this));
        this.add(signUpPanelView);

        LoginPanelView loginPanelView = new LoginPanelView();
        loginPanelView.setLoginListener(new LoginListener(this));
        this.add(loginPanelView);

    }

    public void setMainFrameListener(MainFrameListener mainFrameListener) {
        this.mainFrameListener = mainFrameListener;
    }

}
