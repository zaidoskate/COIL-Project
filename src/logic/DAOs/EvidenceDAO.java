package logic.DAOs;

import logic.interfaces.EvidenceManagerInterface;
import logic.domain.Evidence;
import dataaccess.DatabaseConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Blob;

/**
 *
 * @author chuch
 */
public class EvidenceDAO implements EvidenceManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     *
     * @param evidence
     * @return
     */
    @Override
    public int uploadEvidence(Evidence evidence) {
        Connection connection;
        int result = 1;
        String query = "INSERT INTO evidencia (FolderEvidencia_idFolderEvidencia, nombre, autor, fechacreacion, archivo) VALUES (?, ?, ?, ?, ?)";
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
            result = -1;
        } catch (FileNotFoundException fileNotFoundException) {
            result = -2;
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
     */
    @Override
    public int obtainEvidence(Evidence evidence, String outputPath) {
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        try {
            connection = this.DATABASE_CONNECTION.getConnection();
            statement = connection.prepareStatement("SELECT archivo FROM evidencia WHERE folderevidencia_idfolderevidencia = ?");
            statement.setInt(1, evidence.getIdFolderEvidence());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("archivo");
                InputStream inputStream = blob.getBinaryStream();
                File outputFile = new File(outputPath);
                fileOutputStream = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                return 1;
            }
        } catch (SQLException sqlException) {
            return -1;
        } catch (FileNotFoundException fileNotFoundException) {
            return -2;
        } catch (IOException ioException){
            return -3;
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return -4;
    }
    
}
