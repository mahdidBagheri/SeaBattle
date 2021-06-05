package Connection;

import Signup.Controller.SignupController;
import Signup.Listener.SignupListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread extends Thread {
    private Socket socket;
    private String serializedRequestObject;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();
            objectInputStream.close();

            if(clientRequest.getSource().equals("signup")){
                SignupListener signupListener = new SignupListener(socket);
                signupListener.listen(clientRequest);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
