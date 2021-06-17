package Connection.Client;

import ServerAcountView.Listener.AcountViewListener;
import Connection.Exceptions.CouldNotConnectToServerException;
import Connection.Server.ServerConnection;
import Connection.Utils.ServerWaitForInput;
import Game.Exceptions.NotAvailableUserException;
import Game.Model.OnlineGames;
import ServerLogin.Exceptions.IlligalLogin;
import Game.Listener.ServerGameListener;
import ServerLogin.Listener.ServerLoginListener;
import ServerScoreBoard.Listener.ServerScoreBoardListener;
import ServerSignup.Listener.SignupListener;
import ViewGame.Listener.ServerViewGameListener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientThread extends Thread {
    private ServerConnection serverConnection;
    private volatile OnlineGames onlineGames;

    public ClientThread(Socket socket, OnlineGames onlineGames) {
        this.serverConnection = new ServerConnection(socket);
        this.onlineGames = onlineGames;
    }

    @Override
    public void run() {
        //while (!serverConnection.getSocket().isClosed()) {

            try {
                ServerWaitForInput serverWaitForInput = new ServerWaitForInput();
                serverWaitForInput.waitForInput(serverConnection.getSocket());
                ObjectInputStream objectInputStream = new ObjectInputStream(serverConnection.getSocket().getInputStream());
                ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();


                if (clientRequest.getSource().equals("signup")) {
                    SignupListener signupListener = new SignupListener(serverConnection);
                    signupListener.listen(clientRequest);
                } else if (clientRequest.getSource().equals("login")) {
                    ServerLoginListener serverLoginListener = new ServerLoginListener(serverConnection);
                    serverLoginListener.listen(clientRequest);
                } else if (clientRequest.getSource().equals("newGame")) {
                    checkSession(clientRequest.getUsername(), clientRequest.getPassword(), clientRequest.getSession());
                    ServerGameListener serverNewGameListener = new ServerGameListener(serverConnection, onlineGames);
                    serverNewGameListener.listen(clientRequest);
                } else if (clientRequest.getSource().equals("viewGame")) {
                    //checkSession(clientRequest.getUsername(), clientRequest.getPassword(), clientRequest.getSession());
                    ServerViewGameListener serverViewGameListener = new ServerViewGameListener(serverConnection,onlineGames);
                    serverViewGameListener.listen(clientRequest);
                }else if (clientRequest.getSource().equals("ScoreBoard")) {
                    //checkSession(clientRequest.getUsername(), clientRequest.getPassword(), clientRequest.getSession());
                    ServerScoreBoardListener  serverScoreBoardListener = new ServerScoreBoardListener(serverConnection);
                    serverScoreBoardListener.listen(clientRequest);
                }else if (clientRequest.getSource().equals("ViewAcount")) {
                    //checkSession(clientRequest.getUsername(), clientRequest.getPassword(), clientRequest.getSession());
                   AcountViewListener acountViewListener = new AcountViewListener(serverConnection);
                    acountViewListener.listen(clientRequest);
                }

                int a = 0;
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
            } catch (IlligalLogin illigalLogin) {
                illigalLogin.printStackTrace();
                //break;
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (NotAvailableUserException e) {
                e.printStackTrace();
            }

        //}
        try {
            serverConnection.getSocket().close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void checkSession(String username, String password, String session) throws SQLException, IlligalLogin {
        String sql = String.format("select * from \"UsersTable\" where \"UserName\" = '%s' and \"Password\" = '%s' and \"Session\" = '%s';",username,password,session);
        ResultSet rs = serverConnection.getConnectionToDataBase().executeQuery(sql);
        if(rs == null){
            throw new IlligalLogin("illigal login");
        }
        if(rs!=null){
            if(!rs.next()){
                throw new IlligalLogin("illigal login");
            }
        }

    }

}
