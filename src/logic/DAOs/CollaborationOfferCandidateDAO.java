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
 * @author zaido
 */
public class CollaborationOfferCandidateDAO implements CollaborationOfferCandidateManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     * Insertar un nuevo candidato de oferta de colaboración
     * @param collaborationOfferCandidate Candidato a insertar
     * @return  un entero con el número de rows en la base de datos que fueron modificadas. 1 funcionó correctamente.
     * @throws LogicException cuando hay un problema con la conexión a la base de datos.
     */
    @Override
    public int InsertCollaborationOfferCandidate(CollaborationOfferCandidate collaborationOfferCandidate) throws LogicException {
        int result = 0;
        String query = "INSERT INTO candidatosofertacolaboracion (idofertacolaboracion, profesor_usuario_idusuario) VALUES (?, ?)";
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, collaborationOfferCandidate.getIdCollaboration());
            statement.setInt(2, collaborationOfferCandidate.getIdUser());
            result = statement.executeUpdate();
            
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion, intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     * Consultar si un profesor ya es candidato de una oferta.
     * @param idUser id del profesor a consultar.
     * @param idCollaborationOffer id de la colaboración a consultar.
     * @return boolean que retorna true si ya se postuló o false en caso contrario.
     * @throws LogicException cuando hay un problema con la base de datos.
     */
    @Override
    public boolean professorHasAppliedForOffer(int idUser, int idCollaborationOffer) throws LogicException {
        boolean applied = false;
        String query = "SELECT COUNT(*) AS cantidad FROM candidatosofertacolaboracion WHERE idOfertaColaboracion = ? AND profesor_usuario_idusuario = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
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
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return applied;
    }
    
    /**
     * Obtener los candidatos de una oferta en especifico.
     * @param idCollaborationOffer id de la oferta a consultar.
     * @return ArrayList de candidatos obtenidos.
     * @throws LogicException cuando hay un problema con la base de datos.
     */
    @Override
    public ArrayList<CollaborationOfferCandidate> GetCollaborationOfferCandidateByIdCollaborationOffer(int idCollaborationOffer) throws LogicException {
        String query = "SELECT * FROM candidatosofertacolaboracion WHERE idOfertaColaboracion = ?";
        ArrayList<CollaborationOfferCandidate> candidates = new ArrayList<>();
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaborationOffer);
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
            DATABASE_CONNECTION.closeConnection();
        }
        return candidates;
    }

    /**
     * Eliminar una oferta de la base.
     * @param idOfferCollaboration id de la oferta a eliminar.
     * @return  un entero con el número de rows en la base de datos que fueron modificadas. 1 funcionó correctamente.
     * @throws LogicException cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public int deleteCollaborationOffer(int idOfferCollaboration) throws LogicException {
        int deletedRows = 0;
        String query = "DELETE FROM candidatosOfertaColaboracion WHERE idOfertaColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idOfferCollaboration);
            deletedRows = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return deletedRows;
    }
}
