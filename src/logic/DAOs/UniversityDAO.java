/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.University;
import logic.interfaces.UniversityManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import logic.LogicException;

public class UniversityDAO implements UniversityManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public UniversityDAO(){
        this.databaseConnection = new DatabaseConnection();
    }

    @Override
    public int insertUniversity(University university) throws LogicException {
        int result = 0;
        String query = "INSERT INTO Universidad (idUniversidad, nombre, pais) VALUES (?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, university.getUniversityId());
            statement.setString(2, university.getName());
            statement.setString(3, university.getCountry());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde",sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

}
