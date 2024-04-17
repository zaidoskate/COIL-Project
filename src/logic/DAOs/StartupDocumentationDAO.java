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
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import logic.FileDownloader;
import logic.LogicException;
import logic.domain.StartupDocumentation;
import logic.interfaces.StartupDocumentationManagerInterface;

/**
 *
 * @author chuch
 */
public class StartupDocumentationDAO implements StartupDocumentationManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public StartupDocumentationDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int addStartupDocumentation(StartupDocumentation startupDocumentation) throws LogicException{
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
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int uploadSyllabus(StartupDocumentation startupDocumentation) throws LogicException {
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
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new LogicException("No existe tal archivo en la ruta especificada", fileNotFoundException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int uploadStudentsList(StartupDocumentation startupDocumentation) throws LogicException {
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
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new LogicException("No existe tal archivo en la ruta especificada", fileNotFoundException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int uploadMirrorStudentsList(StartupDocumentation startupDocumentation) throws LogicException {
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
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new LogicException("No existe tal archivo en la ruta especificada", fileNotFoundException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int obtainSyllabus(StartupDocumentation startupDocumentation, String outputPath) throws LogicException {
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        int result =0;
        try {
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT syllabus FROM DocumentacionInicio WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, startupDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("syllabus");
                FileDownloader.transformBlobToFile(outputPath, blob);
                result = 1;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
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
    public int obtainStudentsList(StartupDocumentation startupDocumentation, String outputPath) throws LogicException {
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
    public int obtainMirrorStudentsList(StartupDocumentation startupDocumentation, String outputPath) throws LogicException {
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
}
