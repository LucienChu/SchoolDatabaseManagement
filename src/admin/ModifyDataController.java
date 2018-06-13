package admin;

import dbUtil.dbConnection;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyDataController implements Initializable {
    private dbConnection dbc;

    public void initialize(URL url, ResourceBundle rb){
        this.dbc = new dbConnection();
    }

}
