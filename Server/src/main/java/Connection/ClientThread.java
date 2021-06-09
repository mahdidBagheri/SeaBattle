package Connection;

import Connection.Exceptions.CouldNotConnectToServerException;
import ServerSignup.Listener.SignupListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ClientThread extends Thread {
    private ServerConnection serverConnection;
    private String serializedRequestObject;

    public ClientThread(Socket socket) {
        this.serverConnection = new ServerConnection(socket);
    }

    @Override
    public void run() {
        while (!serverConnection.getSocket().isClosed()) {
            try {
                ServerWaitForInput.waitForInput(serverConnection.getSocket());
                ObjectInputStream objectInputStream = new ObjectInputStream(serverConnection.getSocket().getInputStream());
                ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();

                if (clientRequest.getSource().equals("signup")) {
                    SignupListener signupListener = new SignupListener(serverConnection);
                    signupListener.listen(clientRequest);
                }

            } catch (IOException e) {
                try {
                    serverConnection.getSocket().close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                try {
                    serverConnection.getSocket().close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
            } catch (SQLException throwables) {
                try {
                    serverConnection.getSocket().close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                throwables.printStackTrace();
            } catch (CouldNotConnectToServerException e) {
                try {
                    serverConnection.getSocket().close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
            }

        }
        try {
            serverConnection.getSocket().close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
