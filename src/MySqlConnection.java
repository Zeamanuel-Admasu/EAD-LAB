import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/mydatabase"; // Replace with your DB name
        String username = "root";
        String password = "Afrosenay123.";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connected to MySQL database successfully!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error connecting to MySQL database");
            e.printStackTrace();
        }
    }
}
