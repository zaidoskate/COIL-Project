/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author chima
 */
public class ExternalProfessorDAO implements ExternalProfessorManagerInterface {
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    
    @Override
    public int insertExternalProfessor(ExternalProfessor externalProfessor) throws LogicException{
        int result = 0;
        String query = "INSERT INTO profesorexterno (profesor_usuario_idusuario, universidad_iduniversidad) VALUES (?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, externalProfessor.getIdUser());
            statement.setInt(2, externalProfessor.getIdUniversity());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("Error al crear la cuenta", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public ExternalProfessor getExternalProfessorByIdUniversity(int idUniversity) {
        String query = "SELECT * FROM profesorexterno WHERE Universidad_universidad = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ExternalProfessor externalProfessorResult = new ExternalProfessor();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idUniversity);
            result = statement.executeQuery();
            while(result.next()) {
                externalProfessorResult.setIdUniversity(result.getInt("Universidad_idUniversidad"));
            }
        } catch(SQLException sqlException) {
            externalProfessorResult = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return externalProfessorResult;
    }
}
