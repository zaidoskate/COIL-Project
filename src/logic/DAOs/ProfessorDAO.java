/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.Professor;
import logic.interfaces.ProfessorManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author zaido
 */
public class ProfessorDAO implements ProfessorManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public ProfessorDAO() {
        this.databaseConnection =  new DatabaseConnection();
    }
    
    @Override
    public int insertProfessor(Professor professor){
        int result = 0;
        String query = "INSERT INTO Profesor VALUES (?)";
        try{
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, professor.getIdUser());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
}
