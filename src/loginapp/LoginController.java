package loginapp;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private ComboBox<option> comboBox = new ComboBox<>();
    @FXML
    private Button loginButton;

    public void initialize(URL url, ResourceBundle rb){
        if(this.loginModel.isConnected()){
            this.dbstatus.setText("Connected!!!");
        }
        else{
            this.dbstatus.setText("Not connected!!!");
        }

        this.comboBox.setItems(FXCollections.observableArrayList(option.values()));
    }
}
