package logic.DAOs;

import logic.interfaces.FinalDocumentationManagerInterface;
import logic.domain.FinalDocumentation;
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
import logic.LogicException;
import logic.FileDownloader;

/**
 * Data Access Object (DAO) para gestionar las operaciones relacionadas con la documentación final de las colaboraciones en la base de datos.
 * Implementa la interfaz FinalDocumentationManagerInterface.
 * 
 * @autor zaido
 */
public class FinalDocumentationDAO implements FinalDocumentationManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Inserta la documentación final asociada a una colaboración.
     * 
     * @param finalDocumentation una instancia de FinalDocumentation que contiene el ID de la colaboración.
     * @return un entero con el número de filas afectadas, si es 1 fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de SQL.
     */
    @Override
    public int addFinalDocumentation(FinalDocumentation finalDocumentation) throws LogicException {
        int added = 0;
        String query = "INSERT INTO documentacionFinal (Colaboracion_idColaboracion) VALUES (?)";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, finalDocumentation.getIdColaboration());
            added = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return added;
    }

    /**
     * Carga el feedback del profesor como un Blob en la base de datos.
     * 
     * @param finalDocumentation una instancia de FinalDocumentation que contiene el ID de la colaboración y la ruta al archivo del feedback del profesor.
     * @return un entero con el número de filas afectadas, si es 1 fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de archivo.
     */
    @Override
    public int uploadProfessorFeedback(FinalDocumentation finalDocumentation) throws LogicException {
        int result = 0;
        String query = "UPDATE DocumentacionFinal SET feedbackProfesor = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File professorFeedbackPdf = new File(finalDocumentation.getProfessorFeedback());
            FileInputStream fileInputStream = new FileInputStream(professorFeedbackPdf);
            statement.setBinaryStream(1, fileInputStream, (int) professorFeedbackPdf.length());
            statement.setInt(2, finalDocumentation.getIdColaboration());
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
     * Carga el feedback del profesor espejo como un Blob en la base de datos.
     * 
     * @param finalDocumentation una instancia de FinalDocumentation que contiene el ID de la colaboración y la ruta al archivo del feedback del profesor espejo.
     * @return un entero con el número de filas afectadas, si es 1 fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de archivo.
     */
    @Override
    public int uploadMirrorProfessorFeedback(FinalDocumentation finalDocumentation) throws LogicException {
        int result = 0;
        String query = "UPDATE DocumentacionFinal SET feedbackProfesorEspejo = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File mirrorProfessorFeedbackPdf = new File(finalDocumentation.getMirrorProfessorFeedback());
            FileInputStream fileInputStream = new FileInputStream(mirrorProfessorFeedbackPdf);
            statement.setBinaryStream(1, fileInputStream, (int) mirrorProfessorFeedbackPdf.length());
            statement.setInt(2, finalDocumentation.getIdColaboration());
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
     * Carga el feedback de los estudiantes como un Blob en la base de datos.
     * 
     * @param finalDocumentation una instancia de FinalDocumentation que contiene el ID de la colaboración y la ruta al archivo del feedback de los estudiantes.
     * @return un entero con el número de filas afectadas, si es 1 fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de archivo.
     */
    @Override
    public int uploadStudentsFeedback(FinalDocumentation finalDocumentation) throws LogicException {
        int result = -1;
        String query = "UPDATE DocumentacionFinal SET feedbackEstudiantado = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File studentsFeedbackPdf = new File(finalDocumentation.getStudentsFeedback());
            FileInputStream fileInputStream = new FileInputStream(studentsFeedbackPdf);
            statement.setBinaryStream(1, fileInputStream, (int) studentsFeedbackPdf.length());
            statement.setInt(2, finalDocumentation.getIdColaboration());
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
     * Carga el feedback de los estudiantes espejo como un Blob en la base de datos.
     * 
     * @param finalDocumentation una instancia de FinalDocumentation que contiene el ID de la colaboración y la ruta al archivo del feedback de los estudiantes espejo.
     * @return un entero con el número de filas afectadas, si es 1 fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de archivo.
     */
    @Override
    public int uploadMirrorStudentsFeedback(FinalDocumentation finalDocumentation) throws LogicException {
        int result = -1;
        String query = "UPDATE DocumentacionFinal SET feedbackEstudiantadoEspejo = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File mirrorStudentsFeedbackPdf = new File(finalDocumentation.getMirrorStudentsFeedback());
            FileInputStream fileInputStream = new FileInputStream(mirrorStudentsFeedbackPdf);
            statement.setBinaryStream(1, fileInputStream, (int) mirrorStudentsFeedbackPdf.length());
            statement.setInt(2, finalDocumentation.getIdColaboration());
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
     * Descarga el feedback del profesor desde la base de datos y lo guarda en una ruta especificada en el dispositivo.
     * 
     * @param finalDocumentation una instancia de FinalDocumentation que contiene el ID de la colaboración.
     * @param outputPath la ruta de descarga del archivo generado.
     * @return un entero que indica si la descarga fue exitosa, 1 si fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de archivo.
     */
    @Override
    public int obtainProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException {
        int result = 0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT feedbackProfesor FROM DocumentacionFinal WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, finalDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("feedbackProfesor");
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
     * Descarga el feedback del profesor espejo desde la base de datos y lo guarda en una ruta especificada en el dispositivo.
     * 
     * @param finalDocumentation una instancia de FinalDocumentation que contiene el ID de la colaboración.
     * @param outputPath la ruta de descarga del archivo generado.
     * @return un entero que indica si la descarga fue exitosa, 1 si fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de archivo.
     */
    @Override
    public int obtainMirrorProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException {
        int result = 0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT feedbackProfesorEspejo FROM DocumentacionFinal WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, finalDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("feedbackProfesorEspejo");
                FileDownloader.transformBlobToFile(outputPath, blob);
                result = 1;
            }
        } catch (SQLException sqlException) {
            throw new LogicException("Error al descargar el archivo a la base de datos", sqlException);
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
     * Descarga el feedback de los estudiantes desde la base de datos y lo guarda en una ruta especificada en el dispositivo.
     * 
     * @param finalDocumentation una instancia de FinalDocumentation que contiene el ID de la colaboración.
     * @param outputPath la ruta de descarga del archivo generado.
     * @return un entero que indica si la descarga fue exitosa, 1 si fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de archivo.
     */
    @Override
    public int obtainStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException {
        int result = 0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT feedbackEstudiantado FROM DocumentacionFinal WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, finalDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("feedbackEstudiantado");
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
     * Descarga el feedback de los estudiantes espejo desde la base de datos y lo guarda en una ruta especificada en el dispositivo.
     * 
     * @param finalDocumentation una instancia de FinalDocumentation que contiene el ID de la colaboración.
     * @param outputPath la ruta de descarga del archivo generado.
     * @return un entero que indica si la descarga fue exitosa, 1 si fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de archivo.
     */
    @Override
    public int obtainMirrorStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException {
        int result = 0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT feedbackEstudiantadoEspejo FROM DocumentacionFinal WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, finalDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("feedbackEstudiantadoEspejo");
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
     * Elimina un archivo cargado de la base de datos.
     * 
     * @param fileType el tipo de archivo a eliminar.
     * @param idCollaboration el ID de la colaboración de la que se desea eliminar el archivo.
     * @return un entero que indica el número de filas afectadas, si es 1 fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de SQL.
     */
    @Override
    public int deleteUploadedFile(String fileType, int idCollaboration) throws LogicException {
        int deleted = 0;
        String query = "UPDATE documentacionFinal SET " + fileType + " = NULL WHERE colaboracion_idcolaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            deleted = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return deleted;
    }

    /**
     * Consulta si una colaboración está registrada en la tabla de documentación final.
     * 
     * @param idCollaboration el ID de la colaboración a consultar.
     * @return un booleano que retorna true si la colaboración existe en la tabla de documentación final.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de SQL.
     */
    @Override
    public boolean isCollaborationRegistrated(int idCollaboration) throws LogicException {
        boolean registrated = false;
        String query = "SELECT COUNT(*) AS count FROM documentacionFinal WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet countCollaboration = statement.executeQuery();
            if (countCollaboration.next()) {
                int count = countCollaboration.getInt("count");
                registrated = count > 0;
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return registrated;
    }

    /**
     * Consulta si un tipo de archivo tiene un archivo cargado.
     * 
     * @param fileType el tipo de archivo, ya sea feedback del profesor, feedback del profesor espejo, feedback de estudiantes o feedback de estudiantes espejo.
     * @param idCollaboration el ID de la colaboración a consultar.
     * @return un booleano que retorna true si hay un archivo cargado.
     * @throws LogicException cuando hay problemas de conexión con la base de datos o ocurre un error de SQL.
     */
    @Override
    public boolean hasFileUploaded(String fileType, int idCollaboration) throws LogicException {
        boolean hasFileUploaded = false;
        String query = "SELECT " + fileType + " AS file FROM documentacionFinal WHERE colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                hasFileUploaded = resultSet.getString("file") != null;
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return hasFileUploaded;
    }
}
