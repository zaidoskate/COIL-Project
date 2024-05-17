/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.Coordinator;
import logic.interfaces.CoordinatorManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.LogicException;


/**
 *
 * @author chima
 */
public class CoordinatorDAO implements CoordinatorManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public CoordinatorDAO() {
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int insertCoordinator(Coordinator coordinator) throws LogicException {
        int result = 0;
        String query = "INSERT INTO coordinador (usuario_idusuario) VALUES (?)";
        try{
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, coordinator.getIdUser());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int getIdCoordinatorByIdUser(int idUser) throws LogicException {
        int idObtained = 0;
        String query = "SELECT idCoordinador FROM coordinador WHERE usuario_idUsuario = ?";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idObtained = resultSet.getInt("idcoordinador");
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return idObtained;
    }
    
}
