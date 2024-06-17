package logic.DAOs;

import logic.interfaces.CredentialManagerInterface;
import logic.domain.Credential;
import dataaccess.DatabaseConnection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import logic.LogicException;

/**
 * Objeto de acceso a datos para manejar las operaciones relacionadas con las credenciales en la base de datos.
 * Implementa la interfaz CredentialManagerInterface.
 * Proporciona métodos para insertar credenciales, obtener el ID de usuario por credencial y contar credenciales por usuario.
 * 
 * @author chuch
 */
public class CredentialDAO implements CredentialManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Insertar una nueva credencial de ingreso al sistema.
     * 
     * @param credential el objeto Credential que incluye el username y el password.
     * @return un entero que indica la cantidad de filas de la base de datos modificadas, si es 0 falló, si es 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int insertCredential(Credential credential) throws LogicException {
        int result = 0;
        String query = "INSERT INTO Credencial VALUES (?, sha2( ?, 256) , ?)";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, credential.getUser());
            statement.setString(2, credential.getPassword());
            statement.setInt(3, credential.getIdUser());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Obtener el id del usuario por sus credenciales de ingreso.
     * 
     * @param credential el objeto Credential que incluye el username y el password.
     * @return un entero que indica el id de usuario de la credencial, si es -1 falló, si es mayor o igual a 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int getIdUserByCredential(Credential credential) throws LogicException {
        String query = "SELECT Usuario_idUsuario FROM Credencial WHERE usuario = ? and clave = sha2( ? ,256)";
        int idUser = -1;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, credential.getUser());
            statement.setString(2, credential.getPassword());
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                idUser = result.getInt("Usuario_idUsuario");
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return idUser;
    }

    /**
     * Verificar el número de credenciales con un username.
     * 
     * @param user el username a consultar.
     * @return un entero que indica la cantidad de credenciales con ese username.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int countCredentialsByUser(String user) throws LogicException {
        String query = "SELECT count(*) as count FROM credencial WHERE usuario = ?";
        int count = 0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return count;
    }
}
