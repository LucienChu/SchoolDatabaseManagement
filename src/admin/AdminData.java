package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminData {
    private final StringProperty loginName;
    private final StringProperty password;

    public AdminData(String loginName, String password){
        this.loginName = new SimpleStringProperty(loginName);
        this.password = new SimpleStringProperty(password);
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
