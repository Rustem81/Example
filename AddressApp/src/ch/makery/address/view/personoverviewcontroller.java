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
     * ����������, ����� ������������ ������� �� ������ ��������.
     */
    

    // ������ �� ������� ����������.
    private MainApp mainApp;

    /**
     * �����������.
     * ����������� ���������� ������ ������ initialize().
     */
    public personoverviewcontroller() {
    }

    /**
     * ������������� ������-�����������. ���� ����� ���������� �������������
     * ����� ����, ��� fxml-���� ����� ��������.
     */
    @FXML
    private void initialize() {
        // ������������� ������� ��������� � ����� ���������.
        firstnamecolumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastnamecolumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        

        // ������� �������������� ���������� �� ��������.
        showPersonDetails(null);

        // ������� ��������� ������, � ��� ��������� ����������
        // �������������� ���������� �� ��������.
        persontable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * ���������� ������� �����������, ������� ��� �� ���� ������.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // ���������� � ������� ������ �� ������������ ������
        persontable.setItems(mainApp.getPersonData());
    }
    
    
    /**
     * ��������� ��� ��������� ����, ��������� ����������� �� ��������.
     * ���� ��������� ������� = null, �� ��� ��������� ���� ���������.
     * 
     * @param person � ������� ���� Person ��� null
     */
    private void showPersonDetails(person person) {
        if (person != null) {
            // ��������� ����� ����������� �� ������� person.
            firstnamelabel.setText(person.getFirstName());
            lastnamelabel.setText(person.getLastName());
            streetlabel.setText(person.getStreet());
            postalcodelabel.setText(Integer.toString(person.getPostalCode()));
            citylabel.setText(person.getCity());
            birthdaylabel.setText(DateUtil.format(person.getBirthday()));

            // TODO: ��� ����� ������ ��� �������� ��� �������� � ��� String! 
            // birthdayLabel.setText(...);
        } else {
            // ���� Person = null, �� ������� ���� �����.
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
            // ������ �� �������.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * ����������, ����� ������������ ������� �� ������ New...
     * ��������� ���������� ���� � �������������� ����������� ������ ��������.
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
     * ����������, ����� ������������ ������� �� ������ Edit...
     * ��������� ���������� ���� ��� ��������� ���������� ��������.
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
            // ������ �� �������.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}