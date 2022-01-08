package service;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test DatabaseConnection class
 */
public class DatabaseConnectionTest {

    //Le test ne passe pas lorsque la DB n'est pas disponible

    /**
     * test if the getConnextion() method return not null data.
     * the database must be available.
     */
    @Test
    void getConnection() {
        Connection conn = DatabaseConnection.getConnection();

        assertNotNull(conn);
    }
}