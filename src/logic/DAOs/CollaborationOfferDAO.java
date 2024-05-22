
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
 * @author zaido
 */
public class CollaborationOfferDAO implements CollaborationOfferManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection(); 
    
    /**
     *
     * @param colaborationOffer
     * @return
     * @throws LogicException
     */
    @Override
    public int insertColaborationOffer(CollaborationOffer colaborationOffer) throws LogicException{
        int result = 0;
        String query = "INSERT INTO OfertaColaboracion (Profesor_Usuario_idUsuario, objetivo, temasInteres, cantidadEstudiantes, perfil, idioma, periodo, informacionAdcional, estadoOferta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, colaborationOffer.getIdUser());
            statement.setString(2, colaborationOffer.getObjective());
            statement.setString(3, colaborationOffer.getTopicsOfInterest());
            statement.setInt(4, colaborationOffer.getNumberOfStudents());
            statement.setString(5, colaborationOffer.getProfile());
            statement.setString(6, colaborationOffer.getLanguage());
            statement.setString(7, colaborationOffer.getPeriod());
            statement.setString(8, colaborationOffer.getAditionalInfo());
            statement.setString(9, "Pendiente");
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion, intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     *
     * @param idCollaborationOffer
     * @return
     * @throws LogicException
     */
    @Override
    public int deleteCollaborationOffer(int idCollaborationOffer) throws LogicException {
        int result = 0;
        String query = "DELETE FROM OfertaColaboracion WHERE idOfertaColaboracion = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaborationOffer);
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     *
     * @param idCollaborationOffer
     * @param decision
     * @return
     * @throws LogicException
     */
    @Override
    public int evaluateCollaborationOffer(int idCollaborationOffer, String decision) throws LogicException {
        int evaluationResult = 0;
        String query = "UPDATE ofertaColaboracion SET estadoOferta = '" + decision + "' WHERE idOfertaColaboracion = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaborationOffer);
            evaluationResult = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return evaluationResult;
    }
    
    /**
     *
     * @return
     * @throws LogicException
     */
    @Override
    public ArrayList<CollaborationOffer> getApprovedCollaborationOffer() throws LogicException {
        ArrayList<CollaborationOffer> approvedOffers = new ArrayList<>();
        String query = "SELECT * FROM OfertaColaboracion WHERE estadoOferta = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
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
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return approvedOffers;
    }
    
    /**
     *
     * @return
     * @throws LogicException
     */
    @Override
    public ArrayList<CollaborationOffer> getUnapprovedCollaborationOffer() throws LogicException {
        ArrayList<CollaborationOffer> unapprovedOffers = new ArrayList<>();
        String query = "SELECT * FROM OfertaColaboracion WHERE estadoOferta = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "Pendiente");
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
                unapprovedOffers.add(offer);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return unapprovedOffers;
    }

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    @Override
    public CollaborationOffer getProfessorApprovedOffer(int idUser) throws LogicException {
        CollaborationOffer offer = new CollaborationOffer();
        String query = "SELECT * FROM OfertaColaboracion WHERE Profesor_Usuario_idUsuario = ? AND estadoOferta = 'Aprobada'";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(idUser));
            ResultSet offerObtained = statement.executeQuery();
            while(offerObtained.next()) {
                offer.setIdCollaboration(offerObtained.getInt("idOfertaColaboracion"));
                offer.setIdUser(offerObtained.getInt("Profesor_Usuario_idUsuario"));
                offer.setObjective(offerObtained.getString("objetivo"));
                offer.setTopicsOfInterest(offerObtained.getString("temasInteres"));
                offer.setNumberOfStudents(offerObtained.getInt("cantidadEstudiantes"));
                offer.setProfile(offerObtained.getString("perfil"));
                offer.setLanguage(offerObtained.getString("idioma"));
                offer.setPeriod(offerObtained.getString("periodo"));
                offer.setAditionalInfo(offerObtained.getString("informacionAdcional"));
            }
            
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return offer;
    }
    
    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    @Override
    public boolean professorHasOffer(int idUser) throws LogicException {
        boolean result = false;
        String query = "SELECT COUNT(*) AS total FROM OfertaColaboracion WHERE Profesor_Usuario_idUsuario = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int count = resultSet.getInt("total");
                result = count > 0;
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
}
