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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    @Override
    public ArrayList<University> getUniversities() throws LogicException {
        String query = "SELECT * FROM Universidad";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList <University> universitiesResult = new ArrayList();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            while(result.next()) {
                University university = new University();
                university.setCountry(result.getString("pais"));
                university.setUniversityId(result.getInt("idUniversidad"));
                university.setName(result.getString("nombre"));
                universitiesResult.add(university);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return universitiesResult;
    }

}
