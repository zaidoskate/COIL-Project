package logic.DAOs;

import logic.interfaces.UserManagerInterface;
import logic.domain.User;
import dataaccess.DatabaseConnection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import logic.LogicException;
import java.sql.Statement;

/**
 *
 * @author chuch
 */
public class UserDAO implements UserManagerInterface {
    private static final DatabaseConnection DATABASE_CONNECTION = new DatabaseConnection();
    
    /** Agrega un usuario a la base de datos
     *
     * @param user una instancia de la clase User con los datos correspondientes al nuevo usuario
     * @return un entero que indica la cantidad de filas que se han insertado en la tabla de la base de datos
     * @throws LogicException cuando no existe conexión con la base de datos
     */
    @Override
    public int addUser(User user)  throws LogicException {
        int result = 0;
        String query = "INSERT INTO Usuario(nombre, apellido,correo) VALUES (?, ?, ?)";
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while(resultSet.next()) {
                result = resultSet.getInt(1);
            }
            
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return result;
    }
    
    /** Recupera los datos de un usuario a partir de su propio Id
     *
     * @param userId el id del usuario que se busca recuperar
     * @return Una instancia de la clase User con los datos del usuario que se recuperó de la base de datos
     * @throws LogicException cuando no existe conexión con la base de datos
     */
    @Override
    public User getUserById(int userId) throws LogicException  {
        String query = "SELECT * FROM Usuario WHERE idUsuario = ?";
        User userResult = new User();
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                userResult.setIdUser(result.getInt("idUsuario"));
                userResult.setName(result.getString("nombre"));
                userResult.setLastName(result.getString("apellido"));
                userResult.setEmail(result.getString("correo"));
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return userResult;
    }

    /** Llama al procedimiento almacenado en la base de datos para recuperar el tipo de usuario a partir del Id del usuario
     *
     * @param userId es el id del usuario que se desea saber el tipo
     * @return un String con el tipo de Usuario (ProfesorUV, ProfesorExterno, Coordinador, Administrador)
     * @throws LogicException cuando no existe conexión con la base de datos
     */
    @Override
    public String getUserTypeById(int userId) throws LogicException {
        String query = "CALL encontrarTipoUsuario( ? )";
        String typeUser = null;
        try{
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                typeUser = result.getString("tipo");
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexion intentelo de nuevo mas tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return typeUser;
        
    }
    
    /** Verificar si existe un usuario registrado en la base de datos con la misma dirección de correo electrónico
     *
     * @param email es la dirección de correo electrónico por la que se va a filtrar la búsqueda
     * @return un booleano que es true si ya existe un usuario con ese correo y false si no
     * @throws LogicException cuando no existe conexión con la base de datos
     */
    @Override
    public boolean checkEmailRegistered(String email) throws LogicException{
        String query = "SELECT COUNT(*) as cuentas FROM Usuario WHERE correo = ?";
        boolean emailExists = false;
        try {
            Connection connection = this.DATABASE_CONNECTION.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet accounts = statement.executeQuery();
            if(accounts.next()) {
                if(accounts.getInt("cuentas") >= 1) {
                    emailExists = true;
                }
            }
        } catch(SQLException sqlException) {
            throw new LogicException("No hay conexión a la base de datos, inténtelo de nuevo más tarde", sqlException);
        } finally {
            DATABASE_CONNECTION.closeConnection();
        }
        return emailExists;
    }
}
