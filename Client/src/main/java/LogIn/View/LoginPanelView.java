package LogIn.View;

import Config.ColorConfig.ColorConfig;
import Config.FrameConfig.FrameConfig;
import LogIn.Listener.LoginListener;
import LogIn.LoginEvent.LoginEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginPanelView extends JPanel implements ActionListener {

    JLabel titleLable;

    JLabel userNameLable ;
    JTextArea userNameText;

    JLabel passwordLable ;
    JTextArea passwordText;


    JButton loginBotton;

    LoginListener loginListener;

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

        userNameLable = new JLabel();
        userNameLable.setBounds(10,150,150,20);
        userNameLable.setText("UserName");
        userNameLable.setVisible(true);

        userNameText = new JTextArea();
        userNameText.setBounds(10,170,150,40);
        userNameText.setVisible(true);

        passwordLable = new JLabel();
        passwordLable.setBounds(200,150,150,20);
        passwordLable.setText("password");
        passwordLable.setVisible(true);

        passwordText = new JTextArea();
        passwordText.setBounds(200,170,150,40);
        passwordText.setVisible(true);

        loginBotton = new JButton("SingUp!");
        loginBotton.setText("Login");
        loginBotton.setFocusable(false);
        loginBotton.setBounds(200,300,110,50);
        loginBotton.addActionListener(this);


        this.add(titleLable);
        this.add(userNameLable);
        this.add(userNameText);
        this.add(passwordLable);
        this.add(passwordText);
        this.add(loginBotton);

    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginBotton){
            LoginEvent loginEvent = new LoginEvent();
            loginListener.listen(loginEvent);
        }
    }
}
