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
import java.util.ArrayList;
import logic.LogicException;

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
    public int InsertCollaborationOfferCandidate(CollaborationOfferCandidate collaborationOfferCandidate) throws LogicException {
        int result = 0;
        String query = "INSERT INTO candidatosofertacolaboracion (idofertacolaboracion, profesor_usuario_idusuario) VALUES (?, ?)";
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, collaborationOfferCandidate.getIdCollaboration());
            statement.setInt(2, collaborationOfferCandidate.getIdUser());
            result = statement.executeUpdate();
            
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion, intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public boolean professorHasAppliedForOffer(int idUser, int idCollaborationOffer) throws LogicException {
        boolean applied = false;
        String query = "SELECT COUNT(*) AS cantidad FROM candidatosofertacolaboracion WHERE idOfertaColaboracion = ? AND profesor_usuario_idusuario = ?";
        try {
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaborationOffer);
            statement.setInt(2, idUser);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                if (result.getInt("cantidad") == 1) {
                    applied = true;
                }
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion, intentelo de nuevo mas tarde", sqlException);
        }
        return applied;
    }
    
    @Override
    public ArrayList<CollaborationOfferCandidate> GetCollaborationOfferCandidateByIdCollaborationOffer(int idCollaboration) throws LogicException {
        String query = "SELECT * FROM candidatosofertacolaboracion WHERE idOfertaColaboracion = ?";
        ArrayList<CollaborationOfferCandidate> candidates = new ArrayList<>();
        try{
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                CollaborationOfferCandidate candidate = new CollaborationOfferCandidate();
                candidate.setIdCollaboration(result.getInt("idOfertaColaboracion"));
                candidate.setIdUser(result.getInt("Profesor_Usuario_idUsuario"));
                candidates.add(candidate);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return candidates;
    }
}
