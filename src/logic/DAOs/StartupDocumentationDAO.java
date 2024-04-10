/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.domain.StartupDocumentation;
import logic.interfaces.StartupDocumentationManagerInterface;

/**
 *
 * @author chuch
 */
public class StartupDocumentationDAO implements StartupDocumentationManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public StartupDocumentationDAO(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }
    
    @Override
    public int addStartupDocumentation(StartupDocumentation startupDocumentation) {
        int result = 0;
        String query = "INSERT INTO documentacioninicio(Colaboracion_idColaboracion) VALUES (?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, startupDocumentation.getIdColaboration());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int uploadSyllabus(StartupDocumentation startupDocumentation) {
        Connection connection;
        int result = 0;
        String query = "UPDATE DocumentacionInicio SET Syllabus = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File mirrorStudentsListPdf = new File(startupDocumentation.getSyllabusPath());
            FileInputStream fileInputStream = new FileInputStream(mirrorStudentsListPdf);
            statement.setBinaryStream(1, fileInputStream, (int) mirrorStudentsListPdf.length());
            statement.setInt(2, startupDocumentation.getIdColaboration());
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
    public int uploadStudentsList(StartupDocumentation startupDocumentation) {
        Connection connection;
        int result = 0;
        String query = "UPDATE DocumentacionInicio SET listaEstudiantado = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File mirrorStudentsListPdf = new File(startupDocumentation.getStudentsListPath());
            FileInputStream fileInputStream = new FileInputStream(mirrorStudentsListPdf);
            statement.setBinaryStream(1, fileInputStream, (int) mirrorStudentsListPdf.length());
            statement.setInt(2, startupDocumentation.getIdColaboration());
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
    public int uploadMirrorStudentsList(StartupDocumentation startupDocumentation) {
        Connection connection;
        int result = 0;
        String query = "UPDATE DocumentacionInicio SET listaEstudiantadoEspejo = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File mirrorStudentsListPdf = new File(startupDocumentation.getMirrorClassStudentsListPath());
            FileInputStream fileInputStream = new FileInputStream(mirrorStudentsListPdf);
            statement.setBinaryStream(1, fileInputStream, (int) mirrorStudentsListPdf.length());
            statement.setInt(2, startupDocumentation.getIdColaboration());
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
    public int obtainSyllabus(StartupDocumentation startupDocumentation, String outputPath) {
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        int result =0;
        try {
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT sylabbus FROM DocumentacionInicio WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, startupDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("syllabus");
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
    public int obtainStudentsList(StartupDocumentation startupDocumentation, String outputPath) {
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        int result =0;
        try {
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT listaEstudiantado FROM DocumentacionInicio WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, startupDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("listaEstudiantado");
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
    public int obtainMirrorStudentsList(StartupDocumentation startupDocumentation, String outputPath) {
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        int result =0;
        try {
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT listaEstudiantadoEspejo FROM DocumentacionInicio WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, startupDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("listaEstudiantadoEspejo");
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
