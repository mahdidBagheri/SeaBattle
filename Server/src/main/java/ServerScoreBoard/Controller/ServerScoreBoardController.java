package ServerScoreBoard.Controller;

import Connection.DataCaseConnection.ConnectionToDataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ServerScoreBoardController {
    public HashMap<String, String> readScoreBoard() throws SQLException {
        HashMap<String,String> scoreBoardHash = new HashMap<>();
        int ctr = 0;
        ConnectionToDataBase connectionToDataBase = new ConnectionToDataBase();
        String sql = "select * from \"UsersTable\" order by \"Score\" DESC;";
        ResultSet rs = connectionToDataBase.executeQuery(sql);
        if(rs != null){
            while (rs.next()){
                String data = String.format("<html><body> Username: %s <br> Wins: %s <br> Lost: %s    <br> Score: %s </body></html>",
                        rs.getString(2),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10));
                scoreBoardHash.put(Integer.toString(ctr),data);
                ctr++;
            }
        }
        connectionToDataBase.Disconect();
        return scoreBoardHash;
    }
}
