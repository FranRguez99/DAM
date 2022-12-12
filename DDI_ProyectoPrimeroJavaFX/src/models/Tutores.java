package models;

public class Tutores {
    int cod;
    String nombreCompleto;
    String correoElectronico;
    String telefono;

    public Tutores(int cod, String nombreCompleto, String CorreoElectronico, String Telefono) {
        this.cod = cod;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = CorreoElectronico;
        this.telefono = Telefono;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
