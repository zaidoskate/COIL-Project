/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.ExternalAccountRequest;
import logic.interfaces.ExternalAccountRequestManagerInterface;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import logic.LogicException;
/**
 *
 * @author zaido
 */
public class ExternalAccountRequestDAO implements ExternalAccountRequestManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public ExternalAccountRequestDAO() {
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int insertExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException {
        int result = 0;
        String query = "INSERT INTO SolicitudCuentaExterno (nombre, apellido, correo, idUniversidad) VALUES (?, ?, ?, ?)";
        try {
            Connection connection =  databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, externalAccountRequest.getName());
            statement.setString(2, externalAccountRequest.getLastName());
            statement.setString(3, externalAccountRequest.getEmail());
            statement.setInt(4, externalAccountRequest.getIdUniversity());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int deleteExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException {
        int result = 0;
        String query = "DELETE FROM SolicitudCuentaExterno WHERE idSolicitud = ?";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, externalAccountRequest.getIdRequest());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    
}
