package com.project.logserv.view;

import org.controlsfx.dialog.Dialogs;

import com.project.logserv.MainApp;
import com.project.logserv.model.Trayecto;
import com.project.logserv.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TrayectosOverviewController {
	@FXML
    private TableView<Trayecto> trTable;
    @FXML
    private TableColumn<Trayecto, String> origenColumn;
    @FXML
    private TableColumn<Trayecto, String> destinoColumn;
    
    @FXML
    private Label origenLabel;
    @FXML
    private Label destinoLabel;
    @FXML
    private Label kilometrosLabel;
    @FXML
    private Label salidaLabel;
    @FXML
    private Label llegadaLabel;
    
 // Reference to the main application.
    private MainApp mainApp;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TrayectosOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the routes table with the three columns.
    	
        origenColumn.setCellValueFactory(cellData -> cellData.getValue().origenProperty());
        destinoColumn.setCellValueFactory(cellData -> cellData.getValue().destinoProperty());
        
        // Clear person details.
        showTrayectosDetails(null);
        // Listen for selection changes and show the person details when changed.
        //lambda expression
        trTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTrayectosDetails(newValue));
    }
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        trTable.setItems(mainApp.getPersonData());
    }
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showTrayectosDetails(Trayecto tr) {
        if (tr != null) {
            // Fill the labels with info from the trayecto object.
            origenLabel.setText(tr.getOrigen());
            destinoLabel.setText(tr.getDestino());
            kilometrosLabel.setText(Integer.toString(tr.getKilometros()));            
            salidaLabel.setText(DateUtil.format(tr.getFechaSalida()));
            llegadaLabel.setText(DateUtil.format(tr.getFechaLlegada()));
            
        } else {
            // Person is null, remove all the text.
            origenLabel.setText("");
            destinoLabel.setText("");
            kilometrosLabel.setText("");
            salidaLabel.setText("");
            llegadaLabel.setText("");
        }
    }
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Trayecto tempTr = new Trayecto();
        boolean okClicked = mainApp.showTrayectosEditDialog(tempTr);
        if (okClicked) {
            mainApp.getPersonData().add(tempTr);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Trayecto selectedTr = trTable.getSelectionModel().getSelectedItem();
        if (selectedTr != null) {
            boolean okClicked = mainApp.showTrayectosEditDialog(selectedTr);
            if (okClicked) {
                showTrayectosDetails(selectedTr);
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No Trayecto Selected")
                .message("Please select a Trayecto in the table.")
                .showWarning();
        }
    }

}
