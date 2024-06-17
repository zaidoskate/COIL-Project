package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.FileDownloader;
import logic.LogicException;
import logic.domain.ConcludedCollaboration;
import logic.interfaces.ConcludedCollaborationManagerInterface;

/**
 * Objeto de acceso a datos para manejar las operaciones relacionadas con las colaboraciones concluidas en la base de datos.
 * Implementa la interfaz ConcludedCollaborationManagerInterface.
 * Proporciona métodos para insertar, modificar y recuperar colaboraciones concluidas.
 * 
 * @author zaido
 */
public class ConcludedCollaborationDAO implements ConcludedCollaborationManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Insertar una nueva colaboración concluida.
     * 
     * @param concludedCollaboration Colaboración concluida que trae la calificación, si es visible o no, etc.
     * @return un entero que indica la cantidad de filas de la base de datos modificadas, si es 0 falló, si es 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int addConcludedCollaboration(ConcludedCollaboration concludedCollaboration) throws LogicException {
        int result = 0;
        String query = "INSERT INTO colaboracionconcluida(Colaboracion_idColaboracion, Usuario_idUsuario, visibilidad, calificacion, conclusion) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, concludedCollaboration.getIdColaboration());
            statement.setInt(2, concludedCollaboration.getIdUser());
            statement.setString(3, concludedCollaboration.getVisibility());
            statement.setInt(4, concludedCollaboration.getRating());
            statement.setString(5, concludedCollaboration.getConclusion());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Modificar la visibilidad de la colaboración concluida.
     * 
     * @param concludedCollaboration Colaboración concluida que contiene su ID y la nueva visibilidad.
     * @return un entero que indica la cantidad de filas de la base de datos modificadas, si es 0 falló, si es 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int updateVisibility(ConcludedCollaboration concludedCollaboration) throws LogicException {
        int result = 0;
        String query = "UPDATE colaboracionconcluida SET visibilidad = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, concludedCollaboration.getVisibility());
            statement.setInt(2, concludedCollaboration.getIdColaboration());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Modificar la calificación de la colaboración concluida.
     * 
     * @param concludedCollaboration Colaboración concluida que incluye su ID y la nueva calificación.
     * @return un entero que indica la cantidad de filas de la base de datos modificadas, si es 0 falló, si es 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int updateRating(ConcludedCollaboration concludedCollaboration) throws LogicException {
        int result = 0;
        String query = "UPDATE colaboracionconcluida SET calificacion = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, concludedCollaboration.getRating());
            statement.setInt(2, concludedCollaboration.getIdColaboration());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Cargar un archivo binario como constancia a la base de datos.
     * 
     * @param concludedCollaboration Colaboración concluida que incluye su ID y la ruta al archivo de constancia.
     * @return un entero que indica la cantidad de filas de la base de datos modificadas, si es 0 falló, si es 1 funcionó.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int uploadCertificates(ConcludedCollaboration concludedCollaboration) throws LogicException {
        int result = 0;
        String query = "UPDATE colaboracionconcluida SET constancias = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            FileInputStream fileInputStream = new FileInputStream(concludedCollaboration.getCertificatesFile());
            statement.setBinaryStream(1, fileInputStream, (int) concludedCollaboration.getCertificatesFile().length());
            statement.setInt(2, concludedCollaboration.getIdColaboration());
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
     * Descargar el archivo ZIP de constancias cargado.
     * 
     * @param concludedCollaboration Colaboración concluida que contiene su ID.
     * @param outputPath Ruta en el dispositivo local donde almacenar el archivo generado.
     * @return un entero que indica si la descarga fue exitosa, 1 si fue exitoso, 0 si falló.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public int obtainCertificates(ConcludedCollaboration concludedCollaboration, String outputPath) throws LogicException {
        int result = 0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT constancias FROM colaboracionconcluida WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, concludedCollaboration.getIdColaboration());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Blob blob = resultSet.getBlob("constancias");
                FileDownloader.transformBlobToFile(outputPath, blob);
                result = 1;
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new LogicException("No existe tal archivo en la ruta especificada", fileNotFoundException);
        } catch (IOException ioException) {
            throw new LogicException("Error de entrada y salida de archivos", ioException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Consultar si una colaboración concluida tiene constancias cargadas.
     * 
     * @param idCollaboration ID de la colaboración concluida a consultar.
     * @return boolean que retorna true si hay constancias cargadas, false en caso contrario.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public boolean hasCertificatesUploaded(int idCollaboration) throws LogicException {
        boolean hasCertificatesUploaded = false;
        String query = "SELECT constancias FROM colaboracionconcluida WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet certificates = statement.executeQuery();
            if (certificates.next() && certificates.getString("constancias") != null) {
                hasCertificatesUploaded = true;
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return hasCertificatesUploaded;
    }

    /**
     * Obtener todos los ID de colaboración y el usuario.
     * 
     * @return ArrayList de colaboraciones concluidas obtenidas.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public ArrayList<ConcludedCollaboration> getConcludedCollaborations() throws LogicException {
        String query = "SELECT Colaboracion_idColaboracion, Usuario_idUsuario FROM colaboracionconcluida";
        ArrayList<ConcludedCollaboration> concludedCollaborationsResult = new ArrayList<>();
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
                concludedCollaboration.setIdColaboration(result.getInt("Colaboracion_idColaboracion"));
                concludedCollaboration.setIdUser(result.getInt("Usuario_idUsuario"));
                concludedCollaborationsResult.add(concludedCollaboration);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return concludedCollaborationsResult;
    }

    /**
     * Obtener las colaboraciones concluidas que sean visibles.
     * 
     * @return ArrayList de colaboraciones concluidas obtenidas.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public ArrayList<ConcludedCollaboration> getConcludedCollaborationsByVisibility() throws LogicException {
        String query = "SELECT Colaboracion_idColaboracion, Usuario_idUsuario FROM colaboracionconcluida WHERE visibilidad = 'Visible'";
        ArrayList<ConcludedCollaboration> concludedCollaborationsResult = new ArrayList<>();
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
                concludedCollaboration.setIdColaboration(result.getInt("Colaboracion_idColaboracion"));
                concludedCollaboration.setIdUser(result.getInt("Usuario_idUsuario"));
                concludedCollaborationsResult.add(concludedCollaboration);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return concludedCollaborationsResult;
    }

    /**
     * Obtener una colaboración concluida por su ID de colaboración.
     * 
     * @param idCollaboration ID de la colaboración a consultar.
     * @return Colaboración concluida obtenida.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public ConcludedCollaboration getConcludedCollaborationById(int idCollaboration) throws LogicException {
        String query = "SELECT * FROM colaboracionconcluida WHERE Colaboracion_idColaboracion = ?";
        ConcludedCollaboration concludedCollaborationResult = new ConcludedCollaboration();
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                concludedCollaborationResult.setConclusion(result.getString("conclusion"));
                concludedCollaborationResult.setRating(result.getInt("calificacion"));
                concludedCollaborationResult.setVisibility(result.getString("visibilidad"));
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return concludedCollaborationResult;
    }
}
