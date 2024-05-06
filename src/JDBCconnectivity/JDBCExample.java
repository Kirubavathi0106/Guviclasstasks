package JDBCconnectivity;
import java.sql.*;

public class JDBCExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/employee"; // Update with your database URL

    // Database credentials
    static final String USER = "root"; // Update with your username
    static final String PASS = "Kiruba*0106"; // Update with your password

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS); // Establishing connection using USER and PASS

            // Create a statement
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            // Insert data into the employees table
            String sql = "INSERT INTO employees (empcode, empname, empage, esalary) VALUES " +
                    "(101, 'Jenny', 25, 10000), " +
                    "(102, 'Jacky', 30, 20000), " +
                    "(103, 'Joe', 20, 40000), " +
                    "(104, 'John', 40, 80000), " +
                    "(105, 'Shameer', 25, 90000) " +
                    "ON DUPLICATE KEY UPDATE empname=VALUES(empname), empage=VALUES(empage), esalary=VALUES(esalary)";

            // Execute the SQL statement
            int rowsInserted = stmt.executeUpdate(sql);

            // Print the number of rows inserted
            System.out.println(rowsInserted + " rows inserted.");

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Thank you!"); // Print a closing message
    }
}
