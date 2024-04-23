package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import logic.LogicException;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.interfaces.ProfessorBelongsToCollaborationManagerInterface;

public class ProfessorBelongsToCollaborationDAO implements ProfessorBelongsToCollaborationManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public ProfessorBelongsToCollaborationDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int addProfessorBelongsToCollaboration(ProfessorBelongsToCollaboration professorBelongsToCollaboration) throws LogicException {
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
            sqlException.printStackTrace();
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    } 

    @Override
    public int deleteProfessorBelongsToCollaborationByIdCollaboration(int idCollaboration) throws LogicException {
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
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
}
