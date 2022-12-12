package models;

public class Asignacion {

    private int eleccAl;
    private int eleccEmpresa;
    private int eleccTutor;

    public Asignacion(){}

    public Asignacion(int eleccAl, int eleccEmpresa, int eleccTutor){
        this.eleccAl = eleccAl;
        this.eleccEmpresa = eleccEmpresa;
        this.eleccTutor = eleccTutor;
    }

    public int getEleccAl() {
        return eleccAl;
    }

    public void setEleccAl(int eleccAl) {
        this.eleccAl = eleccAl;
    }

    public int getEleccEmpresa() {
        return eleccEmpresa;
    }

    public void setEleccEmpresa(int eleccEmpresa) {
        this.eleccEmpresa = eleccEmpresa;
    }

    public int getEleccTutor() {
        return eleccTutor;
    }

    public void setEleccTutor(int eleccTutor) {
        this.eleccTutor = eleccTutor;
    }
}
