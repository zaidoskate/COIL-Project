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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.LogicException;

/**
 *
 * @author zaido
 */
public class UvAccountRequestDAO implements UvAccountRequestManagerInterface{
   private static final DatabaseConnection databaseConnection = new DatabaseConnection();
   
    @Override
    public int insertUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException{
        String query = "INSERT INTO SolicitudCuentaUv (idSolicitud, nombre, apellido, correo, numeropersonal, idFacultad) VALUES (?, ?, ?, ?, ?, ?)";
        int result=0;
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, uvAccountRequest.getIdRequest());
            statement.setString(2, uvAccountRequest.getName());
            statement.setString(3, uvAccountRequest.getLastName());
            statement.setString(4, uvAccountRequest.getEmail());
            statement.setString(5, uvAccountRequest.getPersonalNumber());
            statement.setString(6, uvAccountRequest.getIdDepartment());
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
    
    @Override
    public ArrayList<UvAccountRequest> getUvAccountRequests() throws LogicException {
        String query = "SELECT * FROM solicitudcuentauv";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList<UvAccountRequest> uvAccountRequestsResult = new ArrayList();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            while(result.next()) {
                UvAccountRequest uvAccountRequest = new UvAccountRequest();
                uvAccountRequest.setIdRequest(result.getInt("idSolicitud"));
                uvAccountRequest.setName(result.getString("nombre"));
                uvAccountRequest.setLastName(result.getString("apellido"));
                uvAccountRequest.setEmail(result.getString("correo"));
                uvAccountRequest.setIdDepartment(result.getString("idFacultad"));
                uvAccountRequest.setPersonalNumber(result.getString("numeropersonal"));
                uvAccountRequestsResult.add(uvAccountRequest);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return uvAccountRequestsResult;
    }
}
