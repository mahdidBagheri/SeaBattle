package Signup.Controller;

import Connection.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupController {
    Connection connection;

    public SignupController(Connection connection){
        this.connection = connection;
    }

    public boolean validateEmail(String email) throws SQLException {
        boolean emailExist = isEmailExist(email);



        return (emailExist && true);
    }

    public boolean isEmailExist(String email) throws SQLException {
        String sql = "select \"Email\" from \"UsersTable\";";
        ResultSet rs = connection.getConnectionToDataBase().executeQuery(sql);
        if (rs != null) {
            while (rs.next()) {
                if (rs.getString(0).equals(email)){
                    return true;
                }
            }
        }
        return false;
    }
}
