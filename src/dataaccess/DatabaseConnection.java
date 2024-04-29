/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class DatabaseConnection {
    private Connection databaseConnection;
    private final String DATABASE_URL = "jdbc:mysql://127.0.0.1/coilProject";
    private final String DATABASE_USER = "user1";
    private final String DATABASE_PASSWORD = "ZAMATL";
    private static final Logger log = Logger.getLogger(DatabaseConnection.class);
    
    public Connection getConnection() throws SQLException {
        connect();
        return databaseConnection;
    }
    
    private void connect() throws SQLException {
        databaseConnection = DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
    }
    
    public void closeConnection() {
        if(databaseConnection != null) {
            try {
                if(!databaseConnection.isClosed()) {
                    databaseConnection.close();
                }
            } catch (SQLException exception) {
                log.error(exception);
            }
        }
    }
}
