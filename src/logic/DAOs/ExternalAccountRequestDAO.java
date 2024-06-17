package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.ExternalAccountRequest;
import logic.domain.ExternalAccountRequestData;
import logic.interfaces.ExternalAccountRequestManagerInterface;
import logic.LogicException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Objeto de acceso a datos para manejar las operaciones relacionadas con las solicitudes de cuentas externas en la base de datos.
 * Implementa la interfaz ExternalAccountRequestManagerInterface.
 * Proporciona métodos para insertar, eliminar y recuperar solicitudes de cuentas externas.
 * 
 * @autor chuch
 */
public class ExternalAccountRequestDAO implements ExternalAccountRequestManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Inserta una nueva solicitud de cuenta externa en la base de datos.
     * 
     * @param externalAccountRequest el objeto ExternalAccountRequest que contiene los detalles de la solicitud de cuenta externa a insertar.
     * @return el número de filas afectadas por la consulta.
     * @throws LogicException si hay un problema de conexión a la base de datos.
     */
    @Override
    public int insertExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException {
        int result = 0;
        String query = "INSERT INTO SolicitudCuentaExterno (nombre, apellido, correo, idUniversidad) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, externalAccountRequest.getName());
            statement.setString(2, externalAccountRequest.getLastName());
            statement.setString(3, externalAccountRequest.getEmail());
            statement.setInt(4, externalAccountRequest.getIdUniversity());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Elimina una solicitud de cuenta externa de la base de datos.
     * 
     * @param externalAccountRequest el objeto ExternalAccountRequest que contiene los detalles de la solicitud de cuenta externa a eliminar.
     * @return el número de filas afectadas por la consulta.
     * @throws LogicException si hay un problema de conexión a la base de datos.
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
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Obtiene todas las solicitudes de cuentas externas de la base de datos.
     * 
     * @return una lista de objetos ExternalAccountRequestData que contiene los detalles de las solicitudes de cuentas externas.
     * @throws LogicException si hay un problema de conexión a la base de datos.
     */
    @Override
    public ArrayList<ExternalAccountRequestData> getExternalAccountRequestsData() throws LogicException {
        String query = "SELECT solicitudcuentaexterno.nombre AS nombre, solicitudcuentaexterno.idSolicitud "
                + "as id, solicitudcuentaexterno.apellido AS apellido, solicitudcuentaexterno.correo as correo, "
                + "universidad.nombre as universidad from solicitudcuentaexterno INNER JOIN Universidad "
                + "on solicitudcuentaexterno.idUniversidad = universidad.idUniversidad";
        ArrayList<ExternalAccountRequestData> externalAccountRequestsData = new ArrayList<>();
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ExternalAccountRequestData externalRequestData = new ExternalAccountRequestData();
                externalRequestData.setIdRequest(result.getInt("id"));
                externalRequestData.setName(result.getString("nombre"));
                externalRequestData.setLastName(result.getString("apellido"));
                externalRequestData.setEmail(result.getString("correo"));
                externalRequestData.setUniversity(result.getString("universidad"));
                
                externalAccountRequestsData.add(externalRequestData);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return externalAccountRequestsData;
    }

    /**
     * Obtiene una solicitud de cuenta externa por su ID.
     * 
     * @param idExternalAccountRequest el ID de la solicitud de cuenta externa.
     * @return un objeto ExternalAccountRequest que contiene los detalles de la solicitud de cuenta externa.
     * @throws LogicException si hay un problema de conexión a la base de datos.
     */
    @Override
    public ExternalAccountRequest getExternalAccountRequestById(int idExternalAccountRequest) throws LogicException {
        String query = "SELECT * from solicitudcuentaexterno WHERE idSolicitud = ?";
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idExternalAccountRequest);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                externalAccountRequest.setIdRequest(result.getInt("idSolicitud"));
                externalAccountRequest.setName(result.getString("nombre"));
                externalAccountRequest.setLastName(result.getString("apellido"));
                externalAccountRequest.setEmail(result.getString("correo"));
                externalAccountRequest.setIdUniversity(result.getInt("idUniversidad"));
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return externalAccountRequest;
    }

    /**
     * Verifica si una dirección de correo ya está registrada en las solicitudes de cuentas externas.
     * 
     * @param email la dirección de correo a verificar.
     * @return true si la dirección de correo ya está registrada, false en caso contrario.
     * @throws LogicException si hay un problema de conexión a la base de datos.
     */
    @Override
    public boolean checkEmailRegistered(String email) throws LogicException {
        String query = "SELECT COUNT(*) as cuentas FROM solicitudcuentaexterno WHERE correo = ?";
        boolean emailExists = false;
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet accounts = statement.executeQuery();
            if (accounts.next() && accounts.getInt("cuentas") >= 1) {
                emailExists = true;
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión a la base de datos, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return emailExists;
    }
}
