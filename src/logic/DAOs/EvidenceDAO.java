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
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import logic.FileDownloader;
import logic.LogicException;

/**
 * Objeto de acceso a datos para manejar las operaciones relacionadas con la evidencia en la base de datos.
 * Implementa la interfaz EvidenceManagerInterface.
 * Proporciona métodos para subir, descargar y recuperar evidencias.
 * 
 * @autor chuch
 */
public class EvidenceDAO implements EvidenceManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Convierte un objeto Date a una cadena con el formato 'yyyy-MM-dd'.
     * 
     * @param date la fecha a convertir.
     * @return la cadena de fecha formateada.
     */
    private String parseDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * Sube evidencia a la base de datos.
     * 
     * @param evidence el objeto Evidence que contiene los detalles de la evidencia a subir.
     * @return el número de filas afectadas por la consulta.
     * @throws LogicException si hay un problema de conexión a la base de datos o si el archivo no se encuentra.
     */
    @Override
    public int uploadEvidence(Evidence evidence) throws LogicException {
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
        } catch (SQLIntegrityConstraintViolationException sqlConstraintException) {
            throw new LogicException("El nombre de la evidencia ya existe, prueba con otro", sqlConstraintException);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new LogicException("No existe tal archivo en la ruta especificada", fileNotFoundException);
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Obtiene una evidencia de la base de datos y la guarda en la ruta especificada.
     * 
     * @param evidence el objeto Evidence que contiene los detalles de la evidencia a obtener.
     * @param outputPath la ruta donde se guardará el archivo descargado.
     * @return 1 si la descarga fue exitosa, -1 en caso contrario.
     * @throws LogicException si hay un problema de conexión a la base de datos, si el archivo no se encuentra, o si hay un problema de E/S.
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
        } catch (IOException ioException) {
            throw new LogicException("Hubo un problema al descargar el archivo, inténtelo de nuevo", ioException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return downloaded;
    }

    /**
     * Obtiene todas las evidencias asociadas a una colaboración específica.
     * 
     * @param idCollaboration el ID de la colaboración.
     * @return una lista de objetos Evidence asociados a la colaboración.
     * @throws LogicException si hay un problema de conexión a la base de datos.
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
            while (evidences.next()) {
                Evidence evidence = new Evidence();
                evidence.setIdFolderEvidence(evidences.getInt("FolderEvidencia_idFolderEvidencia"));
                evidence.setName(evidences.getString("nombre"));
                evidence.setAuthor(evidences.getString("autor"));
                evidence.setDateOfCreation(parseDateToString(evidences.getDate("fechaCreacion")));
                collaborationEvidences.add(evidence);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        }
        return collaborationEvidences;
    }

    /**
     * Obtiene todas las evidencias asociadas a un folder específico.
     * 
     * @param idFolder el ID del folder.
     * @return una lista de objetos Evidence asociados al folder.
     * @throws LogicException si hay un problema de conexión a la base de datos.
     */
    @Override
    public ArrayList<Evidence> getEvidencesByIdFolder(int idFolder) throws LogicException {
        ArrayList<Evidence> evidences = new ArrayList<>();
        String query = "SELECT e.* FROM evidencia e WHERE e.FolderEvidencia_idFolderEvidencia = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idFolder);
            ResultSet resultEvidences = statement.executeQuery();
            while (resultEvidences.next()) {
                Evidence evidence = new Evidence();
                evidence.setIdFolderEvidence(resultEvidences.getInt("FolderEvidencia_idFolderEvidencia"));
                evidence.setName(resultEvidences.getString("nombre"));
                evidence.setAuthor(resultEvidences.getString("autor"));
                evidence.setDateOfCreation(parseDateToString(resultEvidences.getDate("fechaCreacion")));
                evidences.add(evidence);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        }
        return evidences;
    }

    /**
     * Elimina una evidencia de la base de datos por su nombre.
     * 
     * @param name el nombre de la evidencia a eliminar.
     * @return el número de filas afectadas por la consulta.
     * @throws LogicException si hay un problema de conexión a la base de datos.
     */
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
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
}
