package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Collaboration;
import logic.interfaces.ColaborationManagerInterface;

public class CollaborationDAO implements ColaborationManagerInterface {
    private final DatabaseConnection databaseConnection;
    public CollaborationDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int addColaboration(Collaboration colaboration) throws LogicException{
        int result = 0;
        String query = "INSERT INTO Colaboracion VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, colaboration.getIdColaboration());
            statement.setString(2, colaboration.getColaborationName());
            statement.setString(3, colaboration.getEndDate());
            statement.setString(4, colaboration.getStartDate());
            statement.setString(5, colaboration.getLanguage());
            statement.setString(6, colaboration.getInterestTopic());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public Collaboration getColaborationById(int id) throws LogicException{
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
                colaboration.setInterestTopic(result.getString("temaInteres"));
                colaboration.setColaborationName(result.getString("nombreColaboracion"));
                colaboration.setEndDate(result.getString("fechaCierre"));
                colaboration.setIdColaboration(result.getInt("idColaboracion"));
                colaboration.setLanguage(result.getString("idioma"));
                colaboration.setStartDate(result.getString("fechaInicio"));
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return colaboration;
    }
    
    @Override
    public ArrayList<Collaboration> getAllCollaborations() throws LogicException{
        String query = "SELECT * FROM Colaboracion";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList <Collaboration> collaborationsResult = new ArrayList();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            while(result.next()) {
                Collaboration colaboration = new Collaboration();
                colaboration.setInterestTopic(result.getString("temaInteres"));
                colaboration.setColaborationName(result.getString("nombreColaboracion"));
                colaboration.setEndDate(result.getString("fechaCierre"));
                colaboration.setIdColaboration(result.getInt("idColaboracion"));
                colaboration.setLanguage(result.getString("idioma"));
                colaboration.setStartDate(result.getString("fechaInicio"));
                collaborationsResult.add(colaboration);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return collaborationsResult;
    }
    
    @Override
    public ArrayList<Collaboration> getActiveCollaborations() throws LogicException{
        String query = "SELECT * FROM Colaboracion WHERE  fechaCierre = Null";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList <Collaboration> collaborationsResult = new ArrayList();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            while(result.next()) {
                Collaboration colaboration = new Collaboration();
                colaboration.setInterestTopic(result.getString("temaInteres"));
                colaboration.setColaborationName(result.getString("nombreColaboracion"));
                colaboration.setEndDate(result.getString("fechaCierre"));
                colaboration.setIdColaboration(result.getInt("idColaboracion"));
                colaboration.setLanguage(result.getString("idioma"));
                colaboration.setStartDate(result.getString("fechaInicio"));
                collaborationsResult.add(colaboration);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return collaborationsResult;
    }
    
    @Override
    public int updateEndDateByIdCollaboration(int idCollaboration, String date) throws LogicException{
        int result = 0;
        String query = "UPDATE Colaboracion SET fechaCierre = ? WHERE idColaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, date);
            statement.setInt(2, idCollaboration);
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
}
