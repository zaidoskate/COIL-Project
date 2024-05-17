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

public class DepartmentDAO implements DepartmentManagerInterface{
    private static final DatabaseConnection databaseConnection = new DatabaseConnection();
    
    @Override
    public ArrayList<String> getRegionsNames() throws LogicException {
        String query = "SELECT region from facultad group by region";
        ArrayList<String> regionsNames = new ArrayList();
        try{
            Connection connection = this.databaseConnection.getConnection();
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
            databaseConnection.closeConnection();
        }
        return regionsNames;
    }
    
    @Override
    public ArrayList<Department> getDepartmentsByRegion(String region) throws LogicException {
        String query = "SELECT * FROM facultad WHERE region = ?";
        ArrayList<Department> departments = new ArrayList();
        try{
            Connection connection = this.databaseConnection.getConnection();
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
            databaseConnection.closeConnection();
        }
        return departments;
    }
}
