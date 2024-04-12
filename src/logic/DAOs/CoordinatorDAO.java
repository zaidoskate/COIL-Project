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
import java.sql.SQLException;


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
    public int insertCoordinator(Coordinator coordinator){
        int result = 0;
        String query = "INSERT INTO coordinador (idcoordinador, usuario_idusuario) VALUES (?, ?)";
        try{
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, coordinator.getIdCoordinator());
            statement.setInt(2, coordinator.getIdUser());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
}
