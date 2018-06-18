package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminData {
    private final StringProperty loginName;
    private final StringProperty password;
    private final StringProperty devision;

    public AdminData(String loginName, String password, String devision){
        this.loginName = new SimpleStringProperty(loginName);
        this.password = new SimpleStringProperty(password);
        this.devision = new SimpleStringProperty(devision);
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
}
