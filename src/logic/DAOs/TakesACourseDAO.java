/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.DAOs;

import dataaccess.DatabaseConnection;
import java.util.ArrayList;
import logic.domain.Course;
import logic.domain.TakesACourse;
import logic.domain.User;
import logic.interfaces.TakesACourseManagerInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class TakesACourseDAO implements TakesACourseManagerInterface{
    private final DatabaseConnection databaseConnection;
    
    public TakesACourseDAO() {
        this.databaseConnection = new DatabaseConnection();
    }
    
    @Override
    public int insertTakesACourse(TakesACourse takesACourse) {
        int result = 0;
        String query = "INSERT INTO Cursa VALUES (?, ?, ?)";
        Connection connection;
        PreparedStatement statement;
        try{
            connection = this.databaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, takesACourse.getIdCourse());
            statement.setInt(2, takesACourse.getIdUser());
            statement.setString(3, takesACourse.getAccreditationStatus());
            result = statement.executeUpdate();
        } catch (SQLException sqlException) {
            return result;
        } finally {
            databaseConnection.closeConnection();
        }
        return result;
    }

    @Override
    public ArrayList<User> getUsersByCourse(Course course) {
        ArrayList<User> usersOfCourse = new ArrayList<>();
        String query = "SELECT u.idUsuario, u.nombre, u.apellidoPaterno, u.apellidoMaterno, u.idioma, u.correo " +
                       "FROM Usuario u " +
                       "INNER JOIN Cursa c ON u.idUsuario = c.ProfesorUv_Profesor_Usuario_idUsuario " +
                       "WHERE c.CursoTaller_idCursoTaller = ?";
        try{
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, course.getCourseId());
            ResultSet usersResult = statement.executeQuery();
            while(usersResult.next()) {
                User user = new User();
                user.setIdUser(usersResult.getInt("idUsuario"));
                user.setName(usersResult.getString("nombre"));
                user.setLastName(usersResult.getString("apellidoPaterno"));
                user.setSurname(usersResult.getString("apellidoMaterno"));
                user.setLanguage(usersResult.getString("idioma"));
                user.setEmail(usersResult.getString("correo"));
                usersOfCourse.add(user);
            }
        } catch (SQLException sqlException) {
            return null;
        } finally {
            databaseConnection.closeConnection();
        }
        return usersOfCourse;
    }

    @Override
    public ArrayList<Course> getCourseByUser(User user) {
        ArrayList<Course> coursesOfUser = new ArrayList<>();
        String query = "SELECT c.idCursoTaller, c.nombreTaller, c.fechaInicio, c.fechaCierre, c.modalidad " +
                       "FROM coilProject.CursoTaller c " +
                       "INNER JOIN coilProject.Cursa cr ON c.idCursoTaller = cr.CursoTaller_idCursoTaller " +
                       "WHERE cr.ProfesorUv_Profesor_Usuario_idUsuario = ?";
        try {
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, user.getIdUser());
            ResultSet coursesResult = statement.executeQuery();
            while (coursesResult.next()) {
                Course course = new Course();
                course.setCourseId(coursesResult.getInt("idCursoTaller"));
                course.setCourseName(coursesResult.getString("nombreTaller"));
                course.setBeginningDate(coursesResult.getString("fechaInicio"));
                course.setEndingDate(coursesResult.getString("fechaCierre"));
                course.setModality(coursesResult.getString("modalidad"));
                coursesOfUser.add(course);
            }
        } catch (SQLException sqlException) {
            return null;
        }
        return coursesOfUser;
    }
    
}
