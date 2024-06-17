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
 * Objeto de acceso a datos para manejar las operaciones relacionadas con los folders de evidencia en la base de datos.
 * Implementa la interfaz EvidenceFolderManagerInterface.
 * Proporciona métodos para insertar y recuperar folders de evidencia.
 * 
 * @autor chuch
 */
public class EvidenceFolderDAO implements EvidenceFolderManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Inserta un nuevo folder de evidencia en la base de datos.
     * 
     * @param evidenceFolder el objeto EvidenceFolder que contiene los detalles del folder de evidencia a insertar.
     * @return el número de filas afectadas por la consulta.
     * @throws LogicException si hay un problema de conexión a la base de datos.
     */
    @Override
    public int insertEvidenceFolder(EvidenceFolder evidenceFolder) throws LogicException {
        int result = 0;
        String query = "INSERT INTO folderevidencia (colaboracion_idcolaboracion, idfolderevidencia, nombre, descripcion, fechacreacion) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, evidenceFolder.getIdCollaboration());
            statement.setInt(2, evidenceFolder.getIdEvidenceFolder());
            statement.setString(3, evidenceFolder.getName());
            statement.setString(4, evidenceFolder.getDescription());
            statement.setString(5, evidenceFolder.getCreationDate());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Obtiene todos los folders de evidencia asociados a una colaboración específica.
     * 
     * @param idCollaboration el ID de la colaboración.
     * @return una lista de objetos EvidenceFolder asociados a la colaboración.
     * @throws LogicException si hay un problema de conexión a la base de datos.
     */
    @Override
    public ArrayList<EvidenceFolder> getEvidenceFoldersByIdCollaboration(int idCollaboration) throws LogicException {
        String query = "SELECT * FROM folderevidencia WHERE colaboracion_idcolaboracion = ?";
        EvidenceFolder evidenceFolderResult;
        ArrayList<EvidenceFolder> evidenceFolders = new ArrayList<>();
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                evidenceFolderResult = new EvidenceFolder();
                evidenceFolderResult.setIdCollaboration(result.getInt("Colaboracion_idColaboracion"));
                evidenceFolderResult.setIdEvidenceFolder(result.getInt("idFolderEvidencia"));
                evidenceFolderResult.setName(result.getString("nombre"));
                evidenceFolderResult.setDescription(result.getString("descripcion"));
                evidenceFolderResult.setCreationDate(result.getString("fechaCreacion"));
                evidenceFolders.add(evidenceFolderResult);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return evidenceFolders;
    }

    /**
     * Consulta si existe un folder de evidencias con un determinado nombre.
     * 
     * @param nameFolder el nombre del folder a consultar.
     * @param idCollaboration el ID de la colaboración donde se encuentra el folder.
     * @return un entero que devuelve 1 si existe un folder con ese nombre.
     * @throws LogicException cuando hay un problema con la conexión a la base de datos.
     */
    @Override
    public int checkEvidenceFolderNameByCollaboration(String nameFolder, int idCollaboration) throws LogicException {
        String query = "SELECT COUNT(*) as folders FROM folderevidencia WHERE nombre = ? and Colaboracion_idColaboracion = ?";
        int folders = 0;
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nameFolder);
            statement.setInt(2, idCollaboration);
            ResultSet accounts = statement.executeQuery();
            if (accounts.next() && accounts.getInt("folders") >= 1) {
                folders = 1;
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión a la base de datos, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return folders;
    }
}
