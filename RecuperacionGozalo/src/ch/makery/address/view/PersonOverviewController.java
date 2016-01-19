package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ch.makery.address.control.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> nombreColumn;
    @FXML
    private TableColumn<Person, String> apellidoColumn;

    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidoLabel;
    @FXML
    private Label telefonoLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label nacimientoLabel;

    // Reference to the main application.
    private MainApp mainApp;

 PersonOverviewController() {
    }

    /**
     * inicializo el controlador de la clase automaticamente cuando llamo al fxml
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	nombreColumn.setCellValueFactory(
                cellData -> cellData.getValue().NombreProperty());
    	apellidoColumn.setCellValueFactory(
                cellData -> cellData.getValue().ApellidoProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            nombreLabel.setText(person.getNombre());
            apellidoLabel.setText(person.getApellido());
            telefonoLabel.setText(Integer.toString(person.getTelefono()));
            emailLabel.setText(person.getEmail());
            direccionLabel.setText(person.getDireccion());
            nacimientoLabel.setText(DateUtil.format(person.getNacimiento()));

            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            nombreLabel.setText("");
            apellidoLabel.setText("");
            telefonoLabel.setText("");
            emailLabel.setText("");
            direccionLabel.setText("");
            nacimientoLabel.setText("");
        }
    }
    /**
     * llamo y otrorgo la funcion de borrar datos al boton borrar
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // si no tengo nada seleccionado
            Dialog.create()
                .title("No seleccionado")
                .masthead("No ha selecionado nigun contacto")
                .message("Por favor seleccione un contacto.")
                .showWarning();
        }
    }
    
    /**
     * llamo y otrorgo la funcion de crear nuevos contactos
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * otorgada la funcion de editar
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Dialog.create()
                .title("No Selection")
                .masthead("No Person Selected")
                .message("Please select a person in the table.")
                .showWarning();
        }
    }
    
    
}