/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Evaluation;
import logic.interfaces.EvaluationManagerInterface;

/**
 *
 * @author zaido
 */
public class EvaluationDAO implements EvaluationManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public EvaluationDAO() {
        this.databaseConnection = new DatabaseConnection();
    }
    
    private Date parseStringToDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(date);
        Date sqlDate = new Date(parsedDate.getTime());
        return sqlDate;
    }
    
    private String parseDateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    @Override
    public int insertEvaluation(Evaluation evaluation) throws LogicException {
        int result = 0;
        String query = "INSERT INTO Evaluacion (idOfertaColaboracion, idCoordinador, fechaEvaluacion, motivo) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, evaluation.getIdOfferCollaboration());
            statement.setInt(2, evaluation.getIdCoordinator());
            statement.setDate(3, parseStringToDate(evaluation.getDate()));
            statement.setString(4, evaluation.getReason());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } catch (ParseException parseException) {
            throw new LogicException("Campos incorrectos", parseException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public Evaluation getEvaluationByIdOfferCollaboration(int idOfferCollaboration) throws LogicException {
        String query = "SELECT * FROM Evaluacion WHERE idOfertaColaboracion = ?";
        Evaluation evaluationResult = new Evaluation();
        try{
            Connection connection = this.databaseConnection.getConnection();
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
            databaseConnection.closeConnection();
        }
        return evaluationResult;
    }
    
    @Override
    public ArrayList<Evaluation> getEvaluationByIdCoordinator(int idCoordinator) throws LogicException{
        String query = "SELECT * FROM Evaluacion WHERE idCoordinador = ?";
        ArrayList<Evaluation> evaluationsFromCoordinator = new ArrayList();
        try {
            Connection connection = this.databaseConnection.getConnection();
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
        }
        return evaluationsFromCoordinator;
    }
}
