package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.domain.Collaboration;
import logic.interfaces.ColaborationManagerInterface;

public class CollaborationDAO implements ColaborationManagerInterface {
    private final DatabaseConnection databaseConnection;
    public CollaborationDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int addColaboration(Collaboration colaboration) {
        int result = 0;
        String query = "INSERT INTO Colaboracion VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, colaboration.getIdColaboration());
            statement.setString(2, colaboration.getArea());
            statement.setString(3, colaboration.getColaborationName());
            statement.setString(4, colaboration.getEndDate());
            statement.setString(5, colaboration.getStartDate());
            statement.setString(6, colaboration.getLanguage());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public Collaboration getColaborationById(int id) {
        String query = "SELECT * FROM Colaboracion WHERE idColaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        Collaboration colaboration = new Collaboration();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while(result.next()) {
                colaboration.setArea(result.getString("area"));
                colaboration.setColaborationName(result.getString("nombreColaboracion"));
                colaboration.setEndDate(result.getString("fechaCierre"));
                colaboration.setIdColaboration(result.getInt("idColaboracion"));
                colaboration.setLanguage(result.getString("idioma"));
                colaboration.setStartDate(result.getString("fechaInicio"));
            }
        } catch(SQLException sqlException) {
            colaboration = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return colaboration;
    }
    
    @Override
    public ArrayList<Collaboration> getColaborationsByArea(String area) {
        String query = "SELECT * FROM Colaboracion WHERE idColaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList <Collaboration> collaborationsResult = new ArrayList();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, area);
            result = statement.executeQuery();
            while(result.next()) {
                Collaboration colaboration = new Collaboration();
                colaboration.setArea(result.getString("area"));
                colaboration.setColaborationName(result.getString("nombreColaboracion"));
                colaboration.setEndDate(result.getString("fechaCierre"));
                colaboration.setIdColaboration(result.getInt("idColaboracion"));
                colaboration.setLanguage(result.getString("idioma"));
                colaboration.setStartDate(result.getString("fechaInicio"));
                collaborationsResult.add(colaboration);
            }
        } catch(SQLException sqlException) {
            collaborationsResult = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return collaborationsResult;
    }
}
