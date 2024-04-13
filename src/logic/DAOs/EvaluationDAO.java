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
    
    private Date parseDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(date);
        Date sqlDate = new Date(parsedDate.getTime());
        return sqlDate;
    }

    @Override
    public int insertEvaluation(Evaluation evaluation) {
        int result = 0;
        String query = "INSERT INTO Evaluacion (idOfertaColaboracion, idCoordinador, fechaEvaluacion, motivo) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, evaluation.getIdOfferCollaboration());
            statement.setInt(2, evaluation.getIdCoordinator());
            statement.setDate(3, parseDate(evaluation.getDate()));
            statement.setString(4, evaluation.getReason());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            result = -1;
        } catch (ParseException parseException) {
            result = -2;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public Evaluation getEvaluationByIdOfferCollaboration(int idOfferCollaboration) {
        String query = "SELECT * FROM Evaluacion WHERE idOfertaColaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        Evaluation evaluationResult = new Evaluation();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idOfferCollaboration);
            result = statement.executeQuery();
            while(result.next()) {
                evaluationResult.setIdOfferCollaboration(result.getInt("idOfertaColaboracion"));
                evaluationResult.setIdCoordinator(result.getInt("idCoordinador"));
                evaluationResult.setDate(result.getString("fechaEvaluacion"));
                evaluationResult.setReason(result.getString("motivo"));
            }
        } catch (SQLException sqlException) {
            evaluationResult = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return evaluationResult;
    }
    
}
