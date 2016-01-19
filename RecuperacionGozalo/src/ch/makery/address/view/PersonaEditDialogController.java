package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.Dialog;

import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

public class PersonaEditDialogController {

    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField nacimientoField;


    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Iinicializo el controlador de la clase automaticamente cuando llamo al fxml
     */
    @FXML
    private void initialize() {
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setPerson(Person person) {
        this.person = person;

        nombreField.setText(person.getNombre());
        apellidoField.setText(person.getApellido());
        telefonoField.setText(Integer.toString(person.getTelefono()));
        emailField.setText(person.getEmail());
        telefonoField.setText(person.getDireccion());
        nacimientoField.setText(DateUtil.format(person.getNacimiento()));
        nacimientoField.setPromptText("dd.mm.yyyy");
    }


    public boolean isOkClicked() {
        return okClicked;
    }
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setNombre(nombreField.getText());
            person.setApellido(apellidoField.getText());
            person.setTelefono(Integer.parseInt(telefonoField.getText()));
            person.setEmail(emailField.getText());
            person.setDireccion(direccionField.getText());
            person.setNacimiento(DateUtil.parse(nacimientoField.getText()));

           
            
            okClicked = true;
            dialogStage.close();
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * valido el uso de los textfield
     * 
     * 
     * 
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "nombre no valido\n"; 
        }
        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
            errorMessage += "Apellido no valido\n"; 
        }
        if (telefonoField.getText() == null || telefonoField.getText().length() == 0) {
            errorMessage += "telefono no valido\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(telefonoField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valido el telefono debe ser intenger\n"; 
            }
        }
        
        if (emailField.getText() == null ||emailField.getText().length() == 0) {
            errorMessage += "email no valido!\n"; 
        }

        if (direccionField.getText() == null || direccionField.getText().length() == 0) {
            errorMessage += "direccion no valida!\n"; 
        }

        if (nacimientoField.getText() == null || nacimientoField.getText().length() == 0) {
            errorMessage += "cumpleaños no validoy!\n";
        } else {
            if (!DateUtil.validDate(nacimientoField.getText())) {
                errorMessage += "Nacimiento o cumpleaños no valido use el formato dd.mm.yy\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialog.create()
                .title("Invalid Fields")
                .masthead("Please correct invalid fields")
                .message(errorMessage)
                .showError();
            return false;
        }
    }
}