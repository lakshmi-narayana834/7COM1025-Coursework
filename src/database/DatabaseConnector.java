package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    
    
    private static final String JDBC_URL = "jdbc:sqlite:db/Furzefield_Leisure_Centre.db";

    public Connection connect() {
        
        Connection con = null;
        
        try {
          
            String dbURL = JDBC_URL;
            
            con = DriverManager.getConnection(dbURL);
            
            return con;
        } catch (SQLException e) {
            System.out.println("DatabaseConnection: "+e.getMessage());
     
            return null;
        }
    }


}
