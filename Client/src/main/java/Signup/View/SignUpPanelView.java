package Signup.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import Signup.Events.SignupEvent;
import Signup.Listener.SignupListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SignUpPanelView extends JPanel implements ActionListener {

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

        userNameLable = new JLabel();
        userNameLable.setBounds(110,100,150,20);
        userNameLable.setText("UserName");
        userNameLable.setVisible(true);

        userNameText = new JTextArea();
        userNameText.setBounds(110,120,150,40);
        userNameText.setVisible(true);

        emailLable = new JLabel();
        emailLable.setBounds(110,180,150,20);
        emailLable.setText("Email");
        emailLable.setVisible(true);

        emailText = new JTextArea();
        emailText.setBounds(110,200,150,40);
        emailText.setVisible(true);

        password1Lable = new JLabel();
        password1Lable.setBounds(320,100,150,20);
        password1Lable.setText("password1");
        password1Lable.setVisible(true);

        password1Text = new JTextArea();
        password1Text.setBounds(320,120,150,40);
        password1Text.setVisible(true);

        password2Lable = new JLabel();
        password2Lable.setBounds(320,180,150,20);
        password2Lable.setText("password1");
        password2Lable.setVisible(true);

        password2Text = new JTextArea();
        password2Text.setBounds(320,200,150,40);
        password2Text.setVisible(true);

        singUpBotton = new JButton("SingUp!");
        singUpBotton.setText("SingUp");
        singUpBotton.setFocusable(false);
        singUpBotton.setBounds(250,300,110,50);
        singUpBotton.addActionListener(this);


        this.add(titleLable);
        this.add(userNameLable);
        this.add(userNameText);
        this.add(emailLable);
        this.add(emailText);
        this.add(password1Lable);
        this.add(password1Text);
        this.add(password2Lable);
        this.add(password2Text);
        this.add(singUpBotton);


    }


    public void setSignupListener(SignupListener signupListener) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == singUpBotton){
            SignupEvent signupEvent = new SignupEvent();
            signupListener.listen(signupEvent);
        }
    }
}
