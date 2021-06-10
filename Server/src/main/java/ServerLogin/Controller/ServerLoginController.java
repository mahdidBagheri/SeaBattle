package ServerLogin.Controller;

import Connection.Client.ClientRequest;
import Connection.Server.ServerConnection;
import Connection.Server.ServerRequest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerLoginController {
    ServerConnection serverConnection;

    public ServerLoginController(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public void matchUsernameAndPassword(ClientRequest clientRequest) throws SQLException, IOException {
        String sql = String.format("select * from \"UsersTable\" where \"UserName\" = '%s' and \"Pasword\" = '%s';",
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

    public void login() {

    }
}
