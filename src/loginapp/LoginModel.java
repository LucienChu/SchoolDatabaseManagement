package loginapp;

import admin.StudentData;
import dbUtil.dbConnection;
import student.StudentController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;
    StudentData studentData = null;

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
        PreparedStatement pr0 = null;
        ResultSet rs = null;
        ResultSet rs0 = null;

        String sql = "SELECT * FROM login_tbl WHERE login_name = ? and password = ? and division = ?";
        String findStudentSQL = "SELECT * FROM students WHERE id = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, password);
            pr.setString(3, opt);

            rs = pr.executeQuery();

            String studentId = rs.getString(1);

            pr0 = this.connection.prepareStatement(findStudentSQL);
            pr0.setString(1, studentId);

            rs0 = pr0.executeQuery();

            if(rs0.next()){
                this.studentData = new StudentData (
                        rs0.getString(1),
                        rs0.getString(2),
                        rs0.getString(3),
                        rs0.getString(4),
                        rs0.getString(5),
                        rs0.getDouble(6));
                System.out.print("GOOD");
            }


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
            pr0.close();
            rs0.close();
        }
    }
}
