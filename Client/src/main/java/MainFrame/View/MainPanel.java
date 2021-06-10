package MainFrame.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Interfaces.NextPanelListener;
import LogIn.Listener.LoginListener;
import LogIn.View.LoginPanelView;
import MainMenu.View.MainMenuView;
import Signup.Listener.SignupListener;
import Signup.View.SignUpPanelView;

import javax.swing.*;
import java.io.IOException;

public class MainPanel extends JPanel {
    NextPanelListener prevPanelListener;
    public static MainPanel instance = null;

    SignUpPanelView signUpPanelView;
    LoginPanelView loginPanelView;
    MainMenuView mainMenuView;

    public MainPanel() throws IOException {
        this.setLayout(null);

        FrameConfig frameConfig = new FrameConfig();
        ColorConfig colorConfig = new ColorConfig();

        this.setVisible(true);
        this.setBounds(0,0,frameConfig.getWidth(),frameConfig.getHeight());
        this.setBackground(colorConfig.getColor01());

        instance = this;
        initialize();
    }

    private void initialize() throws IOException {
        signUpPanelView = new SignUpPanelView();
        signUpPanelView.setSignupListener(new SignupListener(this));
        this.add(signUpPanelView);

        NextPanelListener nextPanelListener = string -> {
            if(string.equals("Exit")){
                prevPanelListener.listen("Exit");
            }
            else if(string.equals("Logout")){
                instance.removeAll();
                initialize();
            }
        };

        loginPanelView = new LoginPanelView();
        loginPanelView.setLoginListener(new LoginListener(this));
        loginPanelView.setNextFrameListener(nextPanelListener);
        this.add(loginPanelView);

    }

    public void setNextPanelListener(NextPanelListener nextPanelListener) {
        this.prevPanelListener = nextPanelListener;
    }

    public NextPanelListener getPrevPanelListener() {
        return prevPanelListener;
    }

    public void setPrevPanelListener(NextPanelListener prevPanelListener) {
        this.prevPanelListener = prevPanelListener;
    }

    public void clear(){
        instance.remove(loginPanelView);
        instance.remove(signUpPanelView);
    }

    public void addMainMenu() throws IOException {
        instance.clear();
        mainMenuView = new MainMenuView(instance);
        NextPanelListener nextPanelListener = new NextPanelListener() {
            @Override
            public void listen(String string) throws IOException {
                if(string.equals("Exit")){
                    instance.getPrevPanelListener().listen("Exit");
                }
                else if(string.equals("Logout")){
                    instance.getPrevPanelListener().listen("Logout");
                }
            }
        };
        mainMenuView.setPrevPanelListener(nextPanelListener);
        instance.add(mainMenuView);
        instance.revalidate();
        instance.repaint();
    }
}
