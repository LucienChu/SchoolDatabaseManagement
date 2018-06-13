package admin;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import student.StudentController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private TextField id;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField emailAddress;
    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private TableView<StudentData> studentTable = new TableView<StudentData>();
    @FXML
    private TableColumn<StudentData, String> idColumn;
    @FXML
    private TableColumn<StudentData, String> firstNameColumn;
    @FXML
    private TableColumn<StudentData, String> lastNameColumn;
    @FXML
    private TableColumn<StudentData, String> emailColumn;
    @FXML
    private TableColumn<StudentData, String> dateOfBirthColumn;
    private ObservableList<StudentData> studentData = null;
    private dbConnection dbc;

    private String sql = "SELECT * FROM students";

    public void initialize(URL url, ResourceBundle rb){
        this.dbc = new dbConnection();
    }

    @FXML
    private void LoadStudnetData(ActionEvent event){
        try{
            Connection connect = dbConnection.getConnection();
            this.studentData = FXCollections.observableArrayList();

            ResultSet rs = connect.createStatement().executeQuery(sql);
            while (rs.next()){
                this.studentData.add(new StudentData(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        }catch (SQLException e){
            System.err.println("Error " + e);
        }

        // display data on the table
        this.idColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("ID"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("lastName"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("email"));
        this.dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<StudentData, String>("dateOfBirth"));

        this.studentTable.setItems(null);
        this.studentTable.setItems(studentData);

        studentTable.setRowFactory(tv->{
            TableRow<StudentData> row = new TableRow<>();
            row.setOnMouseClicked(clickEvent ->{
                if(clickEvent.getClickCount() == 2 && (!row.isEmpty())){
                    System.out.println("Okay, row is clicked");
                    //continue here, load an fxml file, put clicked row data there
                    //enable modification.

                    try{
                        Stage studentStage = new Stage();
                        FXMLLoader loader = new FXMLLoader();
                        // load fxml file as root
                        Pane root = (Pane) loader.load(getClass().getResource("/Admin/modifyData.fxml").openStream());
                        ModifyDataController modifyDataController = (ModifyDataController) loader.getController();

                        //put root onto a scene
                        Scene scene = new Scene(root);
                        studentStage.setScene(scene);
                        studentStage.setTitle("Student Dashboard");
                        studentStage.show();

                    }catch(IOException ex){
                        ex.getStackTrace();
                    }
                }
            });
            return row;
        });
    }

    //add data to database
    @FXML
    private void addStudent(ActionEvent event){
        String insertSQL = "INSERT INTO students(id, fName, lName, email, DOB) VALUES(?,?,?,?,?)";
        try{
            Connection connect = new dbConnection().getConnection();
            PreparedStatement ps = connect.prepareStatement(insertSQL);

            ps.setString(1, this.id.getText());
            ps.setString(2, this.firstName.getText());
            ps.setString(3, this.lastName.getText());
            ps.setString(4, this.emailAddress.getText());
            ps.setString(5, this.dateOfBirth.getEditor().getText());

            ps.execute();
            ps.close();
        }catch(SQLException e){
            System.err.print("Error " + e);
        }
    }

    //clear form after adding student
    @FXML
    private void clearFields(ActionEvent event){
        this.id.setText("");
        this.firstName.setText("");
        this.lastName.setText("");
        this.emailAddress.setText("");
        this.dateOfBirth.setValue(null);
    }
}
