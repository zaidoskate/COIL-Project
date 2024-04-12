/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import logic.interfaces.CollaborationOfferCandidateManagerInterface;
import logic.domain.CollaborationOfferCandidate;
import dataaccess.DatabaseConnection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author chima
 */
public class CollaborationOfferCandidateDAO implements CollaborationOfferCandidateManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public CollaborationOfferCandidateDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int InsertCollaborationOfferCandidate(CollaborationOfferCandidate collaborationOfferCandidate) {
        int result = 0;
        String query = "INSERT INTO candidatosofertacolaboracion (idofertacolaboracion, profesor_usuario_idusuario) VALUES (?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, collaborationOfferCandidate.getIdCollaboration());
            statement.setInt(2, collaborationOfferCandidate.getIdUser());
            result = statement.executeUpdate();
            
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
}
