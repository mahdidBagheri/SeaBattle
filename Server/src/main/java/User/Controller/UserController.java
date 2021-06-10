package User.Controller;

import Connection.DataCaseConnection.ConnectionToDataBase;
import Connection.Server.ServerConnection;
import User.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    User user;
    public UserController(User user){
        this.user = user;
    }

    public void readUserDataByUsername(String username) throws SQLException {
        ConnectionToDataBase connectionToDataBase = new ConnectionToDataBase();

        String sql = String.format("select * from \"UsersTable\" where \"UserName\" = '%s';",username);
        ResultSet rs = connectionToDataBase.executeQuery(sql);
        if(rs != null){
            if(rs.next()){
                user.setUuid(rs.getString(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setDateJoined(rs.getString(5));
                user.setLastSeen(rs.getString(6));
                user.setSession(rs.getString(7));
            }
        }
    }
}
