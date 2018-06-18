package admin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.management.StringValueExp;

public class StudentData {
    private final StringProperty ID;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty dateOfBirth;
    private final StringProperty balance;

    public StudentData(String id, String firstName, String lastName, String email, String birthDate, double balance){
        this.ID = new SimpleStringProperty(id); //read the documentation
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.dateOfBirth = new SimpleStringProperty(birthDate);
        this.balance = new SimpleStringProperty(String.valueOf(balance));
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public String getBalance(){ return this.balance.get();}

    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public void setBalance(double balance){this.balance.set(String.valueOf(balance));}
}
