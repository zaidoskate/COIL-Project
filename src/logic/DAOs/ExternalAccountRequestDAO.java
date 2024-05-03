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
import java.sql.ResultSet;
import java.util.ArrayList;
import logic.LogicException;
import logic.domain.ExternalAccountRequestData;
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

    public ArrayList<ExternalAccountRequestData> getExternalAccountRequestsData() throws LogicException  {
        String query = "SELECT solicitudcuentaexterno.nombre AS nombre, solicitudcuentaexterno.idSolicitud "
                + "as id, solicitudcuentaexterno.apellido AS apellido, solicitudcuentaexterno.correo as correo, "
                + "universidad.nombre as universidad from solicitudcuentaexterno INNER JOIN Universidad "
                + "on solicitudcuentaexterno.idUniversidad = universidad.idUniversidad";
        ArrayList<ExternalAccountRequestData> externalAccountRequestsData = new ArrayList<>();
        try{
            Connection connection;
            connection = this.databaseConnection.getConnection();
            PreparedStatement statement;
            statement = connection.prepareStatement(query);
            ResultSet result;
            result = statement.executeQuery();
            while(result.next()) {
                ExternalAccountRequestData externalRequestData = new ExternalAccountRequestData();
                externalRequestData.setIdRequest(result.getInt("id"));
                externalRequestData.setName(result.getString("nombre"));
                externalRequestData.setLastName(result.getString("apellido"));
                externalRequestData.setEmail(result.getString("correo"));
                externalRequestData.setUniversity(result.getString("universidad"));
                
                externalAccountRequestsData.add(externalRequestData);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return externalAccountRequestsData;
    }
    
    public ExternalAccountRequest getExternalAccountRequestById(int idExternalAccountRequest) throws LogicException{
        String query = "SELECT * from solicitudcuentaexterno WHERE idSolicitud = ?";
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        try{
            Connection connection;
            connection = this.databaseConnection.getConnection();
            PreparedStatement statement;
            statement = connection.prepareStatement(query);
            statement.setInt(1, idExternalAccountRequest);
            ResultSet result;
            result = statement.executeQuery();
            while(result.next()) {
                externalAccountRequest.setIdRequest(result.getInt("idSolicitud"));
                externalAccountRequest.setName(result.getString("nombre"));
                externalAccountRequest.setLastName(result.getString("apellido"));
                externalAccountRequest.setEmail(result.getString("correo"));
                externalAccountRequest.setIdUniversity(result.getInt("idUniversidad"));
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return externalAccountRequest;
    }
}
