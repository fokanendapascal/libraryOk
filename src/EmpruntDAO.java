import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpruntDAO {

    // Récupérer la connexion
    static Connection connection;
    static {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Create a Statement
    static Statement statement;
    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //Menu général des emprunts et penalites
    static void menuEmpruntPenalite() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue à la gestion des prêts et des pénalités");
        System.out.println("0. retour au menu principal");
        System.out.println("1. liste des livres en prêt");
        System.out.println("2. Ajouter un emprunt");
        System.out.println("3. Retour au prêt");
        System.out.println("4. Liste et calcul des pénalités");
        System.out.println("5. Exit");
        System.out.println("Your choice : ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                FichierMenus.menuChoice();
                break;
            case 1:
                System.out.println("\n0. return to main menu");
                System.out.println("1. List Of Returns");
                System.out.println("2. list Of Current loan");
                System.out.println("Your Choice : ");
                int choice1 = scanner.nextInt();
                switch (choice1) {
                    case 0:
                        FichierMenus.menuChoice();
                        break;
                    case 1:
                        System.out.println("List of loan return\n :");
                        displayEmpruntRetourne();
                        break;
                    case 2:
                        System.out.println("List of active loan\n :");
                        displayEmpruntEncours();
                        FichierMenus.postUpdateNavigation();
                        break;
                    default:
                        break;
                }
                break;

            case 2:
                System.out.println("\n MENU Add Loan\n");
                System.out.println("Name of Borrower ");
                String nom = scanner.next();
                scanner.nextLine();
                System.out.println("First name of borrower ");
                String prenom = scanner.next();
                scanner.nextLine();
                System.out.println(" title of book ");
                String titre = scanner.nextLine();

                String updateQuery0 = RequeteContainer.ajouterEmprunt(nom, prenom, titre);
                int rowsUpdated0 = statement.executeUpdate(updateQuery0);
                if (rowsUpdated0 > 0) {
                    System.out.println("book added !");
                } else {
                    System.out.println("No book added.");
                }
                System.out.println("Request : " + updateQuery0);
                FichierMenus.postUpdateNavigation();
                break;

            case 3:
                System.out.println("\n MENU of Return of book loan ");
                System.out.println("liste of active loans... ");
                displayEmpruntEncours();
                System.out.println("\n list of id of loan return");
                System.out.println("Print : ");
                int choice2 = scanner.nextInt();
                System.out.println("confirm yes / no");
                String choice3 = scanner.next();
                if (choice3.equalsIgnoreCase("yes")) {
                    String updateQuery = RequeteContainer.retournerEmprunt(choice2);
                    int rowsUpdated1 = statement.executeUpdate(updateQuery);
                    if (rowsUpdated1 > 0) {
                        System.out.println("Modification Ok !");
                    } else {
                        System.out.println("No Modification.");
                    }
                    System.out.println("Request : " + updateQuery);
                    FichierMenus.postUpdateNavigation();
                } else {
                    FichierMenus.postUpdateNavigation();
                }
                break;

            case 4:
                System.out.println("\n calculate automatically penalty \n");
                RequeteContainer.calculPenalites();
                System.out.println("liste of penalties : ");
                displayPenalite();
                break;
            case 5:
                FichierMenus.end();
                System.exit(0);
                break;
            default:
                System.out.println("choix invalid choice... return to principal menu");
                FichierMenus.menuChoice();
        }
    }

    //méthode pour afficher les pénalités
    public static void displayPenalite() throws SQLException {

        // Exécuter une requête pour afficher les pénalités
        String display = RequeteContainer.afficherPenalite(); // Assurez-vous que cette méthode génère la bonne requête
        ResultSet resultSet = statement.executeQuery(display);

        // Afficher l'en-tête du tableau
        String format = "| %-18s | %-18s | %-40s | %-18s |\n";
        System.out.format("+--------------------+--------------------+------------------------------------------ +--------------------+\n");
        System.out.format("| NomEmprunteur | PrenomEmprunteur | LivreEmprunte | MontantPenalite |\n");
        System.out.format("+--------------------+--------------------+------------------------------------------ +--------------------+\n");

        // Parcourir et afficher les résultats
        while (resultSet.next()) {
            String nom = resultSet.getString("NomEmprunteur");
            String prenom = resultSet.getString("PrenomEmprunteur");
            String titre = resultSet.getString("LivreEmprunte");
            String montant = resultSet.getString("MontantPenalite");

            // Formater chaque ligne
            System.out.format(format, nom, prenom, titre, montant);
        }

        // Afficher la ligne de fin du tableau
        System.out.format("+--------------------+--------------------+------------------------------------------+--------------------+\n");

        // Navigation post affichage
            FichierMenus.postUpdateNavigation();
        // Fermer les ressources
            resultSet.close();
    }

    public static void displayEmpruntRetourne() throws SQLException {
// Exécuter une requête pour afficher les emprunts retournés
        String display = RequeteContainer.afficherEmpruntsRetournes(); // Assurez-vous que cette méthode génère la requête SQL correcte
        ResultSet resultSet = statement.executeQuery(display);
// Afficher l'entête du tableau
        String format = "| %-10s | %-18s | %-18s | %-45s | %-15s | %-15s | %-10s |\n";
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+------------+\n");
        System.out.format("| idEmprunt | NomEmprunteur | PrenomEmprunteur | LivreEmprunte | DateEmprunt | DateRetourEff | Penalité |\n");
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+------------+\n");

        // Parcourir et afficher les résultats
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("NomEmprunteur");
            String prenom = resultSet.getString("PrenomEmprunteur");
            String titre = resultSet.getString("LivreEmprunte");
            String dateEmprunt = resultSet.getString("DateEmprunt");
            String dateRetourEffective = resultSet.getString("DateRetourEffective");
            String penaliteEffective = resultSet.getString("PenaliteEffective"); // "Oui" ou "Non"

            // Formater chaque ligne
            System.out.format(format, id ,nom, prenom, titre, dateEmprunt, dateRetourEffective,
                    penaliteEffective);
        }

        // Afficher la ligne de fin du tableau
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+------------+\n");
        // Navigation post affichage
        FichierMenus.postUpdateNavigation();
        // Fermer les ressources
        resultSet.close();

    }


    public static void displayEmpruntEncours() throws SQLException {// Exécuter une requête pour afficher les emprunts en cours
        String display = RequeteContainer.afficherEmpruntEncours(); // Assurez-vous que cette méthode génère la requête SQL pour les emprunts en cours
        ResultSet resultSet = statement.executeQuery(display);

    // Afficher l'en-tête du tableau
        String format = "| %-10s | %-18s | %-18s | %-45s | %-15s | %-15s |\n";
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+\n");
        System.out.format("| idEmprunt | NomEmprunteur | PrenomEmprunteur | LivreEmprunte | DateEmprunt | DateRetourPrevue|\n");
        System.out.format("+------------+--------------------+-------------------- +-----------------------------------------------+-----------------+-----------------+\n");

        // Parcourir et afficher les résultats
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("NomEmprunteur");
            String prenom = resultSet.getString("PrenomEmprunteur");
            String titre = resultSet.getString("LivreEmprunte");
            String dateEmprunt = resultSet.getString("DateEmprunt");
            String dateRetourPrevue = resultSet.getString("DateRetourPrevue");

            // Formater chaque ligne
            System.out.format(format, id ,nom, prenom, titre, dateEmprunt, dateRetourPrevue);

        }
        // Afficher la ligne de fin du tableau
        System.out.format("+------------+--------------------+--------------------+-----------------------------------------------+-----------------+-----------------+\n");
        // Fermer les ressources
        resultSet.close();
    }

}
