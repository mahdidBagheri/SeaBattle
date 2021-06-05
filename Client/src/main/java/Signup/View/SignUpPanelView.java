package Signup.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Signup.Listener.SignupListener;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SignUpPanelView extends JPanel {

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

    public SignUpPanelView() throws IOException {
        ColorConfig colorConfig = new ColorConfig();
        FrameConfig frameConfig = new FrameConfig();

        this.setBackground(colorConfig.getColor03());
        this.setLayout(null);
        this.setBounds((int)(frameConfig.getWidth()/2),0,(int)(frameConfig.getWidth()/2),(int)frameConfig.getHeight());

        titleLable = new JLabel();
        titleLable.setBounds(280,20,150,20);
        titleLable.setText("SingUp!");
        titleLable.setVisible(true);


        this.add(titleLable);


    }


    public void setSignupListener(SignupListener signupListener) {
    }
}
