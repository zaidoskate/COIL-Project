/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import logic.interfaces.FinalDocumentationManagerInterface;
import logic.domain.FinalDocumentation;
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
 * @author zaido
 */
public class FinalDocumentationDAO implements FinalDocumentationManagerInterface{
    private final DatabaseConnection databaseConnection;
    
    public FinalDocumentationDAO() {
        this.databaseConnection = new DatabaseConnection();
    }

    @Override
    public int uploadProfessorFeedback(FinalDocumentation finalDocumentation) {
        Connection connection;
        int result = 0;
        String query = "UPDATE DocumentacionFinal SET feedbackProfesor = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File professorFeedbackPdf = new File(finalDocumentation.getProfessorFeedback());
            FileInputStream fileInputStream = new FileInputStream(professorFeedbackPdf);
            statement.setBinaryStream(1, fileInputStream, (int) professorFeedbackPdf.length());
            statement.setInt(2, finalDocumentation.getIdColaboration());
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
    public int uploadMirrorProfessorFeedback(FinalDocumentation finalDocumentation) {
        Connection connection;
        int result = -1;
        String query = "UPDATE DocumentacionFinal SET feedbackProfesorEspejo = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File professorFeedbackPdf = new File(finalDocumentation.getMirrorProfessorFeedback());
            FileInputStream fileInputStream = new FileInputStream(professorFeedbackPdf);
            statement.setBinaryStream(1, fileInputStream, (int) professorFeedbackPdf.length());
            statement.setInt(2, finalDocumentation.getIdColaboration());
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
    public int uploadStudentsFeedback(FinalDocumentation finalDocumentation) {
        Connection connection;
        int result = -1;
        String query = "UPDATE DocumentacionFinal SET feedbackEstudiantado = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File professorFeedbackPdf = new File(finalDocumentation.getStudentsFeedback());
            FileInputStream fileInputStream = new FileInputStream(professorFeedbackPdf);
            statement.setBinaryStream(1, fileInputStream, (int) professorFeedbackPdf.length());
            statement.setInt(2, finalDocumentation.getIdColaboration());
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
    public int uploadMirrorStudentsFeedback(FinalDocumentation finalDocumentation) {
        Connection connection;
        int result = -1;
        String query = "UPDATE DocumentacionFinal SET feedbackEstudiantadoEspejo = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File professorFeedbackPdf = new File(finalDocumentation.getMirrorStudentsFeedback());
            FileInputStream fileInputStream = new FileInputStream(professorFeedbackPdf);
            statement.setBinaryStream(1, fileInputStream, (int) professorFeedbackPdf.length());
            statement.setInt(2, finalDocumentation.getIdColaboration());
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
    public int obtainProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) {
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        int result = 0;
        try {
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT feedbackProfesor FROM DocumentacionFinal WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, finalDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("feedbackProfesor");
                InputStream inputStream = blob.getBinaryStream();
                File outputFile = new File(outputPath);
                fileOutputStream = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                result = 1;
            }
        } catch (SQLException sqlException) {
            result = -1;
        } catch (FileNotFoundException fileNotFoundException) {
            result = -2;
        } catch (IOException ioException){
            result = -3;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int obtainMirrorProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) {
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        int result = 0;
        try {
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT feedbackProfesorEspejo FROM DocumentacionFinal WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, finalDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("feedbackProfesorEspejo");
                InputStream inputStream = blob.getBinaryStream();
                File outputFile = new File(outputPath);
                fileOutputStream = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                result = 1;
            }
        } catch (SQLException sqlException) {
            result = -1;
        } catch (FileNotFoundException fileNotFoundException) {
            result = -2;
        } catch (IOException ioException){
            result = -3;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int obtainStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) {
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        int result = 0;
        try {
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT feedbackEstudiantado FROM DocumentacionFinal WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, finalDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("feedbackEstudiantado");
                InputStream inputStream = blob.getBinaryStream();
                File outputFile = new File(outputPath);
                fileOutputStream = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                result = 1;
            }
        } catch (SQLException sqlException) {
            result = -1;
        } catch (FileNotFoundException fileNotFoundException) {
            result = -2;
        } catch (IOException ioException){
            result = -3;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int obtainMirrorStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) {
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        int result = 0;
        try {
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT feedbackEstudiantadoEspejo FROM DocumentacionFinal WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, finalDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("feedbackEstudiantadoEspejo");
                InputStream inputStream = blob.getBinaryStream();
                File outputFile = new File(outputPath);
                fileOutputStream = new FileOutputStream(outputFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
                result = 1;
            }
        } catch (SQLException sqlException) {
            result = -1;
        } catch (FileNotFoundException fileNotFoundException) {
            result = -2;
        } catch (IOException ioException){
            result = -3;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
}
