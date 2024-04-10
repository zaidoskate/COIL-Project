package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.domain.Collaboration;
import logic.domain.Professor;
import logic.interfaces.ProfessorBelongsToCollaborationManagerInterface;

public class ProfessorBelongsToCollaborationDAO implements ProfessorBelongsToCollaborationManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public ProfessorBelongsToCollaborationDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int addBelongs(ProfessorBelongsToCollaboration belongs) {
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
    public ArrayList<Collaboration> getColaborationsByProfessor(Professor professor) {
        String query = "SELECT * FROM Pertenece INNER JOIN Colaboracion ON "
                     + "Pertenece.Colaboracion_idColaboracion = Colaboracion.idColaboracion "
                     + "WHERE Pertenece.Professor_Usuario_idUsuario = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList<Collaboration> colaborationsResult = new ArrayList();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, professor.getIdUser());
            result = statement.executeQuery();
            Collaboration colaboration;
            while(result.next()) {
                colaboration = new Collaboration();
                colaboration.setArea(result.getString("area"));
                colaboration.setColaborationName(result.getString("nombreColaboracion"));
                colaboration.setEndDate(result.getString("fechaCierre"));
                colaboration.setIdColaboration(result.getInt("idColaboracion"));
                colaboration.setLanguage(result.getString("idioma"));
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
    public Professor getProfessorByIdCollaboration(String idCollaboration) {
        String query = "SELECT * FROM Profesor INNER JOIN Pertenece ON "
                     + "Pertenece.Profesor_idUsuario = Profesor.Usuario_idUsuario "
                     + "INNER JOIN Usuario ON Profesor.Usuario_idUsuario = Usuario.idUsuario "
                     + "WHERE Pertenece.Colaboracion_idColaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        Professor professor = new Professor();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, idCollaboration);
            result = statement.executeQuery();
            while(result.next()) {
                professor.setIdUser(result.getInt("idUsuario"));
                professor.setAcademicArea("areaAcademica");
                professor.setName("nombre");
                professor.setLastName("apellido");
                professor.setEmail("correo");
            }
        } catch(SQLException sqlException) {
            professor = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return professor;
    }
    
    @Override
    public Professor getMirrorProfessorByIdCollaboration(String idCollaboration) {
        String query = "SELECT * FROM Profesor INNER JOIN Pertenece ON "
                     + "Pertenece.Profesor_idUsuarioEspejo = Profesor.Usuario_idUsuario "
                     + "INNER JOIN Usuario ON Profesor.Usuario_idUsuario = Usuario.idUsuario "
                     + "WHERE Pertenece.Colaboracion_idColaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        Professor professor = new Professor();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, idCollaboration);
            result = statement.executeQuery();
            while(result.next()) {
                professor.setIdUser(result.getInt("idUsuario"));
                professor.setAcademicArea("areaAcademica");
                professor.setName("nombre");
                professor.setLastName("apellido");
                professor.setEmail("correo");
            }
        } catch(SQLException sqlException) {
            professor = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return professor;
    }
}
