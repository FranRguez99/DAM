import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class Asmtv2Test {

    @Test
    void openConnection() throws Exception {
        System.out.println("openConnection");
        Connection result = Asmtv2.openConnection();
        assertNotNull(result);
    }
}