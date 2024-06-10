package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Evaluation;
import logic.interfaces.EvaluationManagerInterface;

/**
 *
 * @author zaido
 */
public class EvaluationDAO implements EvaluationManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    private String parseDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        return dateString;
    }
    
    /**
     * Insertar una nueva evaluación para oferta aprobada
     * @param evaluation datos de la evaluación como id de la oferta, coordinador que evaluó.
     * @return  un entero que indica la cantidad de rows de la base de datos modificada, si es 0 falló, si es 1 funcionó.
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
     * Insertar una evaluación para una oferta rechazada (incluye motivo).
     * @param evaluation información de la evaluación a insertar.
     * @return un entero que indica la cantidad de rows de la base de datos modificada, si es 0 falló, si es 1 funcionó.
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
     * Eliminar una evaluación.
     * @param idCollaborationOffer id de la oferta registrada en la evaluación.
     * @return un entero que indica la cantidad de rows de la base de datos modificada, si es 0 falló, si es 1 funcionó.
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
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Obtener la evaluación de una oferta.
     * @param idOfferCollaboration id de la oferta.
     * @return Evaluación hecha a la oferta.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public Evaluation getEvaluationByIdOfferCollaboration(int idOfferCollaboration) throws LogicException {
        String query = "SELECT * FROM Evaluacion WHERE idOfertaColaboracion = ?";
        Evaluation evaluationResult = new Evaluation();
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idOfferCollaboration);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
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
     * Obtener las evaluaciones hechas por un coordinador.
     * @param idCoordinator id del coordinador a consultar.
     * @return ArrayList de evaluaciones hechas por el coordinador.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public ArrayList<Evaluation> getEvaluationByIdCoordinator(int idCoordinator) throws LogicException{
        String query = "SELECT * FROM Evaluacion WHERE idCoordinador = ?";
        ArrayList<Evaluation> evaluationsFromCoordinator = new ArrayList();
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCoordinator);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
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
