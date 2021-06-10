package ServerLogin.Controller;

import Connection.Client.ClientRequest;
import Connection.Server.ServerConnection;
import Connection.Server.ServerPayLoad;
import Connection.Server.ServerRequest;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerLoginController {
    ServerConnection serverConnection;

    public ServerLoginController(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public void matchUsernameAndPassword(ClientRequest clientRequest) throws SQLException, IOException {
        String sql = String.format("select * from \"UsersTable\" where \"UserName\" = '%s' and \"Password\" = '%s';",
                clientRequest.getUsername(),
                clientRequest.getPassword());

        boolean doesMatch = false;
        ResultSet rs = serverConnection.getConnectionToDataBase().executeQuery(sql);
        if(rs != null){
            if(rs.next()){
                doesMatch =  true;
            }
        }

        if(doesMatch){
            ServerRequest serverRequest = new ServerRequest(clientRequest.getUsername(),"true",null);
            serverConnection.execute(serverRequest);
        }
        else{
            ServerRequest serverRequest = new ServerRequest(clientRequest.getUsername(),"false",null);
            serverConnection.execute(serverRequest);
        }
    }

    public void login(ClientRequest clientRequest) throws SQLException, IOException {
        ServerPayLoad serverPayLoad = new ServerPayLoad();
        String session = createLoginSession();
        putSessionIntoDataBase(session,clientRequest.getUsername());
        serverPayLoad.getStringStringHashMap().put("session",session);

        ServerRequest serverRequest = new ServerRequest(clientRequest.getUsername(),"setLoginSession",serverPayLoad);
        serverConnection.execute(serverRequest);
    }

    public String createLoginSession(){
        SecureRandom secureRandom = new SecureRandom();
        int rnd = secureRandom.nextInt(1000);
        return String.valueOf(rnd);
    }

    public void putSessionIntoDataBase(String session, String userName) throws SQLException {
        String sql = String.format("update \"UsersTable\" set \"Session\" = '%s' where \"UserName\" = '%s';",session,userName);
        serverConnection.getConnectionToDataBase().executeUpdate(sql);
    }
}
