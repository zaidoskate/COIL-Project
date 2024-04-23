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
import java.sql.ResultSet;
import java.util.ArrayList;
import logic.LogicException;
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
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public ArrayList<CollaborationOffer> getApprovedCollaborationOffer() throws LogicException {
        ArrayList<CollaborationOffer> approvedOffers = new ArrayList<>();
        String query = "SELECT * FROM OfertaColaboracion WHERE estadoOferta = ?";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "Aprobada");
            ResultSet offersObtained = statement.executeQuery();
            while(offersObtained.next()) {
                CollaborationOffer offer = new CollaborationOffer();
                offer.setIdCollaboration(offersObtained.getInt("idOfertaColaboracion"));
                offer.setIdUser(offersObtained.getInt("Profesor_Usuario_idUsuario"));
                offer.setObjective(offersObtained.getString("objetivo"));
                offer.setTopicsOfInterest(offersObtained.getString("temasInteres"));
                offer.setNumberOfStudents(offersObtained.getInt("cantidadEstudiantes"));
                offer.setProfile(offersObtained.getString("perfil"));
                offer.setLanguage(offersObtained.getString("idioma"));
                offer.setPeriod(offersObtained.getString("periodo"));
                offer.setAditionalInfo(offersObtained.getString("informacionAdcional"));
                approvedOffers.add(offer);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("Error al obtener las ofertas aprobadas", sqlException);
        }
        return approvedOffers;
    }
}
