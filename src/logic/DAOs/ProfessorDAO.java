/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.Professor;
import logic.interfaces.ProfessorManagerInterface;
import logic.LogicException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

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
    public int insertProfessor(Professor professor) throws LogicException {
        int result = 0;
        String query = "INSERT INTO Profesor VALUES (?)";
        try{
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, professor.getIdUser());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public String getUniversityFromAProfessor(int idUser) throws LogicException {
        String university = null;
        String queryUv = "SELECT COUNT(*) FROM profesorUv WHERE Profesor_Usuario_idUsuario = ?";
        String queryExternal = "SELECT Universidad.nombre FROM profesorExterno "
                + "JOIN universidad ON profesorExterno.Universidad_idUniversidad"
                + "= universidad.idUniversidad WHERE profesorExterno.Profesor_Usuario_idUsuario = ?";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statementUv = connection.prepareStatement(queryUv);
            statementUv.setInt(1, idUser);
            ResultSet resultUv = statementUv.executeQuery();
            if (resultUv.next() && resultUv.getInt(1) > 0) {
                university = "Universidad Veracruzana";
            } else {
                PreparedStatement statementExternal = connection.prepareStatement(queryExternal);
                statementExternal.setInt(1, idUser);
                ResultSet resultExternal = statementExternal.executeQuery();
                if (resultExternal.next()) {
                    university = resultExternal.getString("nombre");
                }
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return university;
    }

}
