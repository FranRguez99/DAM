import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Asmtv2 {

    // jdbc:sqlite:gradesDatabase
    public static Connection conn = null;
    public static String driver = "org.sqlite.JDBC";

    public static Connection openConnection() throws  Exception{
        try{
            // Declaramos el driver
            Driver d = (Driver) Class.forName(driver).newInstance();
            conn = DriverManager.getConnection("jdbc:sqlite:gradesDatabase");
            if (conn != null){
                System.out.println("Conexión con la base de datos establecida");
            }
        } catch (Exception e){
            System.out.println("Conexión con la base de datos fallida");
            e.printStackTrace();
        }
        return conn;
    }


}
