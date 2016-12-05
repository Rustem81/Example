package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.person;

public class personoverviewcontroller {
    @FXML
    private TableView<person> persontable;
    @FXML
    private TableColumn<person, String> firstnamecolumn;
    @FXML
    private TableColumn<person, String> lastnamecolumn;

    @FXML
    private Label firstnamelabel;
    @FXML
    private Label lastnamelabel;
    @FXML
    private Label streetlabel;
    @FXML
    private Label postalcodelabel;
    @FXML
    private Label citylabel;
    @FXML
    private Label birthdaylabel;

    // Ссылка на главное приложение.
    private MainApp mainapp;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public personoverviewcontroller() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        firstnamecolumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastnamecolumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainapp = mainapp;

        // Добавление в таблицу данных из наблюдаемого списка
        persontable.setItems(mainApp.getPersonData());
    }
    
    
    /**
     * Заполняет все текстовые поля, отображая подробности об адресате.
     * Если указанный адресат = null, то все текстовые поля очищаются.
     * 
     * @param person — адресат типа Person или null
     */
    private void showPersonDetails(person person) {
        if (person != null) {
            // Заполняем метки информацией из объекта person.
            firstnamelabel.setText(person.getFirstName());
            lastnamelabel.setText(person.getLastName());
            streetlabel.setText(person.getStreet());
            postalcodelabel.setText(Integer.toString(person.getPostalCode()));
            citylabel.setText(person.getCity());

            // TODO: Нам нужен способ для перевода дня рождения в тип String! 
            // birthdayLabel.setText(...);
        } else {
            // Если Person = null, то убираем весь текст.
            firstnamelabel.setText("");
            lastnamelabel.setText("");
            streetlabel.setText("");
            postalcodelabel.setText("");
            citylabel.setText("");
            birthdaylabel.setText("");
        }
    }
}