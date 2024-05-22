package dataaccess;

import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import java.sql.Connection;

public class DatabaseConnectionTest {
    
    public DatabaseConnectionTest() {
    }

    @Test
    public void testConnectionSuccess() throws SQLException {
        DatabaseConnection instance = new DatabaseConnection();
        Connection result = instance.getConnection();
        assertNotNull(result);
    }

}
