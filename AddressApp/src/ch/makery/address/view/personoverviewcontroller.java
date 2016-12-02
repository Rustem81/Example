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

    // ������ �� ������� ����������.
    private MainApp mainapp;

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
    }

    /**
     * ���������� ������� �����������, ������� ��� �� ���� ������.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainapp = mainapp;

        // ���������� � ������� ������ �� ������������ ������
        persontable.setItems(mainApp.getPersonData());
    }
}