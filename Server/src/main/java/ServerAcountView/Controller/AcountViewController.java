package ServerAcountView.Controller;

import Connection.DataCaseConnection.ConnectionToDataBase;
import Connection.Server.ServerConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AcountViewController {

    public HashMap<String, String> readUserData(String username) throws SQLException {
        HashMap<String,String> userDataHash = new HashMap<>();
        int ctr = 0;
        ConnectionToDataBase connectionToDataBase = new ConnectionToDataBase();
        String sql =String.format("select * from \"UsersTable\" where \"UserName\" = '%s';",username);
        ResultSet rs = connectionToDataBase.executeQuery(sql);
        if(rs != null){
            while (rs.next()){
                String data = String.format("<html><body> Username: %s <br> Wins: %s <br> Lost: %s <br> Score: %s <br> LastSeen: %s <br> Password: %s Email: %s  </body></html>",
                        rs.getString(2),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(6),
                        rs.getString(3),
                        rs.getString(4));
                userDataHash.put(Integer.toString(ctr),data);
                ctr++;
            }
        }
        connectionToDataBase.Disconect();
        return userDataHash;
    }
}
