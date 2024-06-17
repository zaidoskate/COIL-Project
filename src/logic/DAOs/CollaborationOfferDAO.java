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
 * Objeto de acceso a datos para manejar las operaciones relacionadas con las ofertas de colaboración en la base de datos.
 * Implementa la interfaz CollaborationOfferManagerInterface.
 * Proporciona métodos para insertar, eliminar, evaluar y recuperar ofertas de colaboración.
 * 
 * @autor zaido
 */
public class CollaborationOfferDAO implements CollaborationOfferManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Insertar una nueva oferta de colaboración en la base de datos.
     * 
     * @param collaborationOffer Un objeto CollaborationOffer que tiene toda la información de la oferta.
     * @return Un entero con el número de filas en la base de datos que fueron modificadas. 1 si funcionó correctamente.
     * @throws LogicException Cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public int insertColaborationOffer(CollaborationOffer collaborationOffer) throws LogicException {
        int result = 0;
        String query = "INSERT INTO OfertaColaboracion (Profesor_Usuario_idUsuario, objetivo, temasInteres, cantidadEstudiantes, perfil, idioma, periodo, informacionAdcional, estadoOferta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, collaborationOffer.getIdUser());
            statement.setString(2, collaborationOffer.getObjective());
            statement.setString(3, collaborationOffer.getTopicsOfInterest());
            statement.setInt(4, collaborationOffer.getNumberOfStudents());
            statement.setString(5, collaborationOffer.getProfile());
            statement.setString(6, collaborationOffer.getLanguage());
            statement.setString(7, collaborationOffer.getPeriod());
            statement.setString(8, collaborationOffer.getAditionalInfo());
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
     * Eliminar una oferta de colaboración de la base de datos.
     * 
     * @param idCollaborationOffer ID de la oferta a eliminar.
     * @return Un entero con el número de filas en la base de datos que fueron modificadas. 1 si funcionó correctamente.
     * @throws LogicException Cuando hay un problema de conexión con la base de datos.
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
     * Cambiar el estado de una oferta de colaboración a Aprobada o Rechazada.
     * 
     * @param idCollaborationOffer ID de la oferta a cambiar.
     * @param decision Cadena que puede ser "Aprobada" o "Rechazada".
     * @return Un entero con el número de filas en la base de datos que fueron modificadas. 1 si funcionó correctamente.
     * @throws LogicException Cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public int evaluateCollaborationOffer(int idCollaborationOffer, String decision) throws LogicException {
        int evaluationResult = 0;
        String query = "UPDATE ofertaColaboracion SET estadoOferta = ? WHERE idOfertaColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, decision);
            statement.setInt(2, idCollaborationOffer);
            evaluationResult = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return evaluationResult;
    }

    /**
     * Obtener todas las ofertas de colaboración aprobadas.
     * 
     * @return ArrayList de ofertas de colaboración aprobadas.
     * @throws LogicException Cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public ArrayList<CollaborationOffer> getApprovedCollaborationOffer() throws LogicException {
        ArrayList<CollaborationOffer> approvedOffers = new ArrayList<>();
        String query = "SELECT * FROM OfertaColaboracion WHERE estadoOferta = 'Aprobada'";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet offersObtained = statement.executeQuery();
            while (offersObtained.next()) {
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
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return approvedOffers;
    }

    /**
     * Obtener todas las ofertas de colaboración pendientes.
     * 
     * @return ArrayList de ofertas de colaboración pendientes.
     * @throws LogicException Cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public ArrayList<CollaborationOffer> getUnapprovedCollaborationOffer() throws LogicException {
        ArrayList<CollaborationOffer> unapprovedOffers = new ArrayList<>();
        String query = "SELECT * FROM OfertaColaboracion WHERE estadoOferta = 'Pendiente'";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet offersObtained = statement.executeQuery();
            while (offersObtained.next()) {
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
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return unapprovedOffers;
    }

    /**
     * Obtener la oferta de colaboración aprobada de un profesor en específico.
     * 
     * @param idUser ID del profesor.
     * @return Una oferta de colaboración aprobada.
     * @throws LogicException Cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public CollaborationOffer getProfessorApprovedOffer(int idUser) throws LogicException {
        CollaborationOffer offer = new CollaborationOffer();
        String query = "SELECT * FROM OfertaColaboracion WHERE Profesor_Usuario_idUsuario = ? AND estadoOferta = 'Aprobada'";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet offerObtained = statement.executeQuery();
            if (offerObtained.next()) {
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
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return offer;
    }

    /**
     * Consultar si el profesor tiene una oferta de colaboración con estado Aprobada o Pendiente.
     * 
     * @param idUser ID del profesor a consultar.
     * @return Boolean que devuelve true si el profesor tiene una oferta con esos estados, false en caso contrario.
     * @throws LogicException Cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public boolean professorHasOffer(int idUser) throws LogicException {
        boolean result = false;
        String query = "SELECT COUNT(*) AS total FROM OfertaColaboracion WHERE Profesor_Usuario_idUsuario = ? AND estadoOferta IN ('Pendiente', 'Aprobada')";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("total");
                result = count > 0;
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
}
