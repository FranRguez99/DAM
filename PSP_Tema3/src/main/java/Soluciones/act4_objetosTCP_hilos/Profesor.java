package Soluciones.act4_objetosTCP_hilos;

import java.io.Serializable;
@SuppressWarnings("serial")

public class Profesor implements Serializable {

    int idprofesor;
    String nombre;
    Asignatura[] asignaturas;
    Especialidad esp;

    public Profesor () {
        idprofesor = 0;
        nombre = "";
        asignaturas = new Asignatura[0];
        esp = new Especialidad();
    }

    public Profesor(int idprofesor, String nombre, Asignatura[] asignaturas, Especialidad esp) {
        this.idprofesor = idprofesor;
        this.nombre = nombre;
        this.asignaturas = asignaturas;
        this.esp = esp;
    }

    public int getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(int idprofesor) {
        this.idprofesor = idprofesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Asignatura[] getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Asignatura[] asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Especialidad getEsp() {
        return esp;
    }

    public void setEsp(Especialidad esp) {
        this.esp = esp;
    }
}
