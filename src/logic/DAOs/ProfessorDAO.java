package logic.DAOs;

import dataaccess.DatabaseConnection;
import logic.domain.Professor;
import logic.interfaces.ProfessorManagerInterface;
import logic.LogicException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author chuch
 */
public class ProfessorDAO implements ProfessorManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /**
     * Insertar un nuevo profesor en la base de datos.
     * @param professor id usuario del profesor
     * @return entero con las rows afectadas en la query, si retorna 1 fue exitoso.
     * @throws LogicException cuando hay problemas de conexión con la base de datos.
     */
    @Override
    public int insertProfessor(Professor professor) throws LogicException {
        int result = 0;
        String query = "INSERT INTO Profesor VALUES (?)";
        try{
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, professor.getIdUser());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /**
     * Obtener la universidad de un profesor, si el profesor es UV le asigna Universidad Veracruzana
     * @param idUser id usuario para consultar.
     * @return ArrayList de String que contiene el nombre de la universidad y el pais al que pertenece.
     * @throws LogicException cuando se presenta un problema de conexión con la base de datos.
     */
    @Override
    public ArrayList<String> getUniversityFromAProfessor(int idUser) throws LogicException {
        ArrayList<String> universityInformation = new ArrayList<>();
        String queryUv = "SELECT COUNT(*) FROM profesorUv WHERE Profesor_Usuario_idUsuario = ?";
        String queryExternal = "SELECT Universidad.nombre, Universidad.pais FROM profesorExterno "
                + "JOIN universidad ON profesorExterno.Universidad_idUniversidad"
                + "= universidad.idUniversidad WHERE profesorExterno.Profesor_Usuario_idUsuario = ?";
        try {
            Connection connection = DATABASE_CONNECTION.getConnection();
            PreparedStatement statementUv = connection.prepareStatement(queryUv);
            statementUv.setInt(1, idUser);
            ResultSet resultUv = statementUv.executeQuery();
            if (resultUv.next() && resultUv.getInt(1) > 0) {
                universityInformation.add("Universidad Veracruzana");
                UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
                universityInformation.add(uvProfessorDAO.getDepartmentNameBelonging(idUser));
            } else {
                PreparedStatement statementExternal = connection.prepareStatement(queryExternal);
                statementExternal.setInt(1, idUser);
                ResultSet resultExternal = statementExternal.executeQuery();
                if (resultExternal.next()) {
                    universityInformation.add(resultExternal.getString("nombre"));
                    universityInformation.add(resultExternal.getString("pais"));
                }
            }
        } catch (SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return universityInformation;
    }

}
