/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.UvAccountRequest;
import logic.interfaces.UvAccountRequestManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import logic.LogicException;

/**
 *
 * @author zaido
 */
public class UvAccountRequestDAO implements UvAccountRequestManagerInterface{
   private final DatabaseConnection databaseConnection;
   
   public UvAccountRequestDAO() {
       this.databaseConnection = new DatabaseConnection();
   }

    @Override
    public int insertUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException{
        int result = 0;
        String query = "INSERT INTO SolicitudCuentaUv (nombre, apellido, correo, numeropersonal, idFacultad) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uvAccountRequest.getName());
            statement.setString(2, uvAccountRequest.getLastName());
            statement.setString(3, uvAccountRequest.getEmail());
            statement.setInt(4, uvAccountRequest.getPersonalNumber());
            statement.setString(5, uvAccountRequest.getIdDepartment());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("Error al crear la cuenta", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int deleteUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException{
        int result = 0;
        String query = "DELETE FROM SolicitudCuentaUv WHERE idSolicitud = ?";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, uvAccountRequest.getIdRequest());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("Error al eliminar la cuenta de profesor UV", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
}
