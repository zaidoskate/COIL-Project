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
 * Data Access Object (DAO) para gestionar las operaciones relacionadas con la documentación de inicio en la base de datos.
 * Implementa la interfaz StartupDocumentationManagerInterface.
 * 
 * @author zaido
 */
public class StartupDocumentationDAO implements StartupDocumentationManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Inserta un registro con el ID de una colaboración.
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el ID de la colaboración que se va a empezar.
     * @return un entero que indica el total de filas agregadas (0 o 1), si es 1 fue exitoso.
     * @throws LogicException cuando no existe conexión con la base de datos o ocurre un error de SQL.
     */
    @Override
    public int addStartupDocumentation(StartupDocumentation startupDocumentation) throws LogicException {
        int result = 0;
        String query = "INSERT INTO documentacioninicio(Colaboracion_idColaboracion) VALUES (?)";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, startupDocumentation.getIdColaboration());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Carga en la base de datos el syllabus correspondiente a una colaboración como un longblob.
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el ID de la colaboración y la ruta al archivo del syllabus.
     * @return un entero que indica la cantidad de filas que se han registrado en la tabla (0 o 1). Si fue 1 fue exitoso.
     * @throws LogicException cuando no existe conexión con la base de datos o ocurre un error de SQL o de archivo.
     */
    @Override
    public int uploadSyllabus(StartupDocumentation startupDocumentation) throws LogicException {
        int result = 0;
        String query = "UPDATE DocumentacionInicio SET Syllabus = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File syllabusFile = new File(startupDocumentation.getSyllabusPath());
            FileInputStream fileInputStream = new FileInputStream(syllabusFile);
            statement.setBinaryStream(1, fileInputStream, (int) syllabusFile.length());
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

    /**
     * Carga en la base de datos la lista de estudiantes correspondiente a una colaboración.
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el ID de la colaboración y la ruta al archivo de la lista de estudiantes.
     * @return un entero que indica la cantidad de filas agregadas en la tabla (0 o 1). Si retornó 1 fue exitoso.
     * @throws LogicException cuando no existe conexión con la base de datos o ocurre un error de SQL o de archivo.
     */
    @Override
    public int uploadStudentsList(StartupDocumentation startupDocumentation) throws LogicException {
        int result = 0;
        String query = "UPDATE DocumentacionInicio SET listaEstudiantado = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File studentsListFile = new File(startupDocumentation.getStudentsListPath());
            FileInputStream fileInputStream = new FileInputStream(studentsListFile);
            statement.setBinaryStream(1, fileInputStream, (int) studentsListFile.length());
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

    /**
     * Carga en la base de datos la lista de estudiantes de la clase espejo correspondiente a una colaboración.
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el ID de la colaboración y la ruta al archivo de la lista de estudiantes de la clase espejo.
     * @return un entero que indica la cantidad de filas agregadas en la tabla (0 o 1). Retorna 1 si fue exitoso.
     * @throws LogicException cuando no existe conexión con la base de datos o ocurre un error de SQL o de archivo.
     */
    @Override
    public int uploadMirrorStudentsList(StartupDocumentation startupDocumentation) throws LogicException {
        int result = 0;
        String query = "UPDATE DocumentacionInicio SET listaEstudiantadoEspejo = ? WHERE Colaboracion_idColaboracion = ?";
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            File mirrorStudentsListFile = new File(startupDocumentation.getMirrorClassStudentsListPath());
            FileInputStream fileInputStream = new FileInputStream(mirrorStudentsListFile);
            statement.setBinaryStream(1, fileInputStream, (int) mirrorStudentsListFile.length());
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

    /**
     * Descarga el syllabus de la base de datos y lo guarda en una ruta especificada en el dispositivo.
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el ID de la colaboración de la cual se quiere descargar el syllabus.
     * @param outputPath la ruta absoluta en el dispositivo en la cual se va a guardar el archivo.
     * @return un entero que indica 1 si se pudo descargar con éxito el syllabus y 0 si no fue así.
     * @throws LogicException cuando no existe conexión con la base de datos o ocurre un error de SQL o de archivo.
     */
    @Override
    public int obtainSyllabus(StartupDocumentation startupDocumentation, String outputPath) throws LogicException {
        int result = 0;
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
        } catch (IOException ioException) {
            throw new LogicException("Error de entrada y salida de archivos", ioException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Descarga la lista de estudiantes de una colaboración de la base de datos y la guarda en una ruta especificada en el dispositivo.
     *
     * @param startupDocumentation una instancia de la clase StartupDocumentation que incluye el ID de la colaboración de la cual se quiere descargar la lista de estudiantes.
     * @param outputPath la ruta absoluta en el dispositivo en la cual se va a guardar el archivo.
     * @return un entero que indica 1 si se pudo descargar con éxito la lista de estudiantes y 0 si no fue así.
     * @throws LogicException cuando no existe conexión con la base de datos o ocurre un error de SQL o de archivo.
     */
    @Override
    public int obtainStudentsList(StartupDocumentation startupDocumentation, String outputPath) throws LogicException {
        int result = 0;
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
        } catch (IOException ioException) {
            throw new LogicException("Error de entrada y salida de archivos", ioException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Descarga el archivo cargado en la base de datos como Blob para la lista de estudiantes espejo de una determinada colaboración.
     *
     * @param startupDocumentation instancia de StartupDocumentation que contiene el ID de la colaboración de la que se descargará la lista de estudiantes espejo.
     * @param outputPath ruta absoluta del dispositivo donde se seleccionó la descarga.
     * @return entero que retorna 1 si la descarga fue exitosa y 0 si no lo fue.
     * @throws LogicException cuando hay un problema de conexión a la base de datos o ocurre un error de SQL o de archivo.
     */
    @Override
    public int obtainMirrorStudentsList(StartupDocumentation startupDocumentation, String outputPath) throws LogicException {
        int result = 0;
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
        } catch (IOException ioException) {
            throw new LogicException("Error de entrada y salida de archivos", ioException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }

    /**
     * Consulta si uno de los tipos de archivo de documentación inicio tiene un archivo Blob cargado en lugar de NULL.
     *
     * @param fileType tipo de archivo que se desea consultar (puede ser Syllabus, lista de estudiantes o lista de estudiantes espejo).
     * @param idCollaboration ID de la colaboración relacionada a la búsqueda que se desea realizar.
     * @return booleano que retorna true en caso de que ese tipo de archivo en ese ID colaboración tenga algo diferente de null, y false si no hay nada cargado (null).
     * @throws LogicException cuando hay un problema de conexión con la base de datos o ocurre un error de SQL.
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

    /**
     * Modifica el registro de un archivo Blob cargado de un tipo de archivo a null para eliminarlo en determinado ID de colaboración.
     *
     * @param fileType tipo de archivo que se desea eliminar (puede ser Syllabus, lista de estudiantes o lista de estudiantes espejo).
     * @param idCollaboration ID de la colaboración relacionado del que se desea eliminar el tipo de archivo en la base de datos.
     * @return entero que indica el número de filas afectadas durante el update en la base de datos. Retorna 1 si fue exitoso.
     * @throws LogicException cuando hay un problema de conexión con la base de datos o ocurre un error de SQL.
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
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return deleted;
    }

    /**
     * Consulta si un determinado ID de colaboración ya está registrado en la tabla de documentación de inicio.
     *
     * @param idCollaboration ID de la colaboración a verificar su existencia en la tabla DocumentaciónInicio.
     * @return boolean que retorna true si el ID de colaboración ya está registrado en la tabla DocumentaciónInicio.
     * @throws LogicException cuando hay un problema de conexión con la base de datos o ocurre un error de SQL.
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
            if (resultSet.next()) {
                registrated = resultSet.getInt("count") > 0;
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return registrated;
    }
}
