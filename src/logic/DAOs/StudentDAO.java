/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.Student;
import logic.interfaces.StudentManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class StudentDAO implements StudentManagerInterface{
    private final DatabaseConnection databaseConnection;
    
    public StudentDAO() {
        this.databaseConnection = new DatabaseConnection();
    }

    @Override
    public int insertStudent(Student student) {
        int result = 0;
        String query = "INSERT INTO Estudiante VALUES (?, ?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, student.getRegistrationNumber());
            statement.setString(2, student.getRegion());
            statement.setInt(3, student.getIdUser());
            statement.setInt(4, student.getIdUniversity());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return result;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public Student getStudentByIdUser(int id) {
        String query = "SELECT * FROM Estudiante WHERE Usuario_idUsuario = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        Student studentResult = new Student();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();
            while(result.next()) {
                studentResult.setRegistrationNumber(result.getString("matricula"));
                studentResult.setRegion(result.getString("region"));
                studentResult.setIdUser(result.getInt("Usuario_idUsuario"));
                studentResult.setIdUniversity(result.getInt("Universidad_idUniversidad"));
            }
        } catch (SQLException sqlException) {
            studentResult = null;
        } finally {
            databaseConnection.closeConnection();
        }
        return studentResult;
    }
}
