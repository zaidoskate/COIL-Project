package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.UvProfessor;
import logic.interfaces.UvProfessorManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import logic.LogicException;

/**
 *
 * @author zaido
 */
public class UvProfessorDAO implements UvProfessorManagerInterface{
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     *
     * @param uvProfessor
     * @return
     * @throws LogicException
     */
    @Override
    public int insertUvProfessor(UvProfessor uvProfessor) throws LogicException{
        int result = 0;
        String query = "INSERT INTO Profesoruv (numeroPersonal, Profesor_Usuario_idUsuario, idFacultad) VALUES (?, ?, ?)";
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uvProfessor.getPersonalNumber());
            statement.setInt(2, uvProfessor.getIdUser());
            statement.setString(3, uvProfessor.getIdDepartment());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("Error al insertar el profesor UV", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    @Override
    public UvProfessor getUvProfessorByIdUser(int idUser) throws LogicException{
        String query = "SELECT * FROM ProfesorUv WHERE Profesor_Usuario_idUsuario = ?";
        UvProfessor uvProfessorResult = new UvProfessor();
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                uvProfessorResult.setPersonalNumber(result.getString("numeroPersonal"));
                uvProfessorResult.setIdUser(result.getInt("Profesor_Usuario_idUsuario"));
                uvProfessorResult.setIdDepartment(result.getString("idFacultad"));
            }
        } catch (SQLException sqlException) {
            throw new LogicException("Error al obtener el profesor: ", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return uvProfessorResult;
    }
    
    /**
     *
     * @param personalNumber
     * @return
     * @throws LogicException
     */
    @Override
    public int countUvProfessorByPersonalNumber(String personalNumber) throws LogicException {
        String query = "SELECT count(*) as count from ProfesorUv WHERE numeroPersonal = ?";
        int count = 0;
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, personalNumber);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException sqlException) {
            throw new LogicException("Error al obtener el profesor: ", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return count;
    }
    
    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    @Override
    public String getDepartmentNameBelonging(int idUser) throws LogicException {
        String departmentName = null;
        String query = "SELECT f.nombre FROM profesoruv pu JOIN facultad f ON pu.idFacultad = f.idFacultad WHERE pu.Profesor_Usuario_idUsuario = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                departmentName = result.getString("nombre");
            }
        } catch (SQLException sqlException) {
            throw new LogicException("Error al obtener el nombre del departamento: ", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return departmentName; 
    }

    /**
     *
     * @param region
     * @return
     * @throws LogicException
     */
    @Override
    public int getCollaborationCountByProfessorRegion(String region) throws LogicException {
        String query = "SELECT COUNT(DISTINCT pu.Profesor_Usuario_idUsuario) AS count "
                + "FROM profesorUv pu JOIN facultad f ON pu.idFacultad = f.idFacultad "
                + "JOIN profesorPerteneceColaboracion pc ON "
                + "pu.Profesor_Usuario_idUsuario = pc.Profesor_idUsuario "
                + "OR pu.Profesor_Usuario_idUsuario = pc.Profesor_idUsuarioEspejo "
                + "WHERE f.region = ?";
        int collaborationCount = 0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, region);
            ResultSet countResult = statement.executeQuery();
            if(countResult.next()) {
                collaborationCount = countResult.getInt("count");
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return collaborationCount;
    }

    /**
     *
     * @param idAcademicArea
     * @return
     * @throws LogicException
     */
    @Override
    public int getCollaborationCountByProfessorAcademicArea(int idAcademicArea) throws LogicException {
        String query = "SELECT COUNT(DISTINCT pu.Profesor_Usuario_idUsuario) AS count " +
                   "FROM profesorUv pu " +
                   "JOIN facultad f ON pu.idFacultad = f.idFacultad " +
                   "JOIN profesorPerteneceColaboracion pc ON pu.Profesor_Usuario_idUsuario = pc.Profesor_idUsuario " +
                   "OR pu.Profesor_Usuario_idUsuario = pc.Profesor_idUsuarioEspejo " +
                   "WHERE f.idAreaAcademica = ?";
        int count = 0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idAcademicArea);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return count;
    }
}
