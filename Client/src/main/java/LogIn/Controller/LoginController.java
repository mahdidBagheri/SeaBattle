package LogIn.Controller;

import Connection.Client.ClientConnection;
import Connection.Client.ClientRequest;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerListener;
import Connection.Server.ServerRequest;
import LogIn.Exceptions.UsernameAndPasswordDoesNotMatch;
import LogIn.LoginEvent.LoginEvent;
import Utils.SessionHandler;

import java.io.IOException;

public class LoginController {

    public void login(LoginEvent loginEvent) throws IOException,
            ClassNotFoundException, CouldNotConnectToServerException {

        ClientConnection clientConnection = new ClientConnection();
        ClientRequest clientRequest = new ClientRequest("login",null,null,"login",loginEvent.getUserName(),loginEvent.getPassword());
        clientConnection.execute(clientRequest);

        ServerListener serverListener = new ServerListener(clientConnection);
        ServerRequest serverRequest = serverListener.listen();

        String session = serverRequest.getPayLoad().getStringStringHashMap().get("session");
        SessionHandler.saveSession(session);
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
