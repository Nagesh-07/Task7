import java.sql.*;
import java.util.Scanner;

public class DBCRUDApp {
    // Change URL, USER, PASSWORD according to your DB
    private static final String URL = "jdbc:mysql://localhost:3306/bank_db"; // for PostgreSQL: jdbc:postgresql://localhost:5432/bank_db
    private static final String USER = "root";      // PostgreSQL example: "postgres"
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            if (conn != null) {
                System.out.println("✅ Connected to Database Successfully!");
            }

            int choice;
            do {
                System.out.println("\n=== CRUD Operations Menu ===");
                System.out.println("1. Add Account");
                System.out.println("2. View All Accounts");
                System.out.println("3. Update Account Balance");
                System.out.println("4. Delete Account");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> addAccount(conn, sc);
                    case 2 -> viewAccounts(conn);
                    case 3 -> updateAccount(conn, sc);
                    case 4 -> deleteAccount(conn, sc);
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            } while (choice != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE
    private static void addAccount(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        String query = "INSERT INTO accounts(name, balance) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setDouble(2, balance);
            int rows = ps.executeUpdate();
            System.out.println(rows + " account(s) added successfully.");
        }
    }

    // READ
    private static void viewAccounts(Connection conn) throws SQLException {
        String query = "SELECT * FROM accounts";
        try (PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- Accounts List ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Balance: " + rs.getDouble("balance"));
            }
        }
    }

    // UPDATE
    private static void updateAccount(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Account ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter New Balance: ");
        double balance = sc.nextDouble();

        String query = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setDouble(1, balance);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows + " account(s) updated successfully.");
        }
    }

    // DELETE
    private static void deleteAccount(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Account ID to delete: ");
        int id = sc.nextInt();

        String query = "DELETE FROM accounts WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows + " account(s) deleted successfully.");
        }
    }
}
