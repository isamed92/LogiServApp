package com.project.logserv;

import java.io.IOException;
import com.project.logserv.model.Trayecto;
import com.project.logserv.view.TrayectosEditDialogController;
import com.project.logserv.view.TrayectosOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private ObservableList<Trayecto> routeData = FXCollections.observableArrayList();
    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("LogiServ");
        
     // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/1486373035_truck.png"));
        initRootLayout();
        showTrayectosOverview();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the routes overview inside the root layout.
     */
    public void showTrayectosOverview() {
        try {
            // Load route overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TrayectosOverview.fxml"));
            AnchorPane trOverview = (AnchorPane) loader.load();

            // Set route overview into the center of root layout.
            rootLayout.setCenter(trOverview);
            
            // Give the controller access to the main app.
            TrayectosOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
    	routeData.add(new Trayecto("La cocha", "Tucuman"));
    	routeData.add(new Trayecto("Famailla", "Fronterita"));
    	routeData.add(new Trayecto("La cocha", "Banda del Rio Salí"));
    	routeData.add(new Trayecto("Fronterita", "Tucuman"));
    	routeData.add(new Trayecto("Banda del Rio Salí", "Trancas"));
    	routeData.add(new Trayecto("Tucuman", "Famailla"));
    	routeData.add(new Trayecto("La cocha", "Fronterita"));
    	routeData.add(new Trayecto("Tucuman", "Banda del Rio Salí"));
    	routeData.add(new Trayecto("Famailla", "Trancas"));
    	
    }
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Trayecto> getPersonData() {
        return routeData;
    }
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showTrayectosEditDialog(Trayecto tr) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TrayectosEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Trayecto");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            TrayectosEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTrayecto(tr);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
	public static void main(String[] args) {
		launch(args);
		
	}
}
