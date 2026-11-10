
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBonnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/student_management";
        String username = "root";
        String password = "davi";

        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            System.out.println("Connected to MySQL successfully!");
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}
