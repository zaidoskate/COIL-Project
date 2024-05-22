package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.ExternalProfessor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import logic.interfaces.ExternalProfessorManagerInterface;
import java.sql.ResultSet;
import logic.LogicException;

/**
 *
 * @author zaido
 */
public class ExternalProfessorDAO implements ExternalProfessorManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     *
     * @param externalProfessor
     * @return
     * @throws LogicException
     */
    @Override
    public int insertExternalProfessor(ExternalProfessor externalProfessor) throws LogicException{
        int result = 0;
        String query = "INSERT INTO profesorexterno (profesor_usuario_idusuario, universidad_iduniversidad) VALUES (?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.DATABASE_CONNECTION.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, externalProfessor.getIdUser());
            statement.setInt(2, externalProfessor.getIdUniversity());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("Error al crear la cuenta", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     *
     * @param idUniversity
     * @return
     * @throws LogicException
     */
    @Override
    public ExternalProfessor getExternalProfessorByIdUniversity(int idUniversity) throws LogicException {
        String query = "SELECT * FROM profesorexterno WHERE Universidad_universidad = ?";
        ExternalProfessor externalProfessorResult = new ExternalProfessor();
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUniversity);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                externalProfessorResult.setIdUniversity(result.getInt("Universidad_idUniversidad"));
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return externalProfessorResult;
    }
}
