package logic.DAOs;

import logic.interfaces.CredentialManagerInterface;
import logic.domain.Credential;
import dataaccess.DatabaseConnection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class CredentialDAO implements CredentialManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public CredentialDAO(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }
    @Override
    public int insertCredential(Credential credential) {
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
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int getIdUserByCredential(Credential credential) {
        String query = "SELECT Usuario_idUsuario FROM Credencial WHERE usuario = ? and clave = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        int idResult = -1;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, credential.getUser());
            statement.setString(2, credential.getPassword());
            result = statement.executeQuery();
            while(result.next()) {
                idResult = result.getInt("Usuario_idUsuario");
            }
        } catch(SQLException sqlException) {
            idResult = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return idResult;
    }
}
