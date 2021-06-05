package Signup.Listener;

import Connection.ClientRequest;

import java.net.Socket;

public class SignupListener {
    Socket socket;

    public SignupListener(Socket socket) {
        this.socket = socket;
    }

    public void listen(ClientRequest clientRequest){

    }

}
