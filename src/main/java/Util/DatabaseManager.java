package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance;
    private static final String URI = "jdbc:mysql://localhost:3306/recipe_jdbc";
    private static final String USER = "root";
    private static final String PASSWORD = "Root";

    private DatabaseManager (){

    }

    public static DatabaseManager getDatabaseManager (){
        if(instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URI,USER,PASSWORD);
    }
}
