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
import logic.LogicException;

public class UvProfessorDAO implements UvProfessorManagerInterface{
    private final DatabaseConnection databaseConnection;
    
    public UvProfessorDAO() {
        this.databaseConnection = new DatabaseConnection();
    }

    @Override
    public int insertUvProfessor(UvProfessor uvProfessor) throws LogicException{
        int result = 0;
        String query = "INSERT INTO Profesoruv (numeroPersonal, Profesor_Usuario_idUsuario, idFacultad) VALUES (?, ?, ?)";
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uvProfessor.getPersonalNumber());
            statement.setInt(2, uvProfessor.getIdUser());
            statement.setString(3, uvProfessor.getIdDepartment());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("Error al insertar el profesor UV", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public UvProfessor getUvProfessorByIdUser(int idUser) throws LogicException{
        String query = "SELECT * FROM ProfesorUv WHERE Profesor_Usuario_idUsuario = ?";
        UvProfessor uvProfessorResult = new UvProfessor();
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                uvProfessorResult.setPersonalNumber(result.getString("numeroPersonal"));
                uvProfessorResult.setIdUser(result.getInt("Profesor_Usuario_idUsuario"));
                uvProfessorResult.setIdDepartment(result.getString("idFacultad"));
            }
        } catch (SQLException sqlException) {
            throw new LogicException("Error al obtener el profesor: ", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return uvProfessorResult;
    }
    
    @Override
    public int countUvProfessorByPersonalNumber(String personalNumber) throws LogicException {
        String query = "SELECT count(*) as count from ProfesorUv WHERE numeroPersonal = ?";
        
        int count = 0;
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, personalNumber);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                count = result.getInt("count");
            }
        } catch (SQLException sqlException) {
            throw new LogicException("Error al obtener el profesor: ", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return count;
    }
    
    @Override
    public String getDepartmentNameBelonging(int idUser) throws LogicException {
        String departmentName = null;
        String query = "SELECT f.nombre FROM profesoruv pu JOIN facultad f ON pu.idFacultad = f.idFacultad WHERE pu.Profesor_Usuario_idUsuario = ?";
        try {
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                departmentName = result.getString("nombre");
            }
        } catch (SQLException sqlException) {
            throw new LogicException("Error al obtener el nombre del departamento: ", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return departmentName; 
    }
}
