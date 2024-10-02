package naveena;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DBConnection {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/online_reservation";
    private static final String USERNAME = "postgres"; 
    private static final String PASSWORD = "5555";
    
    public static Connection getConnection() throws SQLException
    {
        Connection conn = null;
        try {
        	Class.forName("org.postgresql.Driver");
        	conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected successfully");
        } catch (SQLException e) {
                System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return conn;
    }
    
    
}