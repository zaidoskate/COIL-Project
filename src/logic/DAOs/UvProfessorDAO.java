/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.UvProfessor;
import logic.interfaces.UvProfessorManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UvProfessorDAO implements UvProfessorManagerInterface{
    private final DatabaseConnection databaseConnection;
    
    public UvProfessorDAO() {
        this.databaseConnection = new DatabaseConnection();
    }

    @Override
    public int insertUvProfessor(UvProfessor uvProfessor) {
        int result = 0;
        String query = "INSERT INTO Profesoruv (numeroPersonal, region, Profesor_Usuario_idUsuario) VALUES (?, ?)";
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uvProfessor.getPersonalNumber());
            statement.setInt(2, uvProfessor.getIdUser());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public UvProfessor getUvProfessorByIdUser(int id) {
        String query = "SELECT * FROM ProfesorUv WHERE Profesor_Usuario_idUsuario = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        UvProfessor uvProfessorResult = new UvProfessor();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while(result.next()) {
                uvProfessorResult.setPersonalNumber(result.getString("numeroPersonal"));
                uvProfessorResult.setIdUser(result.getInt("Profesor_Usuario_idUsuario"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            uvProfessorResult = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return uvProfessorResult;
    }
}
