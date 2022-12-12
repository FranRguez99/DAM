package models;

public class Alumno {
    int cod;
    String dni;
    String nombre;
    String apellidos;
    String fechanacimiento;

    public Alumno(int cod, String dni, String nombre, String apellidos, String fechanacimiento) {
        this.cod = cod;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechanacimiento = fechanacimiento;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
}
