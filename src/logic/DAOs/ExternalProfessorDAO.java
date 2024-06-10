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
 * Objeto de acceso a datos para manejar las operaciones relacionadas con los profesores externos en la base de datos.
 * Implementa la interfaz ExternalProfessorManagerInterface.
 * Proporciona métodos para insertar profesores externos.
 * 
 * @autor zaido
 */
public class ExternalProfessorDAO implements ExternalProfessorManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     * Inserta un nuevo profesor externo en la base de datos.
     * 
     * @param externalProfessor el objeto ExternalProfessor que contiene los detalles del profesor externo a insertar
     * @return el número de filas afectadas por la consulta
     * @throws LogicException si hay un problema de conexión a la base de datos o un error al crear la cuenta
     */
    @Override
    public int insertExternalProfessor(ExternalProfessor externalProfessor) throws LogicException {
        int result = 0;
        String query = "INSERT INTO profesorexterno (profesor_usuario_idusuario, universidad_iduniversidad) VALUES (?, ?)";
        Connection connection;
        PreparedStatement statement;
        try {
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
}
