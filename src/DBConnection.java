import java.sql.*;

public class DBConnection {
    private static Connection connection;

    // Méthode pour initialiser la connexion
    public void initialize(String database_url, String username, String password) throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(database_url, username, password);
            System.out.println("connexion réussie!");
        }
    }

    // Méthode pour obtenir la connexion existante
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            throw new SQLException("No database connection.");
        }
        return connection;
    }

    // Méthode pour fermer la connexion
    public static void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("connection close !");
        }
    }
}




