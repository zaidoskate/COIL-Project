package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.Department;
import logic.interfaces.DepartmentManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.LogicException;

/**
 * Objeto de acceso a datos para manejar las operaciones relacionadas con los departamentos (facultades) en la base de datos.
 * Implementa la interfaz DepartmentManagerInterface.
 * Proporciona métodos para obtener los nombres de las regiones y los departamentos por región.
 * 
 * @autor chuch
 */
public class DepartmentDAO implements DepartmentManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();

    /**
     * Obtener el nombre de las regiones a las que pertenecen las facultades.
     * 
     * @return ArrayList de nombres de regiones obtenidas.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public ArrayList<String> getRegionsNames() throws LogicException {
        String query = "SELECT region FROM facultad GROUP BY region";
        ArrayList<String> regionsNames = new ArrayList<>();
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String regionName = result.getString("region");
                regionsNames.add(regionName);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return regionsNames;
    }

    /**
     * Obtener facultades por región.
     * 
     * @param region la región a consultar.
     * @return ArrayList de objetos Department obtenidos.
     * @throws LogicException cuando hay un problema con la conexión de la base de datos.
     */
    @Override
    public ArrayList<Department> getDepartmentsByRegion(String region) throws LogicException {
        String query = "SELECT * FROM facultad WHERE region = ?";
        ArrayList<Department> departments = new ArrayList<>();
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, region);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Department department = new Department();
                department.setIdAcademicArea(result.getInt("idAreaAcademica"));
                department.setIdDepartment(result.getString("idFacultad"));
                department.setName(result.getString("nombre"));
                department.setRegion(region);
                departments.add(department);
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return departments;
    }
}
