package ch.makery.address;


import java.io.IOException;
import ch.makery.address.model.person;
import ch.makery.address.view.personeditdialogcontroller;
import ch.makery.address.view.personoverviewcontroller;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        this.primaryStage.setTitle("Тестовое приложение");
        this.primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
        initRootLayout();
        showPersonOverview();
    }
   
    /**
     * Инициализирует корневой макет.
     */
    public void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/rootlayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Показывает в корневом макете сведения об адресатах.
     */
    public void showPersonOverview() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/personoverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(personOverview);
            
         // Даём контроллеру доступ к главному приложению.
            personoverviewcontroller controller = loader.getController();
            controller.setMainApp(this);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Открывает диалоговое окно для изменения деталей указанного адресата.
     * Если пользователь кликнул OK, то изменения сохраняются в предоставленном
     * объекте адресата и возвращается значение true.
     * 
     * @param person - объект адресата, который надо изменить
     * @return true, если пользователь кликнул OK, в противном случае false.
     */
    public boolean showPersonEditDialog(person person) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            personeditdialogcontroller controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Возвращает главную сцену.
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