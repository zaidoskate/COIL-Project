package logic.DAOs;

import logic.interfaces.CredentialManagerInterface;
import logic.domain.Credential;
import dataaccess.DatabaseConnection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import logic.LogicException;
import org.apache.log4j.Logger;

public class CredentialDAO implements CredentialManagerInterface {
    private final DatabaseConnection databaseConnection;
    private static final Logger log = Logger.getLogger(DatabaseConnection.class);
    
    public CredentialDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    @Override
    public int insertCredential(Credential credential)  throws LogicException {
        int result = 0;
        String query = "INSERT INTO Credencial VALUES (?, sha2( ?, 256) , ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, credential.getUser());
            statement.setString(2, credential.getPassword());
            statement.setInt(3, credential.getIdUser());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int getIdUserByCredential(Credential credential) throws LogicException {
        String query = "SELECT Usuario_idUsuario FROM Credencial WHERE usuario = ? and clave = sha2( ? ,256)";
        Connection connection;
        PreparedStatement statement;
        int idUser = -1;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, credential.getUser());
            statement.setString(2, credential.getPassword());
            ResultSet result;
            result = statement.executeQuery();
            while(result.next()) {
                idUser = result.getInt("Usuario_idUsuario");
            }
        } catch(SQLException sqlException) {
            log.error(sqlException);
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return idUser;
    }
    
    public int countCredentialsByUser(String user) throws LogicException {
        String query = "SELECT count(*) as count from credencial where usuario = ?";
        Connection connection;
        PreparedStatement statement;
        int count = 0;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, user);
            ResultSet result;
            result = statement.executeQuery();
            while(result.next()) {
                count = result.getInt("count");
            }
        } catch(SQLException sqlException) {
            log.error(sqlException);
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return count;
    }
    
}
