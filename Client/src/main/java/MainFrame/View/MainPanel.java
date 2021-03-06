package MainFrame.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Game.View.OpponentGamePanel;
import Game.View.UserGamePanel;
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
    UserGamePanel userGamePanel;
    OpponentGamePanel opponentGamePanel;

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
        if(loginPanelView != null){
            instance.remove(loginPanelView);
        }
        if(signUpPanelView != null){
            instance.remove(signUpPanelView);
        }
        if(mainMenuView != null){
            instance.remove(mainMenuView);
        }
        if(userGamePanel != null){
            instance.remove(userGamePanel);
        }
        if(opponentGamePanel != null){
            instance.remove(opponentGamePanel);
        }

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

    public void addUserGamePanel(UserGamePanel userGamePanel) throws IOException {
        instance.clear();
        this.userGamePanel = userGamePanel;

        userGamePanel.setMainPanel(instance);
        NextPanelListener nextPanelListener = new NextPanelListener() {
            @Override
            public void listen(String string) throws IOException {
                if(string.equals("Exit")){
                    instance.getPrevPanelListener().listen("Exit");
                }
                else if(string.equals("backBtn")){
                    addMainMenu();
                }
            }
        };
        mainMenuView.setPrevPanelListener(nextPanelListener);
        instance.add(userGamePanel);
        instance.revalidate();
        instance.repaint();

    }

    public void addOpponentGamePanel(OpponentGamePanel opponentGamePanel) {
        this.opponentGamePanel = opponentGamePanel;

        opponentGamePanel.setMainPanel(instance);
        opponentGamePanel.setVisible(false);
        instance.add(opponentGamePanel);
        instance.revalidate();
        instance.repaint();
    }
}
