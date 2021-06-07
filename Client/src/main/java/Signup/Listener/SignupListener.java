package Signup.Listener;

import Connection.Exceptions.CouldNotConnectToServerException;
import MainFrame.View.MainPanel;
import Signup.Controller.SignupController;
import Signup.Events.SignupEvent;
import Signup.Exceptions.EmailExistException;
import Signup.Exceptions.PasswordsNotMatchException;
import Signup.Exceptions.UserNameStartsWithDigitException;
import Signup.Exceptions.UsernameExistsException;

import java.io.IOException;

public class SignupListener {
    MainPanel mainPanel;

    public SignupListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void listen(SignupEvent signupEvent) throws PasswordsNotMatchException, UserNameStartsWithDigitException, UsernameExistsException, IOException, EmailExistException, ClassNotFoundException, CouldNotConnectToServerException {
        SignupController signupController = new SignupController();

        signupController.signupValidation(signupEvent);
        signupController.signUp(signupEvent);
    }


}
