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

public class UniversityDAO implements UniversityManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public UniversityDAO(){
        this.databaseConnection = new DatabaseConnection();
    }

    @Override
    public int insertUniversity(University university) {
        int result = university.getUniversityId();
        String query = "INSERT INTO Universidad (idUniversidad, nombre, pais, nombreRepresentante, apellidoRepresentante, correoRepresentante, telefonoRepresentante) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, university.getUniversityId());
            statement.setString(2, university.getName());
            statement.setString(3, university.getCountry());
            statement.setString(4, university.getRepresentativeName());
            statement.setString(5, university.getRepresentativeLastName());
            statement.setString(6, university.getRepresentativeEmail());
            statement.setString(7, university.getRepresentativePhone());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

}
