import java.sql.SQLException;

public static void main(String[] args) throws SQLException {

    try{
        // Initialisez la connexion à la base de données
        DBConnection dbConnection = new DBConnection();
        dbConnection.initialize("jdbc:postgresql://localhost:5432/librarydb","fokanendapascal","Pfn1306_l");

        FichierMenus.menuChoice();
        // Fermez la connexion à la fin
        DBConnection.closeConnection();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

}


