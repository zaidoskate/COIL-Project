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
     *
     * @param university
     * @return
     * @throws LogicException
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
     *
     * @return
     * @throws LogicException
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

}
