package Connection;

import Signup.Listener.SignupListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ClientThread extends Thread {
    private Connection connection;
    private String serializedRequestObject;

    public ClientThread(Socket socket) {
        this.connection = new Connection(socket);
    }

    @Override
    public void run(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(connection.getSocket().getInputStream());
            ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();
            objectInputStream.close();

            if(clientRequest.getSource().equals("signup")){
                SignupListener signupListener = new SignupListener(connection);
                signupListener.listen(clientRequest);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
