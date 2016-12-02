package ch.makery.address;


import java.io.IOException;
import ch.makery.address.model.person;
import ch.makery.address.view.personoverviewcontroller;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<person> personData = FXCollections.observableArrayList();

    
    @Override
    public void start(Stage primaryStage) {
    	
    	
    	personData.add(new person("Hans", "Muster"));
    	personData.add(new person("Ruth", "Mueller"));
        personData.add(new person("Heinz", "Kurz"));
        personData.add(new person("Cornelia", "Meier"));
        personData.add(new person("Werner", "Meyer"));
        personData.add(new person("Lydia", "Kunz"));
        personData.add(new person("Anna", "Best"));
        personData.add(new person("Stefan", "Meier"));
        personData.add(new person("Martin", "Mueller"));
    	
    	
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp_Change");
        initRootLayout();
        showPersonOverview();
    }
   
    /**
     * �������������� �������� �����.
     */
    public void initRootLayout() {
        try {
            // ��������� �������� ����� �� fxml �����.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/rootlayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // ���������� �����, ���������� �������� �����.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ���������� � �������� ������ �������� �� ���������.
     */
    public void showPersonOverview() {
        try {
            // ��������� �������� �� ���������.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/personoverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            // �������� �������� �� ��������� � ����� ��������� ������.
            rootLayout.setCenter(personOverview);
            
         // ��� ����������� ������ � �������� ����������.
            personoverviewcontroller controller = loader.getController();
            controller.setMainApp(this);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ���������� ������� �����.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
   

    public static void main(String[] args) {
        launch(args);
    }
    
   public ObservableList<person> getPersonData() {
        return personData;
    }
}