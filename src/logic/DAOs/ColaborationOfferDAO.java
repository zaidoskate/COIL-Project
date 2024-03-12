/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.ColaborationOffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import logic.interfaces.ColaborationOfferManagerInterface;
/**
 *
 * @author chima
 */
public class ColaborationOfferDAO implements ColaborationOfferManagerInterface {
    private final DatabaseConnection databaseConnection; 
    
    public ColaborationOfferDAO() {
        this.databaseConnection = new DatabaseConnection();
    }
    
   @Override
    public int insertColaborationOffer(ColaborationOffer colaborationOffer) {
        int result = 0;
        String query = "INSERT INTO OfertaColaboracion VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, colaborationOffer.getIdOffer());
            statement.setString(2, colaborationOffer.getApplicationDate());
            statement.setString(3, colaborationOffer.getCorrespondenceDate());
            statement.setString(4, colaborationOffer.getCorrespondenceDate());
            statement.setString(5, colaborationOffer.getStatus());
            statement.setInt(6, colaborationOffer.getIdUser());
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
    public ArrayList<ColaborationOffer> getColaborationOfferByIdUser (ColaborationOffer colaborationOffer) {
        ArrayList<ColaborationOffer> colaborationOfferByUser = new ArrayList<>();
        String query = "SELECT idOferta FROM OfertaColaboracion WHERE Profesor_Usuario_idUsuario = ?";
        try{
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, colaborationOffer.getIdOffer());
            ResultSet idOffer = statement.executeQuery();
            while(idOffer.next()) {
                colaborationOffer = new ColaborationOffer();
                colaborationOffer.setIdOffer(idOffer.getInt("idOferta"));
                colaborationOfferByUser.add(colaborationOffer);
            }
        } catch (SQLException sqlException) {
            return null;
        } finally {
            databaseConnection.closeConnection();
        }
        return colaborationOfferByUser;
    }
    
}
