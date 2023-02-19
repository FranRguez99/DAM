package Practicas.act3_objetosUDP;

import java.io.Serializable;

public class Alumno implements Serializable {
    String idalumno;
    String nombre;
    Curso curso;
    int nota;

    public Alumno () {
        idalumno = "0";
        nombre = "No existe";
        curso = new Curso();
        nota = 0;
    }

    public Alumno(String idalumno, String nombre, Curso curso, int nota) {
        this.idalumno = idalumno;
        this.nombre = nombre;
        this.curso = curso;
        this.nota = nota;
    }

    public String getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(String idalumno) {
        this.idalumno = idalumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
