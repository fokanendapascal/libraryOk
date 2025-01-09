import java.sql.*;

public class DBConnection {
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/librarydb";

    public static void main(String[] args) {

        Connection connection ;
        Statement statement ;
        ResultSet resultSet ;

        try{
            connection = DriverManager.getConnection(DATABASE_URL, "fokanenpascal", "Pfn1306_l");
            statement = connection.createStatement();

            System.out.println("Insertion....");
            String sql = "INSERT INTO livres" + "VALUES( 1, 'Le Larousse médicale', 'Collectif', 'Dictionnaire', 50 )";
            statement.executeUpdate(sql);

            sql = "INSERT INTO livres" + "VALUES( 2, 'La cryptographie déchiffrée', 'Aumasson, Jean-Philippe', 'Argumentatif', 20)";
            statement.executeUpdate(sql);

            sql = "INSERT INTO livres" + "VALUES(3, 'L'intelligence artificielle en 5 minutes par jour', 'Ascoli, Stephane', 'Langages et Programmation', 20)";
            statement.executeUpdate(sql);

            System.out.println("Données insérées dans la table....");

            resultSet = statement.executeQuery("SELECT * FROM livres ");
            System.out.println(resultSet);

        }catch (Exception e){
            System.out.println(e);
        }

    }
}