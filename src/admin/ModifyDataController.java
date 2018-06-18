package admin;

import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ModifyDataController implements Initializable {
    private dbConnection dbc;

    @FXML private TextField id,firstName, lastName, email;
    @FXML private DatePicker dateOfBirth;
    @FXML private Label label;
    public void initialize(URL url, ResourceBundle rb){
        this.dbc = new dbConnection();
    }

    public void initialStudentData(StudentData data){
        this.id.setText(data.getID());
        this.id.setDisable(true);
        this.firstName.setText(data.getFirstName());
        this.lastName.setText(data.getLastName());
        this.email.setText(data.getEmail());
        this.dateOfBirth.getEditor().setText(data.getDateOfBirth());
    }

    @FXML
    private void clearFields(ActionEvent event){
        this.email.setText("");
        this.firstName.setText("");
        this.lastName.setText("");
        this.dateOfBirth.setValue(null);
    }

    @FXML
    private void updateData(ActionEvent event){
        String updateSQL = "UPDATE students SET lName = ?, fName = ?, email = ?, DOB = ? WHERE id = ?";
        try{
            Connection connect = new dbConnection().getConnection();
            PreparedStatement ps = connect.prepareStatement(updateSQL);

            ps.setString(1, this.firstName.getText());
            ps.setString(2, this.lastName.getText());
            ps.setString(3, this.email.getText());
            ps.setString(4, this.dateOfBirth.getEditor().getText());
            ps.setString(5, this.id.getText());

            ps.execute();
            ps.close();

            this.label.setText("Update is done!");


        }catch (SQLException e){
            this.label.setText("Error detected, please try again!");
        }
    }
    @FXML
    private Button cancelBtn;

    @FXML
    private void closeWindow(ActionEvent event){
        Stage stage = (Stage)cancelBtn.getScene().getWindow();
        stage.close();

    }
}
