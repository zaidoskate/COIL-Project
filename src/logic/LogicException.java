package logic;

import java.sql.SQLException;

/**
 *
 * @author zaido
 */
public class LogicException extends Exception {

    public LogicException(String message, Throwable cause) {
        super(buildMessage(message, cause), cause);
    }

    private static String buildMessage(String message, Throwable cause) {
        if (cause instanceof SQLException) {
            SQLException sqlException = (SQLException) cause;
            switch (sqlException.getErrorCode()) {
                case 1045:
                    return message = "Error de autenticación: usuario o contraseña de la base de datos incorrectos ";
                case 1049:
                    return message = "Error de conexión: base de datos desconocida";
                default:
            }
        }
        return message;
    }
}
