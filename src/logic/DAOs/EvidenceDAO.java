/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author chima
 */
public class EvidenceDAO implements EvidenceManagerInterface {
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    
    private String parseDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    @Override
    public int uploadEvidence(Evidence evidence) {
        Connection connection;
        int result = 1;
        String query = "INSERT INTO evidencia (FolderEvidencia_idFolderEvidencia, nombre, autor, fechacreacion, archivo) VALUES (?, ?, ?, ?, ?)";
        try {
            connection = this.databaseConnection.getConnection();
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
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int obtainEvidence(Evidence evidence, String outputPath) throws LogicException {
        int downloaded = 0;
        try {
            Connection connection = this.databaseConnection.getConnection();
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
            databaseConnection.closeConnection();
        }
        return -4;
    }
    
    @Override
    public ArrayList<Evidence> getAllEvidencesByIdCollaboration(int idCollaboration) throws LogicException {
        ArrayList<Evidence> collaborationEvidences = new ArrayList<>();
        String query = "SELECT e.* FROM evidencia e INNER JOIN folderEvidencia fe ON e.FolderEvidencia_idFolderEvidencia = fe.idFolderEvidencia WHERE fe.Colaboracion_idColaboracion = ?";
        try {
            Connection connection = databaseConnection.getConnection();
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
    
}
