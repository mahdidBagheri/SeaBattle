package LogIn.Listener;

import Connection.Exceptions.CouldNotConnectToServerException;
import LogIn.Controller.LoginController;
import LogIn.Exceptions.EmptyFieldException;
import LogIn.Exceptions.UsernameAndPasswordDoesNotMatch;
import LogIn.LoginEvent.LoginEvent;
import MainFrame.View.MainPanel;
import java.io.IOException;

public class LoginListener {
    MainPanel mainPanel;

    public LoginListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void listen(LoginEvent loginEvent) throws EmptyFieldException, IOException,
            UsernameAndPasswordDoesNotMatch, CouldNotConnectToServerException,
            ClassNotFoundException {

        if(loginEvent.getUserName().isEmpty()){
            throw new EmptyFieldException("User name panel is empty");
        }

        if(loginEvent.getPassword().isEmpty()){
            throw new EmptyFieldException("password panel is empty");
        }

        LoginController loginController = new LoginController();
        loginController.validateLogin(loginEvent);
        loginController.login(loginEvent);
    }

}
