package dataaccess;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class DatabaseConnection {
    private Connection databaseConnection;
    private final String DATABASE_URL;
    private final String DATABASE_USER;
    private final String DATABASE_PASSWORD;
    private static final Properties properties = new Properties();
    private static final Logger log = Logger.getLogger(DatabaseConnection.class);

    public DatabaseConnection() {
        if(loadPropertyFile()) {
            DATABASE_URL = properties.getProperty("db.url");
            DATABASE_USER = properties.getProperty("db.user");
            DATABASE_PASSWORD = properties.getProperty("db.password");
        } else {
            DATABASE_URL = null;
            DATABASE_USER = null;
            DATABASE_PASSWORD = null;
        }
    }
    
    public Connection getConnection() throws SQLException {
        connect();
        return databaseConnection;
    }
    
    private void connect() throws SQLException {
        databaseConnection = DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
    }
    
    private boolean loadPropertyFile() {
        try {
            InputStream input = new FileInputStream("dbConfig/dbConfig.properties");
            properties.load(input);
        } catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    public void closeConnection() {
        if(databaseConnection != null) {
            try {
                if(!databaseConnection.isClosed()) {
                    databaseConnection.close();
                }
            } catch (SQLException exception) {
                log.fatal("No se puede cerrar la conexion con la BD.", exception);
            }
        }
    }
}
