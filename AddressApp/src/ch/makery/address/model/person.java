package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *  ласс-модель дл€ адресата (Person).
 *
 */
public class person {

    private final StringProperty  firstname;
    private final StringProperty  lastname;
    private final StringProperty  street;
    private final IntegerProperty postalcode;
    private final StringProperty  city;
    private final ObjectProperty<LocalDate> birthday;

    /**
     *  онструктор по умолчанию.
     */
    public person() {
        this(null, null);
    }

    /**
     *  онструктор с некоторыми начальными данными.
     * 
     * @param firstName
     * @param lastName
     */
    public person(String firstName, String lastName) {
        this.firstname = new SimpleStringProperty(firstName);
        this.lastname = new SimpleStringProperty(lastName);

        //  акие-то фиктивные начальные данные дл€ удобства тестировани€.
        this.street = new SimpleStringProperty("кака€-то улица");
        this.postalcode = new SimpleIntegerProperty(1234);
        this.city = new SimpleStringProperty("какой-то город");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getFirstName() {
        return firstname.get();
    }

    public void setFirstName(String firstName) {
        this.firstname.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstname;
    }

    public String getLastName() {
        return lastname.get();
    }

    public void setLastName(String lastName) {
        this.lastname.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastname;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty streetProperty() {
        return street;
    }

    public int getPostalCode() {
        return postalcode.get();
    }

    public void setPostalCode(int postalCode) {
        this.postalcode.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return postalcode;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
}