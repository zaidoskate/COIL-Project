package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Department;
import logic.interfaces.DepartmentManagerInterface;

/**
 *
 * @author chuch
 */
public class DepartmentDAO implements DepartmentManagerInterface{
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     *
     * @return
     * @throws LogicException
     */
    @Override
    public ArrayList<String> getRegionsNames() throws LogicException {
        String query = "SELECT region from facultad group by region";
        ArrayList<String> regionsNames = new ArrayList();
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                String regionName;
                regionName = result.getString("region");
                regionsNames.add(regionName);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return regionsNames;
    }
    
    /**
     *
     * @param region
     * @return
     * @throws LogicException
     */
    @Override
    public ArrayList<Department> getDepartmentsByRegion(String region) throws LogicException {
        String query = "SELECT * FROM facultad WHERE region = ?";
        ArrayList<Department> departments = new ArrayList();
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, region);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Department department = new Department();
                department.setIdAcademicArea(result.getInt("idAreaAcademica"));
                department.setIdDepartment(result.getString("idFacultad"));
                department.setName(result.getString("nombre"));
                department.setRegion(region);
                
                departments.add(department);
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return departments;
    }
}
