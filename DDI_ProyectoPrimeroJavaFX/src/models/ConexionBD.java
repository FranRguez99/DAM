/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {

    Connection con;
    String db;
    String url;
    String user;
    String pass;

    public Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public ConexionBD() {
        url = "jdbc:mysql://localhost:3307/bdgestionfct?useUnicode=true&amp;characterEncoding=ISO8859-1" ;
        user = "root";
        pass= "root";
        System.out.println("conectado");
    }

    public void desconectar() throws SQLException{
        con.close();
    }
}