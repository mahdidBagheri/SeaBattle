package Signup.Listener;

import Connection.ClientRequest;
import Connection.Connection;
import Connection.ServerRequest;
import Signup.Controller.SignupController;

import java.io.IOException;
import java.sql.SQLException;

public class SignupListener {
    Connection connection;

    public SignupListener(Connection connection) {
        this.connection = connection;
    }

    public void listen(ClientRequest clientRequest) throws IOException, SQLException {
        if(clientRequest.getCommand().equals("validate email")){
            SignupController signupController = new SignupController(connection);
            boolean isValidEmail = signupController.validateEmail(clientRequest.getPayLoad());

            ServerRequest serverRequest = new ServerRequest(clientRequest.getUsername(), Boolean.toString(isValidEmail));
            connection.execute(serverRequest);
        }
        else if(clientRequest.getCommand().equals("validate username")){

        }
    }

}
