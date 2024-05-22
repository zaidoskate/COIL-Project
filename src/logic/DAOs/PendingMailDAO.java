package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.LogicException;
import logic.domain.PendingMail;
import logic.interfaces.PendingMailManagerInterface;

/**
 *
 * @author chuch
 */
public class PendingMailDAO implements PendingMailManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     *
     * @param pendingMail
     * @return
     * @throws LogicException
     */
    @Override
    public int insertPendingMail(PendingMail pendingMail) throws LogicException {
        int result = 0;
        String query = "INSERT INTO CorreoPendiente(Usuario_idUsuario,contenido,destino,asunto) VALUES (?, ?, ?, ?)";
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pendingMail.getIdUser());
            statement.setString(2, pendingMail.getContent());
            statement.setString(3, pendingMail.getDestinationEmail());
            statement.setString(4, pendingMail.getSubject());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     *
     * @param pendingMail
     * @return
     * @throws LogicException
     */
    @Override
    public int deletePendingMail(PendingMail pendingMail) throws LogicException {
        int result = 0;
        String query = "DELETE FROM CorreoPendiente WHERE idCorreo = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pendingMail.getIdEmail());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion con la base de datos", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    @Override
    public ArrayList<PendingMail> getPendingMailsByIdUser(int idUser) throws LogicException {
        String query = "SELECT * FROM correoPendiente WHERE Usuario_idUsuario = ?";
        ArrayList <PendingMail> pendingMailsResult = new ArrayList();
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                PendingMail pendingMail = new PendingMail();
                pendingMail.setIdEmail(result.getInt("idCorreo"));
                pendingMail.setIdUser(result.getInt("Usuario_idUsuario"));
                pendingMail.setContent(result.getString("contenido"));
                pendingMail.setDestinationEmail(result.getString("destino"));
                pendingMail.setSubject(result.getString("asunto"));
                pendingMailsResult.add(pendingMail);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return pendingMailsResult;
    }
    
    
}
