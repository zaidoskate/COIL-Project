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

/**
 *
 * @author chima
 */
public class ExternalProfessorDAO implements ExternalProfessorManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public ExternalProfessorDAO() {
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int insertExternalProfessor(ExternalProfessor externalProfessor) {
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
            sqlException.printStackTrace();
            return result;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
}
