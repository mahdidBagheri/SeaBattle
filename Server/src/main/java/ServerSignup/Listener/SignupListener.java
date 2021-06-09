package ServerSignup.Listener;

import Connection.ClientRequest;
import Connection.ServerConnection;
import Connection.ServerRequest;
import ServerSignup.Controller.SignupController;
import User.Model.User;

import java.io.IOException;
import java.sql.SQLException;

public class SignupListener {
    ServerConnection serverConnection;

    public SignupListener(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public void listen(ClientRequest clientRequest) throws IOException, SQLException {
        SignupController signupController = new SignupController(serverConnection);

        if(clientRequest.getCommand().equals("validate email")){
            boolean isValidEmail = signupController.validateEmail(clientRequest.getPayLoad());
            ServerRequest serverRequest = new ServerRequest(clientRequest.getUsername(), Boolean.toString(isValidEmail));
            serverConnection.execute(serverRequest);
        }
        else if(clientRequest.getCommand().equals("validate username")){
            boolean isValidUserName = signupController.validateUserName(clientRequest.getPayLoad());
            ServerRequest serverRequest = new ServerRequest(clientRequest.getUsername(), Boolean.toString(isValidUserName));
            serverConnection.execute(serverRequest);
        }
        else if(clientRequest.getCommand().equals("signup")){
            User user = new User();
            user.setUsername(clientRequest.getUsername());
            user.setPassword(clientRequest.getPassword());
            user.setEmail(clientRequest.getPayLoad());

            signupController.signupUser(user);
        }
    }

}
