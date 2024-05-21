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

public class CollaborationDAO implements CollaborationManagerInterface {
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    
    @Override
    public int addColaboration(Collaboration colaboration) throws LogicException{
        int result = 0;
        String query = "INSERT INTO Colaboracion(nombrecolaboracion, idioma, temainteres) VALUES (?, ?, ?)";
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, colaboration.getColaborationName());
            statement.setString(2, colaboration.getLanguage());
            statement.setString(3, colaboration.getInterestTopic());
            statement.executeUpdate();
            ResultSet idCollaborationInserted = statement.getGeneratedKeys();
            if(idCollaborationInserted.next()) {
                result = idCollaborationInserted.getInt(1);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int startCollaboration(int idCollaboration) throws LogicException {
        int result = 0;
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String query = "UPDATE Colaboracion SET fechaInicio = ? WHERE idColaboracion = ?";
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, currentDate);
            statement.setInt(2, idCollaboration);
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int concludeCollaboration(int idCollaboration) throws LogicException {
        int result = 0;
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String query = "UPDATE Colaboracion SET fechaCierre = ? WHERE idColaboracion = ?";
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, currentDate);
            statement.setInt(2, idCollaboration);
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
        Collaboration colaboration = new Collaboration();
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
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
        ArrayList <Collaboration> collaborationsResult = new ArrayList();
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
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
        ArrayList <Collaboration> collaborationsResult = new ArrayList();
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
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
    public int updateEndDateByIdCollaboration(int idCollaboration) throws LogicException {
        int result = 0;
        String query = "UPDATE Colaboracion SET fechaCierre = ? WHERE idColaboracion = ?";
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            statement.setInt(2, idCollaboration);
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public ArrayList<Collaboration> getProfessorConcludedCollaborations(ArrayList<ProfessorBelongsToCollaboration> professorCollaborations) throws LogicException {
        ArrayList<Collaboration> collaborations = new ArrayList<>();
        String query = "SELECT * FROM Colaboracion WHERE idColaboracion = ?";
        try {
            Connection connection = this.databaseConnection.getConnection();
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
            databaseConnection.closeConnection();
        }
        return collaborations;
    }
}
