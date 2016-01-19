package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model class for a Person.
 *
 * @author Gonzalo Rodriguez
 * @version 1.3.0
 */
public class Person {

    private final StringProperty nombre;
    private final StringProperty apellido;
    private final IntegerProperty telefono;
    private final StringProperty email;
    private final StringProperty direccion;
    private final ObjectProperty<LocalDate> nacimiento;

    /**
     * Default constructor.
     */
    public Person() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param nombre
     * @param apellido
     */
    public Person(String nombre, String apellido) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty("tu apellido");

        // Some initial dummy data, just for convenient testing.
        this.telefono = new SimpleIntegerProperty(12345789);
        this.email = new SimpleStringProperty("gmail@gmail.com");
        this.direccion = new SimpleStringProperty("alguna direccion");
        this.nacimiento = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty NombreProperty() {
        return nombre;
    }

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public StringProperty ApellidoProperty() {
        return apellido;
    }

    public int getTelefono() {
        return telefono.get();
    }

    public void setTelefono(int telefono) {
        this.telefono.set(telefono);
    }

    public IntegerProperty TelefonoProperty() {
        return telefono;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty EmailProperty() {
        return email;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty DireccionProperty() {
        return direccion;
    }

    public LocalDate getNacimiento() {
        return nacimiento.get();
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento.set(nacimiento);
    }

    public ObjectProperty<LocalDate> NacimientoProperty() {
        return nacimiento;
    }

    
}