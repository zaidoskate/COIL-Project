/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author chima
 */
public class EvidenceFolderDAO implements EvidenceFolderManagerInterface {
    private final DatabaseConnection databaseConnection; 
    
    public EvidenceFolderDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int insertEvidenceFolder(EvidenceFolder evidenceFolder){
        int result = 0;
        String query = "INSERT INTO folderevidencia (colaboracion_idcolaboracion, idfolderevidencia, nombre, descripcion, fechacreacion) VALUES (?, ?, ?, ?, ?)";
        try{
            Connection connection = databaseConnection.getConnection();
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
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    public EvidenceFolder getEvidenceFolderByIdCollaboration(int idCollaboration) {
        String query = "SELECT * FROM folderevidencia WHERE colaboracion_idcolaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        EvidenceFolder evidenceFolderResult = new EvidenceFolder();
        try{
            connection = this.databaseConnection.getConnection();
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
            databaseConnection.closeConnection();
        }
        return evidenceFolderResult;
    }
    
}
