import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller = new Controller();

    @org.junit.jupiter.api.Test
    @DisplayName("Comprobar el número de cuentas")
    void numCuentas() {
        assertEquals(9, controller.numCuentas());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Comprobar los saldos mayores a 50.000€")
    void numCuentas50k() {
        assertEquals(3, controller.numCuentas50k());
    }
}