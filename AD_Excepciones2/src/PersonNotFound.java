/**
 * Excepción personalizada para la comprobación de las personas
 *
 * @author Francisco Rodríguez Espinosa
 */

public class PersonNotFound extends Exception{

    public PersonNotFound(String errorMessage) {
        super(errorMessage);
    }
}
