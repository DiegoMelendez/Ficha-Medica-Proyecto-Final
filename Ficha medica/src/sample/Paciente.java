package sample;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Diego on 15/4/2017.
 */
public class Paciente implements Serializable{
    //Atributos de la clase
    private String nombre1;
    private String apellido1;
    private String cedula1;
    private String nacimiento1;
    private String edad1;
    private String telefono1;
    private String sintomas1;
    private String fecha1;
    //Getters y Setters
    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getCedula1() {
        return cedula1;
    }

    public void setCedula1(String cedula1) {
        this.cedula1 = cedula1;
    }

    public String getNacimiento1() {
        return nacimiento1;
    }

    public void setNacimiento1(String nacimiento1) {
        this.nacimiento1 = nacimiento1;
    }

    public String getEdad1() {
        return edad1;
    }

    public void setEdad1(String edad1) {
        this.edad1 = edad1;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getSintomas1() {
        return sintomas1;
    }

    public void setSintomas1(String sintomas1) {
        this.sintomas1 = sintomas1;
    }
    public String getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    //

}
