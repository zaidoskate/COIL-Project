package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.domain.Administrator;
import logic.interfaces.AdministratorManagerInterface;

public class AdministratorDAO implements AdministratorManagerInterface{
    private final DatabaseConnection databaseConnection;
    
    public AdministratorDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int insertAdministrator(Administrator administrator) {
        int result = 0;
        String query = "INSERT INTO Administrador VALUES (?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, administrator.getIdAdministrator());
            statement.setInt(2, administrator.getIdUser());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public Administrator getAdministratorByIdUser(int id) {
        String query = "SELECT * FROM Administrador INNER JOIN "
                     + "Usuario ON Administrador.Usuario_idUsuario="
                     + "Usuario.idUsuario WHERE idUsuario = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        Administrator administratorResult = new Administrator();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while(result.next()) {
                administratorResult.setIdUser(result.getInt("idUsuario"));
                administratorResult.setName(result.getString("nombre"));
                administratorResult.setLastName(result.getString("apellidoPaterno"));
                administratorResult.setSurname(result.getString("apellidoMaterno"));
                administratorResult.setLanguage(result.getString("idioma"));
                administratorResult.setEmail(result.getString("correo"));
                administratorResult.setIdAdministrator(result.getInt("idAdministrador"));
            }
        } catch(SQLException sqlException) {
            administratorResult = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return administratorResult;
    }
    
    @Override
    public Administrator getAdministratorByIdAdministrator(int id) {
        String query = "SELECT * FROM Administrador INNER JOIN "
                     + "Usuario ON Administrador.Usuario_idUsuario="
                     + "Usuario.idUsuario WHERE idAdministrador = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        Administrator administratorResult = new Administrator();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while(result.next()) {
                administratorResult.setIdUser(result.getInt("idUsuario"));
                administratorResult.setName(result.getString("nombre"));
                administratorResult.setLastName(result.getString("apellidoPaterno"));
                administratorResult.setSurname(result.getString("apellidoMaterno"));
                administratorResult.setLanguage(result.getString("idioma"));
                administratorResult.setEmail(result.getString("correo"));
                administratorResult.setIdAdministrator(result.getInt("idAdministrador"));
            }
        } catch(SQLException sqlException) {
            administratorResult = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return administratorResult;
    }
}
