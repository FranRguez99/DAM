/**
 * Excepción personalizada para la comprobación de los DNI
 *
 * @author Francisco Rodríguez Espinosa
 */

public class DNIException extends Exception{
    public DNIException(String errorMessage) {
        super(errorMessage);
    }
}
