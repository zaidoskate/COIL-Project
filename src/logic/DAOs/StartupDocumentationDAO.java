package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
 * @author zaido
 */
public class StartupDocumentationDAO implements StartupDocumentationManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /** Inserta un registro que registra el id de una colaboración para poder subir los demás documentos
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el id de la colaboración que se va a empezar
     * @return un entero que indica el total de filas agregadas (0 o 1)
     * @throws LogicException Cuando no existe conexión con la base de datos
     */
    @Override
    public int addStartupDocumentation(StartupDocumentation startupDocumentation) throws LogicException{
        int result = 0;
        String query = "INSERT INTO documentacioninicio(Colaboracion_idColaboracion) VALUES (?)";
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, startupDocumentation.getIdColaboration());
            result = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /** Carga en la base de datos el syllabus correspondiente a una colaboración
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el id de la colaboración y la ruta al archivo del syllabus
     * @return un entero que indica la cantidad de filas que se han registrado en la tabla (0 o 1)
     * @throws LogicException cuando no existe conexión con la base de datos
     */
    @Override
    public int uploadSyllabus(StartupDocumentation startupDocumentation) throws LogicException {
        int result = 0;
        String query = "UPDATE DocumentacionInicio SET Syllabus = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
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
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /** Carga en la base de datos la lista de estudiantes correspondiente a una colaboración
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el id de la colaboración y la ruta al archivo de la lista de estudiantes
     * @return un entero que indica la cantidad de filas agregadas en la tabla (0 o 1)
     * @throws LogicException cuando no existe conexión con la base de datos
     */
    @Override
    public int uploadStudentsList(StartupDocumentation startupDocumentation) throws LogicException {
        int result = 0;
        String query = "UPDATE DocumentacionInicio SET listaEstudiantado = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
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
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /** Carga en la base de datos la lista de estudiantes de la clase espejo correspondiente a una colaboración
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el id de la colaboración y la ruta al archivo de la lista de estudiantes de la clase espejo
     * @return un entero que indica la cantidad de filas agregadas en la tabla (0 o 1)
     * @throws LogicException cuando no existe conexión con la base de datos
     */
    @Override
    public int uploadMirrorStudentsList(StartupDocumentation startupDocumentation) throws LogicException {
        int result = 0;
        String query = "UPDATE DocumentacionInicio SET listaEstudiantadoEspejo = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
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
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /** Descarga el syllabus de la base de datos y lo guarda en una ruta especificada en el dispositivo
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el id de la colaboración de la cual se quiere descargar el syllabus
     * @param outputPath la ruta específica en el dispositivo en la cual se va a guardar el archivo
     * @return un entero que indica 1 si se pudo descargar con éxito el syllabus y 0 si no fue así
     * @throws LogicException cuando no existe conexión con la base de datos
     */
    @Override
    public int obtainSyllabus(StartupDocumentation startupDocumentation, String outputPath) throws LogicException {
        int result =0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT syllabus FROM DocumentacionInicio WHERE Colaboracion_idColaboracion = ?");
            statement.setInt(1, startupDocumentation.getIdColaboration());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("syllabus");
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
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /** Descarga la lista de estudiantes de una colaboración de la base de datos y lo guarda en una ruta especificada en el dispositivo
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el id de la colaboración de la cual se quiere descargar la lista de estudiantes
     * @param outputPath la ruta específica en el dispositivo en la cual se va a guardar el archivo
     * @return un entero que indica 1 si se pudo descargar con éxito la lista de estudiantes y 0 si no fue así
     * @throws LogicException cuando no existe conexión con la base de datos
     */
    @Override
    public int obtainStudentsList(StartupDocumentation startupDocumentation, String outputPath) throws LogicException {
        int result =0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT listaEstudiantado FROM DocumentacionInicio WHERE Colaboracion_idColaboracion = ?");
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
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     *
     * @param startupDocumentation
     * @param outputPath
     * @return
     * @throws LogicException
     */
    @Override
    public int obtainMirrorStudentsList(StartupDocumentation startupDocumentation, String outputPath) throws LogicException {
        int result =0;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT listaEstudiantadoEspejo FROM DocumentacionInicio WHERE Colaboracion_idColaboracion = ?");
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
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     *
     * @param fileType
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    @Override
    public boolean hasFileUploaded(String fileType, int idCollaboration) throws LogicException {
        boolean hasFileUploaded = false;
        String query = "SELECT " + fileType + " AS file FROM documentacionInicio WHERE colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
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
            DATABASE_CONNECTION.closeConnection();
        }
        return hasFileUploaded;
    }

    /**
     *
     * @param fileType
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    @Override
    public int deleteUploadedFile(String fileType, int idCollaboration) throws LogicException {
        int deleted = 0;
        String query = "UPDATE documentacionInicio SET " + fileType + " = NULL WHERE colaboracion_idcolaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            deleted = statement.executeUpdate();
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return deleted;
    }

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    @Override
    public boolean isCollaborationRegistrated(int idCollaboration) throws LogicException {
        boolean registrated = false;
        String query = "SELECT COUNT(*) AS count FROM documentacionInicio WHERE colaboracion_idcolaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idCollaboration);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                int count = resultSet.getInt("count");
                registrated = count > 0;
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return registrated;
    }
}
