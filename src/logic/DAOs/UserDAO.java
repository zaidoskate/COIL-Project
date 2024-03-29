package logic.DAOs;

import logic.interfaces.UserManagerInterface;
import logic.domain.User;
import dataaccess.DatabaseConnection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class UserDAO implements UserManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public UserDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int InsertUser(User user) {
        int result = user.getIdUser();
        String query = "INSERT INTO Usuario VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, user.getIdUser());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getLanguage());
            statement.setString(5, user.getEmail());
            statement.executeUpdate();
            
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public User getUserById(int id) {
        String query = "SELECT * FROM Usuario WHERE Usuario_idUsuario = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        User userResult = new User();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while(result.next()) {
                userResult.setIdUser(result.getInt("idUsuario"));
                userResult.setName(result.getString("nombre"));
                userResult.setLastName(result.getString("apellido"));
                userResult.setLanguage(result.getString("idioma"));
                userResult.setEmail(result.getString("correo"));
            }
        } catch(SQLException sqlException) {
            userResult = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return userResult;
    }
    
    @Override
    public User getUserByName(String name) {
        String query = "SELECT * FROM Usuario WHERE nombre = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        User userResult = new User();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            result = statement.executeQuery();
            while(result.next()) {
                userResult.setIdUser(result.getInt("idUsuario"));
                userResult.setName(result.getString("nombre"));
                userResult.setLastName(result.getString("apellido"));
                userResult.setLanguage(result.getString("idioma"));
                userResult.setEmail(result.getString("correo"));
            }
        } catch(SQLException sqlException) {
            userResult = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return userResult;
    }
}
