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
    public int addProfessorBelongsToCollaboration(ProfessorBelongsToCollaboration professorBelongsToCollaboration) {
        int result = 0;
        String query = "INSERT INTO profesorpertenececolaboracion VALUES (?, ?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, professorBelongsToCollaboration.getIdColaboration());
            statement.setInt(2, professorBelongsToCollaboration.getIdUser());
            statement.setInt(3, professorBelongsToCollaboration.getIdUserMirrorClass());
            statement.setString(4, professorBelongsToCollaboration.getColaborationStatus());
            result = statement.executeUpdate();
            
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public ArrayList<Collaboration> getCollaborationsByStatus(String statusCollaboration) {
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
    public Professor getMirrorProfessorByIdCollaboration(int idCollaboration) {
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

    @Override
    public int deleteProfessorBelongsToCollaborationByIdCollaboration(int idCollaboration) {
        int result = 0;
        String query = "DELETE FROM profesorpertenececolaboracion WHERE Colaboracion_idColaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            result = statement.executeUpdate();
            
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
}
