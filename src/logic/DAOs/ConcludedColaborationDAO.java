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
import java.util.ArrayList;
import logic.FileDownloader;
import logic.LogicException;
import logic.domain.ConcludedCollaboration;
import logic.interfaces.ConcludedCollaborationManagerInterface;

public class ConcludedColaborationDAO implements ConcludedCollaborationManagerInterface {
    private final DatabaseConnection databaseConnection;
    
    public ConcludedColaborationDAO(){
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int addConcludedCollaboration(ConcludedCollaboration concludedCollaboration) throws LogicException{
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
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int updateVisibility(ConcludedCollaboration concludedCollaboration) throws LogicException{
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
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int updateRating(ConcludedCollaboration concludedCollaboration) throws LogicException{
        int result = 0;
        String query = "UPDATE colaboracionconcluida SET calificacion = ? WHERE Colaboracion_idColaboracion = ?";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, concludedCollaboration.getRating());
            statement.setInt(2, concludedCollaboration.getIdColaboration());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }
    
    @Override
    public int uploadCertificates(ConcludedCollaboration concludedCollaboration) throws LogicException{
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
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new LogicException("No existe tal archivo en la ruta especificada", fileNotFoundException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public int obtainCertificates(ConcludedCollaboration concludedCollaboration, String outputPath) throws LogicException{
        Connection connection;
        PreparedStatement statement = null;
        FileOutputStream fileOutputStream = null;
        int result =0;
        try {
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement("SELECT constancias FROM colaboracionconcluida WHERE Colaboracion_idColaboracion = ?");
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
        } catch (IOException ioException){
            throw new LogicException("Error de entrada y salida de archivos", ioException);
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public ArrayList<ConcludedCollaboration> getConcludedCollaborations() throws LogicException{
        String query = "select Colaboracion_idColaboracion, Usuario_idUsuario from colaboracionconcluida";
        Connection connection;
        PreparedStatement statement;
        ResultSet result;
        ArrayList <ConcludedCollaboration> concludedCollaborationsResult = new ArrayList();
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();
            while(result.next()) {
                ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
                concludedCollaboration.setIdColaboration(result.getInt("Colaboracion_idColaboracion"));
                concludedCollaboration.setIdUser(result.getInt("Usuario_idUsuario"));
                concludedCollaborationsResult.add(concludedCollaboration);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            databaseConnection.closeConnection();
        }
        return concludedCollaborationsResult;
    }
}
