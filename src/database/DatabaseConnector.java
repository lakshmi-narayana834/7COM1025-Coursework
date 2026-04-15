package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    
    private static final String DB_PATH = "F:/Writing/Chaitanya UK/Winter 2026/7COM1025 Coursework Briefing/Furzefield Leisure Centre/db/Furzefield_Leisure_Centre.db";
    private static final String JDBC_URL = "jdbc:sqlite:" + DB_PATH;

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
