package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.domain.User;
import logic.interfaces.ProfessorBelongsToCollaborationManagerInterface;
import logic.LogicException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Data Access Object (DAO) para gestionar las operaciones relacionadas con la pertenencia de profesores a colaboraciones en la base de datos.
 * Implementa la interfaz ProfessorBelongsToCollaborationManagerInterface.
 * 
 * @autor zaido
 */
public class ProfessorBelongsToCollaborationDAO implements ProfessorBelongsToCollaborationManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Inserta un profesor perteneciente a una colaboración.
     * 
     * @param professorBelongsToCollaboration una instancia de la clase ProfessorBelongsToCollaboration que contiene el ID de la colaboración, 
     *                                         el ID del profesor, el ID del profesor espejo y el estado de la colaboración.
     * @return un entero que indica el número de filas afectadas, si es 1 fue exitoso.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos o ocurre un error de SQL.
     */
    @Override
    public int addProfessorBelongsToCollaboration(ProfessorBelongsToCollaboration professorBelongsToCollaboration) throws LogicException {
        int result = 0;
        String query = "INSERT INTO profesorpertenececolaboracion VALUES (?, ?, ?, ?)";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, professorBelongsToCollaboration.getIdColaboration());
            statement.setInt(2, professorBelongsToCollaboration.getIdUser());
            statement.setInt(3, professorBelongsToCollaboration.getIdUserMirrorClass());
            statement.setString(4, professorBelongsToCollaboration.getColaborationStatus());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Elimina profesores que pertenecen a una colaboración.
     * 
     * @param idCollaboration el ID de la colaboración a modificar.
     * @return un entero que indica el número de filas afectadas, si es 1 fue exitoso.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos o ocurre un error de SQL.
     */
    @Override
    public int deleteProfessorBelongsToCollaborationByIdCollaboration(int idCollaboration) throws LogicException {
        int result = 0;
        String query = "DELETE FROM profesorpertenececolaboracion WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Obtiene la colaboración pendiente o iniciada de un profesor.
     * 
     * @param idUser el ID del usuario a consultar.
     * @return una instancia de la clase ProfessorBelongsToCollaboration con los IDs de los profesores, ID de la colaboración y el estado de la misma.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos o ocurre un error de SQL.
     */
    @Override
    public ProfessorBelongsToCollaboration getProfessorPendingCollaboration(int idUser) throws LogicException {
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = new ProfessorBelongsToCollaboration();
        String query = "SELECT * FROM profesorpertenececolaboracion WHERE profesor_idusuario = ? AND estadoColaboracion IN ('Pendiente', 'Iniciada')";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet idObtained = statement.executeQuery();
            if (idObtained.next()) {
                professorBelongsToCollaboration.setIdColaboration(idObtained.getInt("Colaboracion_idColaboracion"));
                professorBelongsToCollaboration.setIdUser(idObtained.getInt("Profesor_idUsuario"));
                professorBelongsToCollaboration.setIdUserMirrorClass(idObtained.getInt("Profesor_idUsuarioEspejo"));
                professorBelongsToCollaboration.setColaborationStatus(idObtained.getString("estadoColaboracion"));
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión intentelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return professorBelongsToCollaboration;
    }

    /**
     * Cambia el estado de una colaboración.
     * 
     * @param idCollaboration el ID de la colaboración a modificar.
     * @param status el nuevo estado a definir.
     * @return un entero que indica el número de filas afectadas, si es 1 fue exitoso.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos o ocurre un error de SQL.
     */
    @Override
    public int setStatusToCollaboration(int idCollaboration, String status) throws LogicException {
        int result = 0;
        String query = "UPDATE profesorPerteneceColaboracion SET estadoColaboracion = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setInt(2, idCollaboration);
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Obtiene todas las colaboraciones con estado "Espera".
     * 
     * @return un ArrayList de ProfessorBelongsToCollaboration con las colaboraciones en estado "Espera".
     * @throws LogicException cuando hay un problema con la conexión de la base de datos o ocurre un error de SQL.
     */
    @Override
    public ArrayList<ProfessorBelongsToCollaboration> getOnHoldCollaborations() throws LogicException {
        ArrayList<ProfessorBelongsToCollaboration> collaborations = new ArrayList<>();
        String query = "SELECT * FROM profesorpertenececolaboracion WHERE estadoColaboracion = 'Espera'";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ProfessorBelongsToCollaboration collaboration = new ProfessorBelongsToCollaboration();
                collaboration.setIdColaboration(result.getInt("Colaboracion_idColaboracion"));
                collaboration.setIdUser(result.getInt("Profesor_idUsuario"));
                collaboration.setIdUserMirrorClass(result.getInt("Profesor_idUsuarioEspejo"));
                collaboration.setColaborationStatus(result.getString("estadoColaboracion"));
                collaborations.add(collaboration);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión intentelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return collaborations;
    }

    /**
     * Obtiene las colaboraciones concluidas de un profesor.
     * 
     * @param idUser el ID del profesor.
     * @return un ArrayList de ProfessorBelongsToCollaboration con las colaboraciones concluidas del profesor.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos o ocurre un error de SQL.
     */
    @Override
    public ArrayList<ProfessorBelongsToCollaboration> getConcludedCollaborationsByIdUser(int idUser) throws LogicException {
        ArrayList<ProfessorBelongsToCollaboration> collaborations = new ArrayList<>();
        String query = "SELECT * FROM profesorpertenececolaboracion WHERE Profesor_idUsuario = ? AND estadoColaboracion = 'Concluida' OR Profesor_idUsuarioEspejo = ? AND estadoColaboracion = 'Concluida'";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            statement.setInt(2, idUser);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ProfessorBelongsToCollaboration collaboration = new ProfessorBelongsToCollaboration();
                collaboration.setIdColaboration(result.getInt("Colaboracion_idColaboracion"));
                collaboration.setIdUser(result.getInt("Profesor_idUsuario"));
                collaboration.setIdUserMirrorClass(result.getInt("Profesor_idUsuarioEspejo"));
                collaboration.setColaborationStatus(result.getString("estadoColaboracion"));
                collaborations.add(collaboration);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión intentelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return collaborations;
    }

    /**
     * Obtiene el correo del profesor por su ID de colaboración.
     * 
     * @param idCollaboration el ID de la colaboración a consultar.
     * @return el correo del profesor.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos o ocurre un error de SQL.
     */
    @Override
    public String getEmailProfessorByIdCollaboration(int idCollaboration) throws LogicException {
        String emailResult = null;
        String query = "select u.correo from usuario u inner join profesorpertenececolaboracion belongs on belongs.Profesor_idUsuario = u.idUsuario where Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                emailResult = result.getString("correo");
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión intentelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return emailResult;
    }

    /**
     * Obtiene el estado de una colaboración.
     * 
     * @param idCollaboration el ID de la colaboración.
     * @return el estado de la colaboración.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos o ocurre un error de SQL.
     */
    @Override
    public String getStatusByIdCollaboration(int idCollaboration) throws LogicException {
        String statusResult = null;
        String query = "select estadoColaboracion from profesorpertenececolaboracion WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                statusResult = result.getString("estadoColaboracion");
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión intentelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return statusResult;
    }

    /**
     * Obtiene la información de un profesor a partir de su colaboración vinculada en ProfessorBelongsToCollaboration.
     * 
     * @param idCollaboration el ID de la colaboración a consultar.
     * @return un ArrayList de User con los datos de los profesores.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos o ocurre un error de SQL.
     */
    @Override
    public ArrayList<User> getProfessorsDataByCollaboration(int idCollaboration) throws LogicException {
        ArrayList<User> professors = new ArrayList<>();
        String query = "(select u.idUsuario, u.nombre from profesorpertenececolaboracion p inner join usuario u on u.idUsuario = p.Profesor_idUsuario where p.Colaboracion_idColaboracion = ?)"
                + "UNION"
                + "(select u.idUsuario, u.nombre from profesorpertenececolaboracion p inner join usuario u on u.idUsuario = p.Profesor_idUsuarioEspejo where p.Colaboracion_idColaboracion = ?)";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            statement.setInt(2, idCollaboration);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User professor = new User();
                professor.setIdUser(result.getInt("idUsuario"));
                professor.setName(result.getString("nombre"));
                professors.add(professor);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión intentelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return professors;
    }
}
