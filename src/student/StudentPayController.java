package student;

import admin.StudentData;
import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.geom.RectangularShape;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;

public class StudentPayController implements Initializable {
    private dbConnection connect;
    private StudentData studentData = null;

    @FXML
    private TextField inputAmount;
    @FXML
    private Label paymentLabel;
    @FXML
    private Button cancelBtn;
    public void initialize(URL url, ResourceBundle rb){}

    public void addPayment(ActionEvent event){
        String intRegex = "[0-9]*";
        String doubleRegex = "[0-9]*\\.[0-9]{1,2}";
        String paymentString = this.inputAmount.getText();
        if((paymentString.matches(intRegex) ||
            paymentString.matches(doubleRegex))
            && !paymentString.contains("-")) {
            try {
                Connection connection = new dbConnection().getConnection();
                String updateSQL = "UPDATE students SET balance = balance - ? WHERE id = ?";
                PreparedStatement ps = connection.prepareStatement(updateSQL);
                ps.setString(1, paymentString);
                ps.setString(2, this.studentData.getID());

                ps.executeUpdate();
                ps.close();
                connection.close();
                this.paymentLabel.setText("Payment accepted!");

            } catch (SQLException e) {
                this.paymentLabel.setText("SQL exception detected.");
            }
        }
        else{
               this.paymentLabel.setText("Invalid input");
            }
        }

    public void setStudentData(StudentData studentData) {
        this.studentData = studentData;
    }

    @FXML
    private void closeWindow(ActionEvent event){
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();

    }
}
