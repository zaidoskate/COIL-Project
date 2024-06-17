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
 * Objeto de acceso a datos para manejar las operaciones relacionadas con los coordinadores en la base de datos.
 * Implementa la interfaz CoordinatorManagerInterface.
 * Proporciona métodos para insertar coordinadores y obtener el ID de coordinador por ID de usuario.
 * 
 * @autor chuch
 */
public class CoordinatorDAO implements CoordinatorManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Insertar un nuevo coordinador en la base de datos.
     * 
     * @param coordinator el objeto Coordinator que incluye el ID de usuario.
     * @return un entero que indica la cantidad de filas de la base de datos modificadas, si es 0 falló, si es 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int insertCoordinator(Coordinator coordinator) throws LogicException {
        int result = 0;
        String query = "INSERT INTO coordinador (usuario_idusuario) VALUES (?)";
        try {
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
     * Obtener el ID de coordinador a partir del ID de usuario.
     * 
     * @param idUser el ID de usuario a consultar.
     * @return un entero que indica el ID del coordinador obtenido, si es 0 falló, si es mayor o igual a 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
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
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return idObtained;
    }
}
