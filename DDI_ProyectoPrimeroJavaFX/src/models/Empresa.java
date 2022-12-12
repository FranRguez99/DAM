package models;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Empresa {
    private int codEmpresa;
    private String cif;
    private String nombre;
    private String direccion;
    private String cp;
    private String localidad;
    private String jornada;
    private String modalidad;
    private String mail;
    private String dniRL;
    private String nombreRL;
    private String apRL;
    private String dniTL;
    private String nombreTL;
    private String apTL;
    private String tlfTL;

    public Empresa(){}

    public Empresa(int codEmpresa, String cif, String nombre, String direccion, String cp, String localidad, String jornada, String modalidad, String mail, String dniRL, String nombreRL, String apRL, String dniTL, String nombreTL, String apTL, String tlfTL) {
        this.codEmpresa = codEmpresa;
        this.cif = cif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cp = cp;
        this.localidad = localidad;
        this.jornada = jornada;
        this.modalidad = modalidad;
        this.mail = mail;
        this.dniRL = dniRL;
        this.nombreRL = nombreRL;
        this.apRL = apRL;
        this.dniTL = dniTL;
        this.nombreTL = nombreTL;
        this.apTL = apTL;
        this.tlfTL = tlfTL;
    }

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDniRL() {
        return dniRL;
    }

    public void setDniRL(String dniRL) {
        this.dniRL = dniRL;
    }

    public String getNombreRL() {
        return nombreRL;
    }

    public void setNombreRL(String nombreRL) {
        this.nombreRL = nombreRL;
    }

    public String getApRL() {
        return apRL;
    }

    public void setApRL(String apRL) {
        this.apRL = apRL;
    }

    public String getDniTL() {
        return dniTL;
    }

    public void setDniTL(String dniTL) {
        this.dniTL = dniTL;
    }

    public String getNombreTL() {
        return nombreTL;
    }

    public void setNombreTL(String nombreTL) {
        this.nombreTL = nombreTL;
    }

    public String getApTL() {
        return apTL;
    }

    public void setApTL(String apTL) {
        this.apTL = apTL;
    }

    public String getTlfTL() {
        return tlfTL;
    }

    public void setTlfTL(String tlfTL) {
        this.tlfTL = tlfTL;
    }

    public TableModel getTablaProducto(ConexionBD cbd) {

        String[] columNames = {"CIF", "Nombre", "Direccion", "CP", "Localidad", "Jornada", "Modalidad", "Mail",};
        TableModel model = null;
        try {
            Statement s = cbd.conectar().createStatement();
            ResultSet rsAux = s.executeQuery("select count(*) from empresas");
            rsAux.next();
            int rows = rsAux.getInt(1); // Conseguir número de filas de la tabla Empresas

            ResultSet rs = s.executeQuery("select CIF, Nombre, Direccion, CP ,Localidad, Jornada, Modalidad, Mail, Cod_Empresas from empresas");
            Object[][] resultSet = new Object[rows][9];
            int row = 0;
            while (rs.next()) {
                for (int i = 0; i < 9; i++) {
                    resultSet[row][i] = rs.getObject(i + 1);
                }
                row++;
            }

            model = new AbstractTableModel() {
                public int getColumnCount() {
                    return columNames.length;
                }

                public int getRowCount() {
                    return resultSet.length;
                }

                public Object getValueAt(int row, int col) {
                    return resultSet[row][col];
                }

                public String getColumnName(int column) {
                    return columNames[column];
                }

                public Class getColumnClass(int col) {
                    return getValueAt(0, col).getClass();
                }

                public void setValueAt(Object aValue, int row, int column) {
                    resultSet[row][column] = aValue;
                }
            };


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return model;
    }
}