import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.io.IOException;

/**
 * @author Francisco Rodríguez Espinosa
 */

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel( new FlatDarkLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }


        View view = new View();
        Controller controller = new Controller();

    }
}