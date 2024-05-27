package logic.DAOs;

import logic.interfaces.EvidenceManagerInterface;
import logic.domain.Evidence;
import dataaccess.DatabaseConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import logic.FileDownloader;
import logic.LogicException;

/**
 *
 * @author chuch
 */
public class EvidenceDAO implements EvidenceManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    private String parseDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     *
     * @param evidence
     * @return
     * @throws logic.LogicException
     */
    @Override
    public int uploadEvidence(Evidence evidence) throws LogicException {
        Connection connection;
        int result = 1;
        String query = "INSERT INTO evidencia ( FolderEvidencia_idFolderEvidencia, nombre, autor, fechacreacion, archivo) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File evidencePDF = new File(evidence.getFile());
            FileInputStream fileInputStream = new FileInputStream(evidencePDF);
            statement.setInt(1, evidence.getIdFolderEvidence());
            statement.setString(2, evidence.getName());
            statement.setString(3, evidence.getAuthor());
            statement.setString(4, evidence.getDateOfCreation());
            statement.setBinaryStream(5, fileInputStream, (int) evidencePDF.length());
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new LogicException("No existe tal archivo en la ruta especificada", fileNotFoundException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     *
     * @param evidence
     * @param outputPath
     * @return
     * @throws logic.LogicException
     */
    @Override
    public int obtainEvidence(Evidence evidence, String outputPath) throws LogicException {
        int downloaded = -1;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT archivo FROM evidencia WHERE nombre = ?");
            statement.setString(1, evidence.getName());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("archivo");
                FileDownloader.transformBlobToFile(outputPath, blob);
                downloaded = 1;
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new LogicException("No existe tal archivo en la ruta especificada", fileNotFoundException);
        } catch (IOException ioException){
            throw new LogicException("Hubo un problema al descargar el archivo, inténtelo de nuevo", ioException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return downloaded;
    }
    
    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    @Override
    public ArrayList<Evidence> getAllEvidencesByIdCollaboration(int idCollaboration) throws LogicException {
        ArrayList<Evidence> collaborationEvidences = new ArrayList<>();
        String query = "SELECT e.* FROM evidencia e INNER JOIN folderEvidencia fe ON e.FolderEvidencia_idFolderEvidencia = fe.idFolderEvidencia WHERE fe.Colaboracion_idColaboracion = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet evidences = statement.executeQuery();
            while(evidences.next()) {
                Evidence evidence = new Evidence();
                evidence.setIdFolderEvidence(evidences.getInt("FolderEvidencia_idFolderEvidencia"));
                evidence.setName(evidences.getString("nombre"));
                evidence.setAuthor(evidences.getString("autor"));
                evidence.setDateOfCreation(parseDateToString(evidences.getDate("fechaCreacion")));
                collaborationEvidences.add(evidence);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        }
        return collaborationEvidences;
    }
    
    @Override
    public ArrayList<Evidence> getEvidencesByIdFolder(int idFolder) throws LogicException {
        ArrayList<Evidence> evidences = new ArrayList<>();
        String query = "SELECT e.* FROM evidencia e WHERE e.FolderEvidencia_idFolderEvidencia = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFolder);
            ResultSet resultEvidences = statement.executeQuery();
            while(resultEvidences.next()) {
                Evidence evidence = new Evidence();
                evidence.setIdFolderEvidence(resultEvidences.getInt("FolderEvidencia_idFolderEvidencia"));
                evidence.setName(resultEvidences.getString("nombre"));
                evidence.setAuthor(resultEvidences.getString("autor"));
                evidence.setDateOfCreation(parseDateToString(resultEvidences.getDate("fechaCreacion")));
                evidences.add(evidence);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        }
        return evidences;
    }
    
    @Override
    public int deleteEvidenceByName(String name) throws LogicException {
        int result = 1;
        String query = "DELETE FROM evidencia WHERE nombre = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            result = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
}
