package admin;

import dbUtil.dbConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteAdminController implements Initializable {

    private dbConnection dbconnect;

    @FXML
    private TextField targetID;
    @FXML
    private PasswordField targetPassword;
    @FXML
    private PasswordField actionPassword;
    @FXML
    private Label deleteLabel;
    @FXML
    private Button quitBtn;

    private AdminData currentAdmin = null;


    public void initialize(URL url, ResourceBundle rs){
        this.dbconnect = new dbConnection();
    }

    @FXML
    public void deleteAdmin(javafx.event.ActionEvent event){
        String deleteSQL = "DELETE FROM login_tbl WHERE login_name = ? AND password = ?";
        String verifyAdminSQL = "SELECT * FROM login_tbl WHERE login_name = \'" + this.currentAdmin.getLoginName() + "\' AND password = \'" + this.actionPassword.getText() + "\'";
        try{
            Connection connection = new dbConnection().getConnection();

            ResultSet rs = connection.createStatement().executeQuery(verifyAdminSQL);
            if(rs != null) {
                PreparedStatement psDelete = connection.prepareStatement(deleteSQL);
                psDelete.setString(1, this.targetID.getText());
                psDelete.setString(2, this.targetPassword.getText());
                psDelete.executeUpdate();
                this.deleteLabel.setText("User " + this.targetID.getText() + " has been deleted.");
                psDelete.close();

            }
            else{
                this.deleteLabel.setText("Error, either current " + currentAdmin.getLoginName() + " or " + this.targetID.getText() + " is not identify.");
            }
            rs.close();

        }catch (SQLException sqlex){
            sqlex.getStackTrace();
        }

    }

    public void setCurrentAdmin(AdminData admin){
        this.currentAdmin = admin;
    }

    @FXML
    private void closeWindow(javafx.event.ActionEvent event){
        Stage stage = (Stage)quitBtn.getScene().getWindow();
        stage.close();

    }
}
