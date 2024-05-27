package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.EvidenceFolder;
import logic.interfaces.EvidenceFolderManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import logic.LogicException;

/**
 *
 * @author chuch
 */
public class EvidenceFolderDAO implements EvidenceFolderManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     *
     * @param evidenceFolder
     * @return
     * @throws logic.LogicException
     */
    @Override
    public int insertEvidenceFolder(EvidenceFolder evidenceFolder) throws LogicException{
        int result = 0;
        String query = "INSERT INTO folderevidencia (colaboracion_idcolaboracion, idfolderevidencia, nombre, descripcion, fechacreacion) VALUES (?, ?, ?, ?, ?)";
        try{
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, evidenceFolder.getIdCollaboration());
            statement.setInt(2, evidenceFolder.getIdEvidenceFolder());
            statement.setString(3, evidenceFolder.getName());
            statement.setString(4, evidenceFolder.getDescription());
            statement.setString(5, evidenceFolder.getCreationDate());
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
     * @param idCollaboration
     * @return
     * @throws logic.LogicException
     */
    @Override
    public ArrayList<EvidenceFolder> getEvidenceFoldersByIdCollaboration(int idCollaboration) throws LogicException {
        String query = "SELECT * FROM folderevidencia WHERE colaboracion_idcolaboracion = ?";
        EvidenceFolder evidenceFolderResult;
        ArrayList<EvidenceFolder> evidenceFolders = new ArrayList();
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                evidenceFolderResult = new EvidenceFolder();
                evidenceFolderResult.setIdCollaboration(result.getInt("Colaboracion_idColaboracion"));
                evidenceFolderResult.setIdEvidenceFolder(result.getInt("idFolderEvidencia"));
                evidenceFolderResult.setName(result.getString("nombre"));
                evidenceFolderResult.setDescription(result.getString("descripcion"));
                evidenceFolderResult.setCreationDate(result.getString("fechaCreacion"));
                evidenceFolders.add(evidenceFolderResult);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return evidenceFolders;
    }
    
}
