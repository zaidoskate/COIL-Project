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

public class FinalDocumentationDAO implements FinalDocumentationManagerInterface {
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    
    @Override
    public int addFinalDocumentation(FinalDocumentation finalDocumentation) throws LogicException {
        int added = 0;
        String query = "INSERT INTO documentacionFinal (Colaboracion_idColaboracion) VALUES (?)";
        try {
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, finalDocumentation.getIdColaboration());
            added = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return added;
    }
    
    @Override
    public int uploadProfessorFeedback(FinalDocumentation finalDocumentation) throws LogicException{
        int result = 0;
        String query = "UPDATE DocumentacionFinal SET feedbackProfesor = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.databaseConnection.getConnection();
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
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int uploadMirrorProfessorFeedback(FinalDocumentation finalDocumentation) throws LogicException {
        int result = 0;
        String query = "UPDATE DocumentacionFinal SET feedbackProfesorEspejo = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File professorFeedbackPdf = new File(finalDocumentation.getMirrorProfessorFeedback());
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
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int uploadStudentsFeedback(FinalDocumentation finalDocumentation) throws LogicException {
        int result = -1;
        String query = "UPDATE DocumentacionFinal SET feedbackEstudiantado = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File professorFeedbackPdf = new File(finalDocumentation.getStudentsFeedback());
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
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int uploadMirrorStudentsFeedback(FinalDocumentation finalDocumentation) throws LogicException {
        int result = -1;
        String query = "UPDATE DocumentacionFinal SET feedbackEstudiantadoEspejo = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File professorFeedbackPdf = new File(finalDocumentation.getMirrorStudentsFeedback());
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
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int obtainProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException {
        int result = 0;
        try {
            Connection connection = this.databaseConnection.getConnection();
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
        } catch (IOException ioException){
            throw new LogicException("Error de entrada y salida de archivos", ioException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int obtainMirrorProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException {
        int result = 0;
        try {
            Connection connection = this.databaseConnection.getConnection();
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
        } catch (IOException ioException){
            throw new LogicException("Error de entrada y salida de archivos", ioException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int obtainStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException {
        int result = 0;
        try {
            Connection connection = this.databaseConnection.getConnection();
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
        } catch (IOException ioException){
            throw new LogicException("Error de entrada y salida de archivos", ioException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int obtainMirrorStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException {
        int result = 0;
        try {
            Connection connection = this.databaseConnection.getConnection();
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
        } catch (IOException ioException){
            throw new LogicException("Error de entrada y salida de archivos", ioException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int deleteUploadedFile(String fileType, int idCollaboration) throws LogicException {
        int deleted = 0;
        String query = "UPDATE documentacionFinal SET " + fileType + " = NULL WHERE colaboracion_idcolaboracion = ?";
        try {
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            deleted = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return deleted;
    }
    
    @Override
    public boolean isCollaborationRegistrated(int idCollaboration) throws LogicException {
        boolean registrated = false;
        String query = "SELECT COUNT(*) AS count FROM documentacionFinal WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet countCollaboration = statement.executeQuery();
            if(countCollaboration.next()) {
                int count = countCollaboration.getInt("count");
                registrated = count > 0;
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return registrated;
    }
    
    @Override
    public boolean hasFileUploaded(String fileType, int idCollaboration) throws LogicException {
        boolean hasFileUploaded = false;
        String query = "SELECT " + fileType + " AS file FROM documentacionFinal WHERE colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getString("file") != null) {
                    hasFileUploaded = true;
                }
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return hasFileUploaded;
    }
}
