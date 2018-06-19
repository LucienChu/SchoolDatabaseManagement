package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminData {
    private final StringProperty loginName;
    private final StringProperty password;
    private final StringProperty division;

    public AdminData(String loginName, String password, String division){
        this.loginName = new SimpleStringProperty(loginName);
        this.password = new SimpleStringProperty(password);
        this.division = new SimpleStringProperty(division);
    }



    public String getLoginName() {
        return loginName.get();
    }

    public StringProperty loginNameProperty() {
        return loginName;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public String getDivision(){return division.get();}

    public StringProperty divisionProperty(){return division;}
}
