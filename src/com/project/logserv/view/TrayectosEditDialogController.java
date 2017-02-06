package com.project.logserv.view;

import org.controlsfx.dialog.Dialogs;

import com.project.logserv.model.Trayecto;
import com.project.logserv.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TrayectosEditDialogController {
	 	@FXML
	    private TextField origenField;
	    @FXML
	    private TextField destinoField;
	    @FXML
	    private TextField kilometroField;
	    @FXML
	    private TextField fechaSalidaField;
	    @FXML
	    private TextField fechaLlegadaField;
	   
	    private Stage dialogStage;
	    private Trayecto tr;
	    private boolean okClicked = false;

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    }

	    /**
	     * Sets the stage of this dialog.
	     * 
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	        this.dialogStage.getIcons().add(new Image("file:resources/images/1486373276_compose.png"));
	    }

	    /**
	     * Sets the person to be edited in the dialog.
	     * 
	     * @param person
	     */
	    public void setTrayecto(Trayecto tr) {
	        this.tr = tr;

	        origenField.setText(tr.getOrigen());
	        destinoField.setText(tr.getDestino());
	        kilometroField.setText(Integer.toString(tr.getKilometros()));
	        
	        fechaSalidaField.setText(DateUtil.format(tr.getFechaSalida()));
	        fechaSalidaField.setPromptText("dd.mm.yyyy");
	        fechaLlegadaField.setText(DateUtil.format(tr.getFechaLlegada()));
	        fechaLlegadaField.setPromptText("dd.mm.yyyy");
	    }

	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     * 
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Called when the user clicks ok.
	     */
	    @FXML
	    private void handleOk() {
	        if (isInputValid()) {
	            tr.setOrigen(origenField.getText());
	            tr.setDestino(destinoField.getText());
	            tr.setKilometros(Integer.parseInt(kilometroField.getText()));
	            
	            tr.setFechaSalida(DateUtil.parse(fechaSalidaField.getText()));
	            tr.setFechaLlegada(DateUtil.parse(fechaLlegadaField.getText()));

	            okClicked = true;
	            dialogStage.close();
	        }
	    }

	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

	    /**
	     * Validates the user input in the text fields.
	     * 
	     * @return true if the input is valid
	     */
	    private boolean isInputValid() {
	        String errorMessage = "";

	        if (origenField.getText() == null || origenField.getText().length() == 0) {
	            errorMessage += "No valid origen!\n"; 
	        }
	        if (destinoField.getText() == null || destinoField.getText().length() == 0) {
	            errorMessage += "No valid destino!\n"; 
	        }
	       
	        if (kilometroField.getText() == null || kilometroField.getText().length() == 0) {
	            errorMessage += "No valid postal code!\n"; 
	        } else {
	            // try to parse the kilometro code into an int.
	            try {
	                Integer.parseInt(kilometroField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "No valid kilometro (must be an integer)!\n"; 
	            }
	        }

	        if (fechaSalidaField.getText() == null || fechaSalidaField.getText().length() == 0) {
	            errorMessage += "No valid Fecha Salida!\n";
	        } else {
	            if (!DateUtil.validDate(fechaSalidaField.getText())) {
	                errorMessage += "No valid Fecha Salida. Use the format dd.mm.yyyy!\n";
	            }
	        }
	        if (fechaLlegadaField.getText() == null || fechaLlegadaField.getText().length() == 0) {
	            errorMessage += "No valid fecha Llegada!\n";
	        } else {
	            if (!DateUtil.validDate(fechaLlegadaField.getText())) {
	                errorMessage += "No valid Fecha Llegada. Use the format dd.mm.yyyy!\n";
	            }
	        }
	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Dialogs.create()
	                .title("Invalid Fields")
	                .masthead("Please correct invalid fields")
	                .message(errorMessage)
	                .showError();
	            return false;
	        }
	    }

}
