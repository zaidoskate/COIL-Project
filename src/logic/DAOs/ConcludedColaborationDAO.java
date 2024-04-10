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
import logic.domain.ConcludedCollaboration;
import logic.interfaces.ConcludedCollaborationManagerInterface;

public class ConcludedColaborationDAO implements ConcludedCollaborationManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public ConcludedColaborationDAO(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }
    
    @Override
    public int addConcludedCollaboration(ConcludedCollaboration concludedCollaboration) {
        int result = 0;
        String query = "INSERT INTO colaboracionconcluida(Colaboracion_idColaboracion, Usuario_idUsuario, numeroEstudiantes) VALUES (?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, concludedCollaboration.getIdColaboration());
            statement.setInt(2, concludedCollaboration.getIdUser());
            statement.setInt(3, concludedCollaboration.getNumberStudents());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int updateVisibility(ConcludedCollaboration concludedCollaboration) {
        int result = 0;
        String query = "UPDATE colaboracionconcluida SET visibilidad = ? WHERE Colaboracion_idColaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, concludedCollaboration.getVisibility());
            statement.setInt(2, concludedCollaboration.getIdColaboration());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int updateRating(ConcludedCollaboration concludedCollaboration) {
        int result = 0;
        String query = "UPDATE colaboracionconcluida SET reting = ? WHERE Colaboracion_idColaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setDouble(1, concludedCollaboration.getRating());
            statement.setInt(2, concludedCollaboration.getIdColaboration());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            result = -1;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int uploadCertificates(ConcludedCollaboration concludedCollaboration) {
        Connection connection;
        int result = 0;
        String query = "UPDATE colaboracionconcluida SET constancias = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            connection = this.databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File certificatesZip = new File(concludedCollaboration.getCertificatesPath());
            FileInputStream fileInputStream = new FileInputStream(certificatesZip);
            statement.setBinaryStream(1, fileInputStream, (int) certificatesZip.length());
            statement.setInt(2, concludedCollaboration.getIdColaboration());
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
    public int obtainCertificates(ConcludedCollaboration concludedCollaboration, String outputPath) {
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        int result =0;
        try {
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT constancias FROM colaboracionconcluida WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, concludedCollaboration.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("constancias");
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
