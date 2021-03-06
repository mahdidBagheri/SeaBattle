package Signup.Listener;

import Connection.Exceptions.CouldNotConnectToServerException;
import MainFrame.View.MainPanel;
import Signup.Controller.SignupController;
import Signup.Events.SignupEvent;
import Signup.Exceptions.*;

import java.io.IOException;

public class SignupListener {
    MainPanel mainPanel;

    public SignupListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void listen(SignupEvent signupEvent) throws PasswordsNotMatchException, UserNameStartsWithDigitException, UsernameExistsException, IOException, EmailExistException, ClassNotFoundException, CouldNotConnectToServerException, EmptyFieldException {
        SignupController signupController = new SignupController();

        signupController.signupValidation(signupEvent);
        signupController.signUp(signupEvent);
    }


}
