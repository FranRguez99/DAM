import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Controller controller = new Controller();

    @org.junit.jupiter.api.Test
    void numCuentas() {
        assertEquals(9, controller.numCuentas());
    }

    @org.junit.jupiter.api.Test
    void numCuentas50k() {
        assertEquals(3, controller.numCuentas50k());
    }
}