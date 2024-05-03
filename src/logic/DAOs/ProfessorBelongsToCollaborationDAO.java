package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
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

    @Override
    public ProfessorBelongsToCollaboration getProfessorPendingCollaboration(int idUser) throws LogicException {
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = new ProfessorBelongsToCollaboration();
        String query = "SELECT * FROM profesorpertenececolaboracion WHERE profesor_idusuario = ? AND estadoColaboracion IN ('Pendiente', 'Iniciada')";
        try {
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet idObtained = statement.executeQuery();
            if(idObtained.next()) {
                professorBelongsToCollaboration.setIdColaboration(idObtained.getInt("Colaboracion_idColaboracion"));
                professorBelongsToCollaboration.setIdUser(idObtained.getInt("Profesor_idUsuario"));
                professorBelongsToCollaboration.setIdUserMirrorClass(idObtained.getInt("Profesor_idUsuarioEspejo"));
                professorBelongsToCollaboration.setColaborationStatus(idObtained.getString("estadoColaboracion"));
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión intentelo de nuevo más tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return professorBelongsToCollaboration;
    }

    @Override
    public int setStartedStatusToCollaboration(int idCollaboration) throws LogicException {
        int result = 0;
        String query = "UPDATE profesorPerteneceColaboracion SET estadoColaboracion = 'Iniciada' WHERE Colaboracion_idColaboracion = ?";
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
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
