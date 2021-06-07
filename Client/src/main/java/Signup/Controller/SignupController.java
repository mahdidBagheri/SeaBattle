package Signup.Controller;

import Connection.ClientConnection;
import Connection.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import Signup.Events.SignupEvent;
import Signup.Exceptions.EmailExistException;
import Signup.Exceptions.PasswordsNotMatchException;
import Signup.Exceptions.UserNameStartsWithDigitException;
import Signup.Exceptions.UsernameExistsException;

import java.io.IOException;

public class SignupController {
    public void signupValidation(SignupEvent signupEvent) throws PasswordsNotMatchException, UserNameStartsWithDigitException, EmailExistException, UsernameExistsException, IOException, ClassNotFoundException, CouldNotConnectToServerException {
        if(!signupEvent.getPassword1().equals(signupEvent.getPassword2())){
            throw new PasswordsNotMatchException("Passwords do not match");
        }

        if(Character.isDigit(signupEvent.getUserName().charAt(0))){
            throw new UserNameStartsWithDigitException(signupEvent.getUserName());
        }

        ClientConnection clientConnection = new ClientConnection();
        ClientRequest clientRequest = new ClientRequest("signup",signupEvent.getUserName(),null,"validate username",null,null);
        boolean isEmailExists = clientConnection.executeBoolean(clientRequest);
        if(isEmailExists){
            throw new EmailExistException("Email Exists");
        }

        clientRequest = new ClientRequest("signup",signupEvent.getUserName(),null,"validate email",null,null);
        boolean isUsernameExists = clientConnection.executeBoolean(clientRequest);
        if(isUsernameExists){
            throw new UsernameExistsException("Username Exists");
        }

    }

    public void signUp(SignupEvent signupEvent) {

    }
}
