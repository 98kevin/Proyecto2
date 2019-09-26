package exceptions;

/**
 * Excepcion para manejar los errores de creacion de perfil
 * @author kevin
 *
 */
public class ErrorCreacionUsuario extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 2515440521866137566L;

    public ErrorCreacionUsuario(String msg) {
        super(msg);
    }
    
}