import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConexionBDTest {

    @Test
    @DisplayName("Test de conexión de la base de datos")
    void conectar() throws SQLException {
        Connection result = ConexionBD.conectar();
        assertNotNull(result);
    }
}