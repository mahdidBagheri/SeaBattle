package LogIn.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import LogIn.Listener.LoginListener;
import Signup.Listener.SignupListener;

import javax.swing.*;
import java.io.IOException;

public class LoginPanelView extends JPanel {

    JLabel titleLable;

    JLabel emailLable ;
    JTextArea emailText;

    JLabel userNameLable ;
    JTextArea userNameText;

    JLabel password1Lable ;
    JTextArea password1Text;

    JLabel password2Lable ;
    JTextArea password2Text;

    JButton singUpBotton;

    SignupListener signupListener;

    public LoginPanelView() throws IOException {
        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor02());
        this.setLayout(null);
        this.setBounds(0,0,(int)(frameConfig.getWidth()/2),(int)frameConfig.getHeight());

        titleLable = new JLabel();
        titleLable.setBounds(280,20,150,20);
        titleLable.setText("Login");
        titleLable.setVisible(true);


        this.add(titleLable);


    }

    public void setLoginListener(LoginListener loginListener) {
    }
}
