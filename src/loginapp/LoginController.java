package loginapp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import student.StudentController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    LoginModel loginModel = new LoginModel();
    @FXML
    private Label dbstatus;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<option> comboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;

    public void initialize(URL url, ResourceBundle rb){
        if(this.loginModel.isConnected()){
            this.dbstatus.setText("Connected!!!");
        }
        else{
            this.dbstatus.setText("Not connected!!!");
        }

        this.comboBox.setItems(FXCollections.observableArrayList(option.values()));
    }

    @FXML
    public void Login(ActionEvent event){
        try{
            String userName = this.userName.getText();
            String password = this.password.getText();
            String opt = this.comboBox.getValue().toString();
            if(this.loginModel.isLogin(userName, password, opt)){
                Stage stage = (Stage) this.loginButton.getScene().getWindow();
                stage.close();
                switch (this.comboBox.getValue().toString()){
                    case "Admin": {
                        adminLogin();
                        break;
                    }
                    case "Student":{
                        studentLogin();
                        break;
                    }
                }
            }else{
                this.loginStatus.setText("Login FAILD");
            }

        }catch (Exception localException){
            localException.getStackTrace();
        }

    }

    public void studentLogin(){
        try{
            Stage studentStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            // load fxml file as root
            Pane root = (Pane) loader.load(getClass().getResource("/student/Student.fxml").openStream());
            StudentController studentController = (StudentController) loader.getController();

            //put root onto a scene
            Scene scene = new Scene(root);
            studentStage.setScene(scene);
            studentStage.setTitle("Student Dashboard");
            studentStage.show();

        }catch(IOException ex){
            ex.getStackTrace();
        }
    }

    public void adminLogin(){
        try{
            Stage adminStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            // load fxml file as root
            Pane root = (Pane) loader.load(getClass().getResource("/admin/Admin.fxml").openStream());

            AdminController adminController = (AdminController) loader.getController();

            //put root onto a scene
            Scene scene = new Scene(root);

            //put a scene on a stage
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.show();

        }catch(IOException ex){
            ex.getStackTrace();
        }
    }
}
