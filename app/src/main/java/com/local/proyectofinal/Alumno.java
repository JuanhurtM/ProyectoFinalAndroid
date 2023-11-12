package com.local.proyectofinal;

import java.io.Serializable;

public class Alumno implements Serializable {

    private String carril;
    private String grado;
    private String nombre;
    private String padre;

    public Alumno() {
        this.carril = carril;
        this.grado = grado;
        this.nombre = nombre;
        this.padre = padre;
    }

    //Gets and Sets

    public String getCarril() {
        return carril;
    }

    public void setCarril(String carril) {
        this.carril = carril;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    //To String

    @Override
    public String toString() {
        return "Alumno{" +
                "carril='" + carril + '\'' +
                ", grado='" + grado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", padre='" + padre + '\'' +
                '}';
    }
}
