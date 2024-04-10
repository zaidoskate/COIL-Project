package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.domain.AccountRequest;
import logic.interfaces.AccountRequestManagerInterface;

public class AccountRequestDAO implements AccountRequestManagerInterface{
    private final DatabaseConnection databaseConnection;
    
    public AccountRequestDAO(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }
    @Override
    public int addAccountRequest(AccountRequest accountRequest) {
        int result = 0;
        String query = "INSERT INTO numerosolicitudcuenta(areaAcademica, nombre, apellido, correo) VALUES (?, ?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, accountRequest.getAcademicArea());
            statement.setString(2, accountRequest.getName());
            statement.setString(3, accountRequest.getLastname());
            statement.setString(4, accountRequest.getEmail());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int deleteAccountRequestById(int id) {
        int result = 0;
        String query = "DELETE FROM numerosolicitudcuenta WHERE idSolicitudCuenta = ?";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public AccountRequest getAccountRequestById(int id) {
        String query = "SELECT * FROM numerosolicitudcuenta WHERE idSolicitudCuenta = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        AccountRequest accountRequest = null;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while(result.next()) {
                accountRequest.setIdAccountRequest(result.getInt("idSolicitudCuenta"));
                accountRequest.setName(result.getString("nombre"));
                accountRequest.setLastname(result.getString("apellido"));
                accountRequest.setAcademicArea(result.getString("areaAcademica"));
                accountRequest.setEmail(result.getString("correo"));
            }
        } catch(SQLException sqlException) {
            accountRequest = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return accountRequest;
    }
}
