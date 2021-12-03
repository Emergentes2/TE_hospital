
package com.emergentes.modelo;

public class Medico {
    private int id;
    private String nombre;
    private String especialidad;
    private String celular;

    public Medico() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Medico{" + "id=" + id + ", nombre=" + nombre + ", especialidad=" + especialidad + ", celular=" + celular + '}';
    }
    
    
    
}
