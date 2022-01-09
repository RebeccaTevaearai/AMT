package service;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class that manage database connexion
 */
public class DatabaseConnection {

    private static Connection connection;
    private static DatabaseConnection instance;
    private static final String URL = "jdbc:mysql://localhost:3306/pecheur?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PWD = "12345";

    /**
     * Constructor
     */
    private DatabaseConnection() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(URL
                    ,USER,PWD);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Get the database connexion
     * @return The connexion
     */
    public static Connection getConnection() {
        if(instance == null)
        {
            instance = new DatabaseConnection();
        }

        return connection;
    }

    /**
     * Execute specified query
     * @param query Query
     * @param params List of params
     * @return Query result
     * @throws SQLException
     */
    public static ResultSet doQuery(String query, ArrayList<String> params) throws SQLException {
        PreparedStatement st = getConnection().prepareStatement(query);

        int nbParam = 1;
        for(String param : params)
        {
            st.setString(nbParam, param);
            nbParam++;
        }

      return st.executeQuery();
    }
}
