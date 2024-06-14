package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.University;
import logic.interfaces.UniversityManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.LogicException;

/**
 *
 * @author chuch
 */
public class UniversityDAO implements UniversityManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     * Insertar una nueva universidad en la base de datos.
     * @param university información de la universidad (nombre y país).
     * @return entero que retorna las rows afectadas en la query, es exitoso si devuelve 1.
     * @throws LogicException cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public int insertUniversity(University university) throws LogicException {
        int result = 0;
        String query = "INSERT INTO Universidad (nombre, pais) VALUES (?, ?)";
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, university.getName());
            statement.setString(2, university.getCountry());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde",sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     * Obtener todas las universidades.
     * @return ArrayList de universidades.
     * @throws LogicException cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public ArrayList<University> getUniversities() throws LogicException {
        String query = "SELECT * FROM Universidad";
        ArrayList <University> universitiesResult = new ArrayList();
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                University university = new University();
                university.setCountry(result.getString("pais"));
                university.setUniversityId(result.getInt("idUniversidad"));
                university.setName(result.getString("nombre"));
                universitiesResult.add(university);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return universitiesResult;
    }
    
    /**
     * Consultar si el nombre de la universidad ya está registrado en la base de datos.
     * @param universityName nombre de la universidad a consultar.
     * @return boolean que devuelve true si ya existe la universidad.
     * @throws LogicException cuando hay un problema de conexión con la base de datos.
     */
    @Override
    public boolean checkUniversityRegistered(String universityName) throws LogicException {
        String query = "SELECT COUNT(*) as universidades FROM Universidad WHERE nombre = ?";
        boolean universityExists = false;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, universityName);
            ResultSet universities = statement.executeQuery();
            if(universities.next()) {
                if(universities.getInt("universidades") >= 1) {
                    universityExists = true;
                } 
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return universityExists;
    }

}
