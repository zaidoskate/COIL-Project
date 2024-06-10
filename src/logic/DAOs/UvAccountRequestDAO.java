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
   
    /** Inserta una solicitud de cuenta de acceso UV en la base de datos
     *
     * @param uvAccountRequest una instancia de la clase UvAccountRequest que contiene todos los datos de la solicitud
     * @return un entero con el resultado de las filas agregadas en una tabla
     * @throws LogicException cuando no existe conexión con la base de datos
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

    /** Borra una solicitud de cuenta de acceso de la base de datos
     *
     * @param uvAccountRequest una instancia de la clase UvAccountRequest que contiene todos los datos de la solicitud
     * @return un entero que indica la cantidad de filas que se borraron de la base de datos
     * @throws LogicException cuando no existe conexión con la base de datos
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
    
    /** Obtiene todas las solicitudes de cuentas de acceso UV
     *
     * @return un ArrayList con todas las solicitudes de cuentas de acceso UV registradas en la base de datos
     * @throws LogicException cuando no hay conexión con la base de datos
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

    /** Verifica cuantas veces se ha registrado un correo en las solicitudes de cuentas de acceso
     *
     * @param email la dirección de correo que se desea buscar
     * @return un booleano que indica si existe algún correo con la misma dirección
     * @throws LogicException cuando no existe conexión con la base de  datos
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

    /** Verifica si se ha registrado un número de personal dado en las solicitudes de cuentas uv
     *
     * @param personalNumber un string con el número de personal que se desea buscar
     * @return un booleano que indica si existe o no un registro en las solicitudes de cuentas uv con el mismo número de personal
     * @throws LogicException cuando no existe conexión con la base de datos
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
