package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Collaboration;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.interfaces.CollaborationManagerInterface;

/**
 * Objeto de acceso a datos para manejar las operaciones relacionadas con las colaboraciones en la base de datos.
 * Implementa la interfaz CollaborationManagerInterface.
 * Proporciona métodos para insertar, modificar y recuperar colaboraciones.
 * 
 * @autor zaido
 */
public class CollaborationDAO implements CollaborationManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     * Inserta una nueva colaboración en la base de datos.
     * 
     * @param colaboration una Colaboración de la que se extraen el nombre, idioma y tema de interés.
     * @return Un entero que indica la cantidad de filas de la base de datos modificadas. Si es 0, falló; si es 1, funcionó.
     * @throws LogicException Cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int addColaboration(Collaboration colaboration) throws LogicException {
        int result = 0;
        String query = "INSERT INTO Colaboracion(nombrecolaboracion, idioma, temainteres) VALUES (?, ?, ?)";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, colaboration.getColaborationName());
            statement.setString(2, colaboration.getLanguage());
            statement.setString(3, colaboration.getInterestTopic());
            statement.executeUpdate();
            ResultSet idCollaborationInserted = statement.getGeneratedKeys();
            if (idCollaborationInserted.next()) {
                result = idCollaborationInserted.getInt(1);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     * Cambia la fecha de inicio de la colaboración en la base de datos.
     * 
     * @param idCollaboration ID de la colaboración a cambiar.
     * @return Un entero que indica el número de filas de la base de datos modificadas.
     * @throws LogicException Cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public int startCollaboration(int idCollaboration) throws LogicException {
        int result = 0;
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String query = "UPDATE Colaboracion SET fechaInicio = ? WHERE idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, currentDate);
            statement.setInt(2, idCollaboration);
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     * Modifica la fecha de cierre de la colaboración.
     * 
     * @param idCollaboration ID de la colaboración a modificar en la base de datos.
     * @return Un entero que indica el número de filas de la base de datos modificadas. 1 si funcionó correctamente.
     * @throws LogicException Cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public int concludeCollaboration(int idCollaboration) throws LogicException {
        int result = 0;
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String query = "UPDATE Colaboracion SET fechaCierre = ? WHERE idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, currentDate);
            statement.setInt(2, idCollaboration);
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     * Obtener la información completa de una colaboración por su ID.
     * 
     * @param idCollaboration ID de la colaboración a obtener.
     * @return Un objeto Colaboración que contiene toda la información de la colaboración obtenida.
     * @throws LogicException Cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public Collaboration getColaborationById(int idCollaboration) throws LogicException {
        String query = "SELECT * FROM Colaboracion WHERE idColaboracion = ?";
        Collaboration colaboration = new Collaboration();
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                colaboration.setInterestTopic(result.getString("temaInteres"));
                colaboration.setColaborationName(result.getString("nombreColaboracion"));
                colaboration.setEndDate(result.getString("fechaCierre"));
                colaboration.setIdColaboration(result.getInt("idColaboracion"));
                colaboration.setLanguage(result.getString("idioma"));
                colaboration.setStartDate(result.getString("fechaInicio"));
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return colaboration;
    }
    
    /**
     * Obtener todas las colaboraciones.
     * 
     * @return ArrayList de colaboraciones obtenidas.
     * @throws LogicException Cuando hay un problema con la conexión a la base de datos.
     */
    @Override
    public ArrayList<Collaboration> getAllCollaborations() throws LogicException {
        String query = "SELECT * FROM Colaboracion";
        ArrayList<Collaboration> collaborationsResult = new ArrayList<>();
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Collaboration colaboration = new Collaboration();
                colaboration.setInterestTopic(result.getString("temaInteres"));
                colaboration.setColaborationName(result.getString("nombreColaboracion"));
                colaboration.setEndDate(result.getString("fechaCierre"));
                colaboration.setIdColaboration(result.getInt("idColaboracion"));
                colaboration.setLanguage(result.getString("idioma"));
                colaboration.setStartDate(result.getString("fechaInicio"));
                collaborationsResult.add(colaboration);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return collaborationsResult;
    }
    
    /**
     * Obtener las colaboraciones que no han concluido.
     * 
     * @return ArrayList de colaboraciones activas obtenidas.
     * @throws LogicException Cuando hay un problema con la conexión a la base de datos.
     */
    @Override
    public ArrayList<Collaboration> getActiveCollaborations() throws LogicException {
        String query = "SELECT * FROM Colaboracion WHERE fechaCierre IS NULL";
        ArrayList<Collaboration> collaborationsResult = new ArrayList<>();
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Collaboration colaboration = new Collaboration();
                colaboration.setInterestTopic(result.getString("temaInteres"));
                colaboration.setColaborationName(result.getString("nombreColaboracion"));
                colaboration.setEndDate(result.getString("fechaCierre"));
                colaboration.setIdColaboration(result.getInt("idColaboracion"));
                colaboration.setLanguage(result.getString("idioma"));
                colaboration.setStartDate(result.getString("fechaInicio"));
                collaborationsResult.add(colaboration);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return collaborationsResult;
    }
    
    /**
     * Concluir la colaboración modificando la fecha de cierre.
     * 
     * @param idCollaboration ID de la colaboración a modificar.
     * @return Un entero que indica el número de filas de la base de datos modificadas. 1 si funcionó correctamente.
     * @throws LogicException Cuando hay un problema con la conexión a la base de datos.
     */
    @Override
    public int updateEndDateByIdCollaboration(int idCollaboration) throws LogicException {
        int result = 0;
        String query = "UPDATE Colaboracion SET fechaCierre = ? WHERE idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            statement.setInt(2, idCollaboration);
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     * Obtener las colaboraciones concluidas de un profesor.
     * 
     * @param professorCollaborations Array de ProfessorBelongsToCollaboration que contiene los IDs de colaboraciones concluidas.
     * @return ArrayList de colaboraciones concluidas obtenidas.
     * @throws LogicException Cuando hay un problema con la conexión a la base de datos.
     */
    @Override
    public ArrayList<Collaboration> getProfessorConcludedCollaborations(ArrayList<ProfessorBelongsToCollaboration> professorCollaborations) throws LogicException {
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT * FROM Colaboracion WHERE idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            for (ProfessorBelongsToCollaboration professorCollaboration : professorCollaborations) {
                statement.setInt(1, professorCollaboration.getIdColaboration());
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    Collaboration colaboration = new Collaboration();
                    colaboration.setInterestTopic(result.getString("temaInteres"));
                    colaboration.setColaborationName(result.getString("nombreColaboracion"));
                    colaboration.setEndDate(result.getString("fechaCierre"));
                    colaboration.setIdColaboration(result.getInt("idColaboracion"));
                    colaboration.setLanguage(result.getString("idioma"));
                    colaboration.setStartDate(result.getString("fechaInicio"));
                    collaborations.add(colaboration);
                }
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return collaborations;
    }
}
