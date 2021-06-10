package LogIn.Controller;

import Connection.Client.ClientConnection;
import Connection.Client.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import LogIn.Exceptions.UsernameAndPasswordDoesNotMatch;
import LogIn.LoginEvent.LoginEvent;

import java.io.IOException;

public class LoginController {

    public void login(LoginEvent loginEvent) throws IOException {
        ClientConnection clientConnection = new ClientConnection();
        ClientRequest clientRequest = new ClientRequest("login",null,null,"login",loginEvent.getUserName(),loginEvent.getPassword());
        clientConnection.execute(clientRequest);
    }

    public void validateLogin(LoginEvent loginEvent) throws IOException,
            ClassNotFoundException, CouldNotConnectToServerException,
            UsernameAndPasswordDoesNotMatch {

        matchUsernameAndPassword(loginEvent);

    }

    private void matchUsernameAndPassword(LoginEvent loginEvent) throws IOException, CouldNotConnectToServerException, ClassNotFoundException, UsernameAndPasswordDoesNotMatch {
        ClientConnection clientConnection = new ClientConnection();
        ClientRequest clientRequest = new ClientRequest("login",null,null,"matchUsernameAndPassword",loginEvent.getUserName(),loginEvent.getPassword());
        boolean isValid = clientConnection.executeBoolean(clientRequest);
        if(!isValid){
            throw new UsernameAndPasswordDoesNotMatch("Username And Password Does Not Match");
        }
    }
}
