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
 * @author chuch
 */
public class ExternalAccountRequestDAO implements ExternalAccountRequestManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     *
     * @param externalAccountRequest
     * @return
     * @throws LogicException
     */
    @Override
    public int insertExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException {
        int result = 0;
        String query = "INSERT INTO SolicitudCuentaExterno (nombre, apellido, correo, idUniversidad) VALUES (?, ?, ?, ?)";
        try {
            Connection connection =  DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, externalAccountRequest.getName());
            statement.setString(2, externalAccountRequest.getLastName());
            statement.setString(3, externalAccountRequest.getEmail());
            statement.setInt(4, externalAccountRequest.getIdUniversity());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     *
     * @param externalAccountRequest
     * @return
     * @throws LogicException
     */
    @Override
    public int deleteExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException {
        int result = 0;
        String query = "DELETE FROM SolicitudCuentaExterno WHERE idSolicitud = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, externalAccountRequest.getIdRequest());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
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
    public ArrayList<ExternalAccountRequestData> getExternalAccountRequestsData() throws LogicException  {
        String query = "SELECT solicitudcuentaexterno.nombre AS nombre, solicitudcuentaexterno.idSolicitud "
                + "as id, solicitudcuentaexterno.apellido AS apellido, solicitudcuentaexterno.correo as correo, "
                + "universidad.nombre as universidad from solicitudcuentaexterno INNER JOIN Universidad "
                + "on solicitudcuentaexterno.idUniversidad = universidad.idUniversidad";
        ArrayList<ExternalAccountRequestData> externalAccountRequestsData = new ArrayList<>();
        try{
            Connection connection;
            connection = this.DATABASE_CONNECTION.getConnection();
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
            DATABASE_CONNECTION.closeConnection();
        }
        return externalAccountRequestsData;
    }

    /**
     *
     * @param idExternalAccountRequest
     * @return
     * @throws LogicException
     */
    @Override
    public ExternalAccountRequest getExternalAccountRequestById(int idExternalAccountRequest) throws LogicException{
        String query = "SELECT * from solicitudcuentaexterno WHERE idSolicitud = ?";
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        try{
            Connection connection;
            connection = this.DATABASE_CONNECTION.getConnection();
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
            DATABASE_CONNECTION.closeConnection();
        }
        return externalAccountRequest;
    }
    
    /**
     *
     * @param email
     * @return
     * @throws LogicException
     */
    @Override
    public boolean checkEmailRegistered(String email) throws LogicException {
        String query = "SELECT COUNT(*) as cuentas FROM solicitudcuentaexterno WHERE correo = ?";
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
}
