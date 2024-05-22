package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.Coordinator;
import logic.interfaces.CoordinatorManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.LogicException;

/**
 *
 * @author chuch
 */
public class CoordinatorDAO implements CoordinatorManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     *
     * @param coordinator
     * @return
     * @throws LogicException
     */
    @Override
    public int insertCoordinator(Coordinator coordinator) throws LogicException {
        int result = 0;
        String query = "INSERT INTO coordinador (usuario_idusuario) VALUES (?)";
        try{
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, coordinator.getIdUser());
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
     * @param idUser
     * @return
     * @throws LogicException
     */
    @Override
    public int getIdCoordinatorByIdUser(int idUser) throws LogicException {
        int idObtained = 0;
        String query = "SELECT idCoordinador FROM coordinador WHERE usuario_idUsuario = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idObtained = resultSet.getInt("idcoordinador");
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return idObtained;
    }
    
}
