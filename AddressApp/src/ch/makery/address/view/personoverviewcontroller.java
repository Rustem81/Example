package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.person;
import ch.makery.address.util.DateUtil;

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
    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    

    // Ссылка на главное приложение.
    private MainApp mainApp;

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
        
        

        // Очистка дополнительной информации об адресате.
        showPersonDetails(null);

        // Слушаем изменения выбора, и при изменении отображаем
        // дополнительную информацию об адресате.
        persontable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

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
            birthdaylabel.setText(DateUtil.format(person.getBirthday()));

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
    
    @FXML
    private void handleDeletePerson() {
    	
    	int selectedIndex = persontable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            persontable.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Вызывается, когда пользователь кликает по кнопке New...
     * Открывает диалоговое окно с дополнительной информацией нового адресата.
     */
    @FXML
    private void handleNewPerson() {
        person tempPerson = new person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void handleEditPerson() {
        person selectedPerson = persontable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}