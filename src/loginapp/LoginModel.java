package loginapp;

import dbUtil.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;

    public LoginModel(){
        try{
            this.connection = dbConnection.getConnection();
        }catch(SQLException ex){
            ex.getStackTrace();
        }
        if(this.connection == null){
            System.exit(1);
        }
    }

    public boolean isConnected(){
        return this.connection != null;
    }

    public boolean isLogin(String user, String password, String opt) throws Exception {
        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM login_tbl WHERE login_name = ? and password = ? and division = ?";

        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, password);
            pr.setString(3, opt);

            rs = pr.executeQuery();

            boolean boll1;
            if (rs.next())
                return true;
            else
                return false;
        } catch (SQLException ex) {
            return false;
        } finally {
            pr.close();
            rs.close();
        }
    }
}
