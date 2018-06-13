package admin;

import dbUtil.dbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyDataController implements Initializable {
    private dbConnection dbc;

    @FXML private TextField id,firstName, lastName, email;
    @FXML private DatePicker dateOfBirth;
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

}
