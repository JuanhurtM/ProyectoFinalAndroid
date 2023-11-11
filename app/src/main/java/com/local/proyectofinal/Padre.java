package com.local.proyectofinal;

import java.io.Serializable;

public class Padre implements Serializable {

    private String carril;
    private String cedula;
    private String contrasena;
    private String correo;
    private String nombre;
    private String telefono;

    //Constructor
    public Padre(String carril, String cedula, String contrasena, String correo, String nombre, String telefono) {
        this.carril = carril;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.correo = correo;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    //Gets and Sets
    public String getCarril() {
        return carril;
    }

    public void setCarril(String carril) {
        this.carril = carril;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //To String

    @Override
    public String toString() {
        return "padre{" +
                "carril='" + carril + '\'' +
                ", cedula='" + cedula + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
