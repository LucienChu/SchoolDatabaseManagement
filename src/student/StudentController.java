package student;

import admin.StudentData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;

public class StudentController implements Initializable {
    @FXML
    private TextField studentId, studentFirstName, studentLastName, email, dateOfBirth, currentBalance;
    private StudentData studentData = null;
    public void initialize(URL url, ResourceBundle rb){}

    public void setStudentData(StudentData studentData) {
        this.studentData = studentData;
    }
    public void setCurrentStudent (StudentData studentData){
        this.studentData = new StudentData(
                studentData.getID(),
                studentData.getFirstName(),
                studentData.getLastName(),
                studentData.getEmail(),
                studentData.getDateOfBirth(),
                parseDouble(studentData.getBalance()));
    }

    public void initializeStudentData(){
        this.studentId.setDisable(true);
        this.studentFirstName.setDisable(true);
        this.studentLastName.setDisable(true);
        this.email.setDisable(true);
        this.dateOfBirth.setDisable(true);
        this.currentBalance.setDisable(true);
        this.studentId.setText(this.studentData.getID());
        this.studentFirstName.setText(this.studentData.getID());
        this.studentLastName.setText(this.studentData.getLastName());
        this.email.setText(this.studentData.getEmail());
        this.dateOfBirth.setText(this.studentData.getDateOfBirth());
        this.currentBalance.setText(this.studentData.getBalance());
    }
}
