package jdbc_connection;

import java.sql.*;


public class JDBCDemo {
    public static void main(String[] args) {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/StudentsDB"; 
            String username = "root"; 
            String password = "A12345678."; 
            
           
            Class.forName(driver);
            System.out.println("MySQL JDBC Driver Registered!");

            
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Established Connection");
            
            conn.close();
            
            System.out.println("Connection Closed");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
