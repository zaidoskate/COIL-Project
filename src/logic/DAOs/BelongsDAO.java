package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.domain.Belongs;
import logic.domain.Colaboration;
import logic.domain.Professor;
import logic.interfaces.BelongsManagerInterface;

public class BelongsDAO implements BelongsManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public BelongsDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int insertBelongs(Belongs belongs) {
        int result = 0;
        String query = "INSERT INTO Pertenece VALUES (?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, belongs.getIdUser());
            statement.setInt(2, belongs.getIdColaboration());
            statement.setString(3, belongs.getColaborationStatus());
            result = statement.executeUpdate();
            
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public ArrayList<Colaboration> getColaborationsByProfessor(Professor professor) {
        String query = "SELECT * FROM Pertenece INNER JOIN Colaboracion ON "
                     + "Pertenece.Colaboracion_idColaboracion = Colaboracion.idColaboracion "
                     + "WHERE Pertenece.Professor_Usuario_idUsuario = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList<Colaboration> colaborationsResult = new ArrayList();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, professor.getIdUser());
            result = statement.executeQuery();
            Colaboration colaboration;
            while(result.next()) {
                colaboration = new Colaboration();
                colaboration.setArea(result.getString("area"));
                colaboration.setColaborationName(result.getString("nombreColaboracion"));
                colaboration.setEndDate(result.getString("fechaCierre"));
                colaboration.setIdColaboration(result.getInt("idColaboracion"));
                colaboration.setIdColaborationOffer(result.getInt("OfertaColaboracion_idOferta"));
                colaboration.setLanguage(result.getString("idioma"));
                colaboration.setNumStudents(result.getInt("numeroEstudiante"));
                colaboration.setStartDate(result.getString("fechaInicio"));
                colaborationsResult.add(colaboration);
            }
        } catch(SQLException sqlException) {
            colaborationsResult = new ArrayList();
        } finally {
            databaseConnection.closeConnection();
        }
        return colaborationsResult;
    }
    
    @Override
    public ArrayList<Professor> getProfessorsByColaboration(Colaboration colaboration) {
        
    }
}
