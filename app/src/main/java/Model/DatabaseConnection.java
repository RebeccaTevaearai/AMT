package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pecheur?serverTimezone=UTC"
                    ,"root","");
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if(instance == null)
        {
            instance = new DatabaseConnection();
        }

        return connection;
    }
}
