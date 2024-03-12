/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.util.ArrayList;
import logic.domain.Professor;
import logic.interfaces.ProfessorManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author zaido
 */
public class ProfessorDAO implements ProfessorManagerInterface{
    private final DatabaseConnection databaseConnection;
    
    public ProfessorDAO() {
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int insertProfessor(Professor professor){
        int result = 0;
        String query = "INSERT INTO Profesor VALUES (?, ?)";
        try{
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, professor.getAcademicArea());
            statement.setInt(2, professor.getIdUser());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            return result;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public ArrayList<Professor> getProfessorByAcademicArea(String academicArea) {
        ArrayList<Professor> professors = new ArrayList<>();
        String query = "SELECT p.areaAcademica, u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.idioma, u.correo " +
                       "FROM coilProject.Profesor p " +
                       "INNER JOIN coilProject.Usuario u ON p.Usuario_idUsuario = u.idUsuario " +
                       "WHERE p.areaAcademica = ?";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, academicArea);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Professor professor = new Professor();
                professor.setAcademicArea(resultSet.getString("areaAcademica"));
                professor.setIdUser(resultSet.getInt("idUsuario"));
                professor.setName(resultSet.getString("nombre"));
                professor.setLastName(resultSet.getString("apellidoPaterno"));
                professor.setSurname(resultSet.getString("apellidoMaterno"));
                professor.setLanguage(resultSet.getString("idioma"));
                professor.setEmail(resultSet.getString("correo"));
                professors.add(professor);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            return null;
        }
        return professors;
    }
}
