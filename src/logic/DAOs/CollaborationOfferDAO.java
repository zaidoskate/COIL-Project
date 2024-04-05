/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.CollaborationOffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import logic.interfaces.CollaborationOfferManagerInterface;
/**
 *
 * @author chima
 */
public class CollaborationOfferDAO implements CollaborationOfferManagerInterface {
    private final DatabaseConnection databaseConnection; 
    
    public CollaborationOfferDAO() {
        this.databaseConnection = new DatabaseConnection();
    }
    
   @Override
    public int insertColaborationOffer(CollaborationOffer colaborationOffer) {
        int result = 0;
        String query = "INSERT INTO OfertaColaboracion (idofertacolaboracion, objetivo, temasInteres, cantidadEstudiantes, perfil, idioma, periodo, informacionAdcional) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, colaborationOffer.getIdCollaboration());
            statement.setString(2, colaborationOffer.getObjective());
            statement.setString(3, colaborationOffer.getTopicsOfInterest());
            statement.setInt(4, colaborationOffer.getNumberOfStudents());
            statement.setString(5, colaborationOffer.getProfile());
            statement.setString(6, colaborationOffer.getLanguage());
            statement.setString(7, colaborationOffer.getPeriod());
            statement.setString(8, colaborationOffer.getAditionalInfo());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            return result;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
}
