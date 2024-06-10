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
 * @author chuch
 */
public class UvAccountRequestDAO implements UvAccountRequestManagerInterface{
   private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
   
    /**
     *
     * @param uvAccountRequest
     * @return
     * @throws LogicException
     */
    @Override
    public int insertUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException{
        String query = "INSERT INTO SolicitudCuentaUv (idSolicitud, nombre, apellido, correo, numeropersonal, idFacultad) VALUES (?, ?, ?, ?, ?, ?)";
        int result=0;
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
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
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     *
     * @param uvAccountRequest
     * @return
     * @throws LogicException
     */
    @Override
    public int deleteUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException{
        int result = 0;
        String query = "DELETE FROM SolicitudCuentaUv WHERE idSolicitud = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, uvAccountRequest.getIdRequest());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("Error al eliminar la cuenta de profesor UV", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     *
     * @return
     * @throws LogicException
     */
    @Override
    public ArrayList<UvAccountRequest> getUvAccountRequests() throws LogicException {
        String query = "SELECT * FROM solicitudcuentauv";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList<UvAccountRequest> uvAccountRequestsResult = new ArrayList();
        try{
            connection = this.DATABASE_CONNECTION.getConnection();
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
            DATABASE_CONNECTION.closeConnection();
        }
        return uvAccountRequestsResult;
    }

    /**
     *
     * @param email
     * @return
     * @throws LogicException
     */
    @Override
    public boolean checkEmailRegistered(String email) throws LogicException{
        String query = "SELECT COUNT(*) as cuentas FROM solicitudcuentauv WHERE correo = ?";
        boolean emailExists = false;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet accounts = statement.executeQuery();
            if(accounts.next()) {
                if(accounts.getInt("cuentas") >= 1) {
                    emailExists = true;
                }
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión a la base de datos, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return emailExists;
    }

    /**
     *
     * @param personalNumber
     * @return
     * @throws logic.LogicException
     */
    
    @Override
    public boolean checkPersonalNumberRegistered(String personalNumber) throws LogicException {
        String query = "SELECT COUNT(*) as numeros FROM solicitudcuentauv WHERE numeropersonal = ?";
        boolean emailExists = false;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, personalNumber);
            ResultSet accounts = statement.executeQuery();
            if(accounts.next()) {
                if(accounts.getInt("numeros") >= 1) {
                    emailExists = true;
                }
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión a la base de datos, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return emailExists;
    }
    
}
