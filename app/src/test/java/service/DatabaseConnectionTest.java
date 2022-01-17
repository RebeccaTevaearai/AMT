package service;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    //Le test ne passe pas lorsque la DB n'est pas disponible
    @Test
    void getConnection() {
        Connection conn = DatabaseConnection.getConnection();

        assertNotNull(conn);
    }
}