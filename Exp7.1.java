import java.sql.*;

public class FetchEmployeeData {
    public static void main(String[] args) {
        String url =  "jdbc:mysql://localhost:3306/testdb";   
        String user = "root";              
        String password = "password"; 

        String query = "SELECT EmpID, Name, Salary FROM Employee";

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            // Create statement and execute query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Display results
            System.out.println("\nEmployee Records:");
            System.out.println("--------------------------------------");
            System.out.printf("%-10s %-20s %-10s%n", "EmpID", "Name", "Salary");
            System.out.println("--------------------------------------");

            while (rs.next()) {
                int empID = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                System.out.printf("%-10d %-20s %-10.2f%n", empID, name, salary);
            }

            // Close resources
            rs.close();
            stmt.close();
            con.close();
            System.out.println("\nConnection closed.");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}


