import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    private ArrayList<Cuenta> cuentas = new ArrayList<>();

    public Controller() {

        // Variables para la creación de nuevas cuentas
        Integer numCuenta;
        String titular;
        String nacionalidad;
        LocalDate fechaApertura;
        Double saldo;

        try {
            Connection con = ConexionBD.conectar();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM cuenta");
            while (rs.next()) {
                numCuenta = Integer.valueOf(rs.getString(1));
                titular = rs.getString(2);
                nacionalidad = rs.getString(3);
                fechaApertura = LocalDate.parse(rs.getString(4));
                saldo = Double.valueOf(rs.getString(5));
                cuentas.add(new Cuenta(numCuenta, titular, nacionalidad, fechaApertura, saldo));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer numCuentas() {
        return cuentas.size();
    }

    public Integer numCuentas50k() {
        Integer res = 0;
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getSaldo() >= 50000) {
                res++;
            }
        }
        return res;
    }


}
