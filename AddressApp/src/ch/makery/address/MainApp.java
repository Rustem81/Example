package ch.makery.address;


import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.makery.address.model.PersonListWrapper;
import ch.makery.address.model.person;
import ch.makery.address.view.personeditdialogcontroller;
import ch.makery.address.view.personoverviewcontroller;
import ch.makery.address.view.rootlayoutcontroller;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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
        this.primaryStage.setTitle("�������� ����������");
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
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
            
            
            // ��� ����������� ������ � �������� ���������.
            rootlayoutcontroller controller = loader.getController();
            controller.setMainApp(this);
            
            
            
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
          // �������� ��������� ��������� �������� ���� � ����������.
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
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
     * ��������� ���������� ���� ��� ��������� ������� ���������� ��������.
     * ���� ������������ ������� OK, �� ��������� ����������� � ���������������
     * ������� �������� � ������������ �������� true.
     * 
     * @param person - ������ ��������, ������� ���� ��������
     * @return true, ���� ������������ ������� OK, � ��������� ������ false.
     */
    public boolean showPersonEditDialog(person person) {
        try {
            // ��������� fxml-���� � ������ ����� �����
            // ��� ������������ ����������� ����.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            
            AnchorPane page = (AnchorPane) loader.load();

            // ������ ���������� ���� Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // ������� �������� � ����������.
            personeditdialogcontroller controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // ���������� ���������� ���� � ���, ���� ������������ ��� �� �������
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
   
   
   /**
    * ���������� preference ����� ���������, �� ����, ��������� �������� ����.
    * ���� preference ����������� �� �������, ������������ ��� ����������
    * ������������ �������. ���� preference �� ��� ������, �� ������������ null.
    * 
    * @return
    */
   public File getPersonFilePath() {
       Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
       String filePath = prefs.get("filePath", null);
       if (filePath != null) {
           return new File(filePath);
       } else {
           return null;
       }
   }

   /**
    * ����� ���� �������� ������������ �����. ���� ���� �����������
    * � �������, ����������� ��� ���������� ������������ �������.
    * 
    * @param file - ���� ��� null, ����� ������� ����
    */
   public void setPersonFilePath(File file) {
       Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
       if (file != null) {
           prefs.put("filePath", file.getPath());

           // ���������� �������� �����.
           primaryStage.setTitle("AddressApp - " + file.getName());
       } else {
           prefs.remove("filePath");

           // ���������� �������� �����.
           primaryStage.setTitle("AddressApp");
       }
   }
   
   
   
   /**
    * ��������� ���������� �� ��������� �� ���������� �����.
    * ������� ���������� �� ��������� ����� ��������.
    * 
    * @param file
    */
   public void loadPersonDataFromFile(File file) {
       try {
           JAXBContext context = JAXBContext
                   .newInstance(PersonListWrapper.class);
           Unmarshaller um = context.createUnmarshaller();

           // ������ XML �� ����� � ��������������.
           PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

           personData.clear();
           personData.addAll(wrapper.getPersons());

           // ��������� ���� � ����� � �������.
           setPersonFilePath(file);

       } catch (Exception e) { // catches ANY exception
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Could not load data");
           alert.setContentText("Could not load data from file:\n" + file.getPath());

           alert.showAndWait();
       }
   }

   /**
    * ��������� ������� ���������� �� ��������� � ��������� �����.
    * 
    * @param file
    */
   public void savePersonDataToFile(File file) {
       try {
           JAXBContext context = JAXBContext
                   .newInstance(PersonListWrapper.class);
           Marshaller m = context.createMarshaller();
           m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

           // ��������� ���� ������ �� ���������.
           PersonListWrapper wrapper = new PersonListWrapper();
           wrapper.setPersons(personData);

           // ������������ � ��������� XML � ����.
           m.marshal(wrapper, file);

           // ��������� ���� � ����� � �������.
           setPersonFilePath(file);
       } catch (Exception e) { // catches ANY exception
           Alert alert = new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Could not save data");
           alert.setContentText("Could not save data to file:\n" + file.getPath());

           alert.showAndWait();
       }
   }
}