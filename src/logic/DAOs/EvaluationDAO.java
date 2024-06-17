package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.Evaluation;
import logic.interfaces.EvaluationManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import logic.LogicException;

/**
 * Objeto de acceso a datos para manejar las operaciones relacionadas con las evaluaciones en la base de datos.
 * Implementa la interfaz EvaluationManagerInterface.
 * Proporciona métodos para insertar, eliminar y recuperar evaluaciones.
 * 
 * @author zaido
 */
public class EvaluationDAO implements EvaluationManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Convierte un objeto Date a una cadena con el formato 'yyyy-MM-dd'.
     * 
     * @param date la fecha a convertir.
     * @return la cadena de fecha formateada.
     */
    private String parseDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    /**
     * Inserta una nueva evaluación para una oferta aprobada.
     * 
     * @param evaluation el objeto Evaluation que contiene los datos de la evaluación, como id de la oferta y el coordinador que evaluó.
     * @return un entero que indica la cantidad de filas afectadas por la consulta, si es 0 falló, si es 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int insertEvaluationForApprovedOffer(Evaluation evaluation) throws LogicException {
        int result = 0;
        String query = "INSERT INTO Evaluacion (idOfertaColaboracion, idCoordinador, fechaEvaluacion) VALUES (?, ?, ?)";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, evaluation.getIdOfferCollaboration());
            statement.setInt(2, evaluation.getIdCoordinator());
            statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Inserta una evaluación para una oferta rechazada, incluyendo el motivo.
     * 
     * @param evaluation el objeto Evaluation que contiene los datos de la evaluación.
     * @return un entero que indica la cantidad de filas afectadas por la consulta, si es 0 falló, si es 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int insertEvaluationForDeclinedOffer(Evaluation evaluation) throws LogicException {
        int result = 0;
        String query = "INSERT INTO Evaluacion (idOfertaColaboracion, idCoordinador, fechaEvaluacion, motivo) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, evaluation.getIdOfferCollaboration());
            statement.setInt(2, evaluation.getIdCoordinator());
            statement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            statement.setString(4, evaluation.getReason());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Elimina una evaluación de la base de datos.
     * 
     * @param idCollaborationOffer el ID de la oferta registrada en la evaluación.
     * @return un entero que indica la cantidad de filas afectadas por la consulta, si es 0 falló, si es 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int deleteEvaluation(int idCollaborationOffer) throws LogicException {
        int result = 0;
        String query = "DELETE FROM Evaluacion WHERE idOfertaColaboracion = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaborationOffer);
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Obtiene la evaluación de una oferta específica.
     * 
     * @param idOfferCollaboration el ID de la oferta.
     * @return el objeto Evaluation que contiene los datos de la evaluación.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public Evaluation getEvaluationByIdOfferCollaboration(int idOfferCollaboration) throws LogicException {
        String query = "SELECT * FROM Evaluacion WHERE idOfertaColaboracion = ?";
        Evaluation evaluationResult = new Evaluation();
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idOfferCollaboration);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                evaluationResult.setIdOfferCollaboration(result.getInt("idOfertaColaboracion"));
                evaluationResult.setIdCoordinator(result.getInt("idCoordinador"));
                evaluationResult.setDate(result.getString("fechaEvaluacion"));
                evaluationResult.setReason(result.getString("motivo"));
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return evaluationResult;
    }

    /**
     * Obtiene las evaluaciones hechas por un coordinador específico.
     * 
     * @param idCoordinator el ID del coordinador.
     * @return una lista de objetos Evaluation que contiene las evaluaciones hechas por el coordinador.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public ArrayList<Evaluation> getEvaluationByIdCoordinator(int idCoordinator) throws LogicException {
        String query = "SELECT * FROM Evaluacion WHERE idCoordinador = ?";
        ArrayList<Evaluation> evaluationsFromCoordinator = new ArrayList<>();
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCoordinator);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Evaluation evaluationObtained = new Evaluation();
                evaluationObtained.setIdOfferCollaboration(result.getInt("idOfertaColaboracion"));
                evaluationObtained.setIdCoordinator(result.getInt("idCoordinador"));
                evaluationObtained.setDate(parseDateToString(result.getDate("fechaEvaluacion")));
                evaluationObtained.setReason(result.getString("motivo"));
                evaluationsFromCoordinator.add(evaluationObtained);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return evaluationsFromCoordinator;
    }
}
