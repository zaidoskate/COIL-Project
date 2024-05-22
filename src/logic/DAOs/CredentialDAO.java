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
 *
 * @author chuch
 */
public class CredentialDAO implements CredentialManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     *
     * @param credential
     * @return
     * @throws LogicException
     */
    @Override
    public int insertCredential(Credential credential)  throws LogicException {
        int result = 0;
        String query = "INSERT INTO Credencial VALUES (?, sha2( ?, 256) , ?)";
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, credential.getUser());
            statement.setString(2, credential.getPassword());
            statement.setInt(3, credential.getIdUser());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     *
     * @param credential
     * @return
     * @throws LogicException
     */
    @Override
    public int getIdUserByCredential(Credential credential) throws LogicException {
        String query = "SELECT Usuario_idUsuario FROM Credencial WHERE usuario = ? and clave = sha2( ? ,256)";
        int idUser = -1;
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, credential.getUser());
            statement.setString(2, credential.getPassword());
            ResultSet result;
            result = statement.executeQuery();
            while(result.next()) {
                idUser = result.getInt("Usuario_idUsuario");
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return idUser;
    }
    
    /**
     *
     * @param user
     * @return
     * @throws LogicException
     */
    public int countCredentialsByUser(String user) throws LogicException {
        String query = "SELECT count(*) as count from credencial where usuario = ?";
        int count = 0;
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user);
            ResultSet result;
            result = statement.executeQuery();
            while(result.next()) {
                count = result.getInt("count");
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return count;
    }
    
}
