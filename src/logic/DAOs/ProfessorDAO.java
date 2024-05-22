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
     *
     * @param professor
     * @return
     * @throws LogicException
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
     *
     * @param idUser
     * @return
     * @throws LogicException
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
