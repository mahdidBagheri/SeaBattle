package Signup.Listener;

import MainFrame.View.MainPanel;
import Signup.Controller.SignupController;
import Signup.Events.SignupEvent;
import Signup.Exceptions.PasswordsNotMatchException;
import Signup.Exceptions.UserNameStartsWithDigitException;

public class SignupListener {
    MainPanel mainPanel;

    public SignupListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public void listen(SignupEvent signupEvent) throws PasswordsNotMatchException, UserNameStartsWithDigitException {
        SignupController signupController = new SignupController();

        signupController.signupValidation(signupEvent);
        signupController.signUp(signupEvent);
    }


}
