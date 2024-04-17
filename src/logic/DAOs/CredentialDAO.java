package logic.DAOs;

import logic.interfaces.CredentialManagerInterface;
import logic.domain.Credential;
import dataaccess.DatabaseConnection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import logic.LogicException;

public class CredentialDAO implements CredentialManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public CredentialDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    @Override
    public int insertCredential(Credential credential)  throws LogicException {
        int result = 0;
        String query = "INSERT INTO Credencial VALUES (?, ?, ?)";
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
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public Credential getCredentialByUser(String user)  throws LogicException {
        String query = "SELECT * FROM Credencial WHERE usuario = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        Credential credential = new Credential();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, credential.getUser());
            result = statement.executeQuery();
            while(result.next()) {
                credential.setIdUser(result.getInt("Usuario_idUsuario"));
                credential.setUser(user);
                credential.setPassword(result.getString("clave"));
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return credential;
    }
}
