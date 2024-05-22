package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.EvidenceFolder;
import logic.interfaces.EvidenceFolderManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

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
     */
    @Override
    public int insertEvidenceFolder(EvidenceFolder evidenceFolder){
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
            result = -1;
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     *
     * @param idCollaboration
     * @return
     */
    public EvidenceFolder getEvidenceFolderByIdCollaboration(int idCollaboration) {
        String query = "SELECT * FROM folderevidencia WHERE colaboracion_idcolaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        EvidenceFolder evidenceFolderResult = new EvidenceFolder();
        try{
            connection = this.DATABASE_CONNECTION.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            result = statement.executeQuery();
            while(result.next()) {
                evidenceFolderResult.setIdCollaboration(result.getInt("Colaboracion_idColaboracion"));
                evidenceFolderResult.setIdEvidenceFolder(result.getInt("idFolderEvidencia"));
                evidenceFolderResult.setName(result.getString("nombre"));
                evidenceFolderResult.setDescription(result.getString("descripcion"));
                evidenceFolderResult.setCreationDate(result.getString("fechaCreacion"));
            }
        } catch(SQLException sqlException) {
            evidenceFolderResult = null;
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return evidenceFolderResult;
    }
    
}
