import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class LivreDAO {

    // Récupérer la connexion
    static Connection connection;
    static {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Créer un Statement
    static Statement statement;
    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Méthode pour afficher toutes les listes
    public static void displayLivres() throws SQLException {
    // Exécuter une requête
        String display = RequeteContainer.afficherAllElem("livres");
        ResultSet resultSet = statement.executeQuery(display);
        displayElem(resultSet);

        // Fermer les ressources
        resultSet.close();
        statement.close();
    }

    //Méthode pour rechercher un livre
    static <V> void menuDeRechercheLivres() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome To Book Management");
        System.out.println("Choose your option:");
        System.out.println("0. return to principal menu");
        System.out.println("1. Search By Title");
        System.out.println("2. Search by category");
        System.out.println("3. Search By Author");
        System.out.println("4. exit\n");
        System.out.print("Your Choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                FichierMenus.menuChoice();
                break;
            case 1:
                String column1 = "titre";
                System.out.println("Please Enter a Title: ");
                String titre = scanner.next();
                String display = RequeteContainer.searchElem("livres", column1, titre);
                ResultSet resultSet0 = statement.executeQuery(display);
                System.out.println("Book list with Title : , " + titre);
                displayElem(resultSet0);
                FichierMenus.postUpdateNavigation();

                // Fermer les ressources
                resultSet0.close();
                statement.close();
                break;
            case 2:
                String column2 = "categorie";
                System.out.println("Enter Your Category: ");
                String categorie = scanner.next();
                String display1 = RequeteContainer.searchElem("livres", column2, categorie.toLowerCase());
                ResultSet resultSet1 = statement.executeQuery(display1);
                System.out.println("List Of Category : " + categorie);
                displayElem(resultSet1);
                FichierMenus.postUpdateNavigation();

                // Fermer les ressources
                resultSet1.close();
                statement.close();
                break;

            case 3:
                String column3 = "auteur";
                System.out.println("Enter the Author: ");
                String auteur = scanner.next();
                String display2 = RequeteContainer.searchElem("livres", column3, auteur.toLowerCase());
                ResultSet resultSet2 = statement.executeQuery(display2);
                System.out.println("list Of authors book : " + auteur);
                displayElem(resultSet2);
                FichierMenus.postUpdateNavigation();

                // Fermer les ressources
                resultSet2.close();
                statement.close();
                break;

            case 4:
                FichierMenus.end();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice... return to menu");

                return;
        }
    }

    //Méthode qui gère l'ajout modification et suppresion des livres
    static void menuAddDelUpdateLivres() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ADD/DEL/UPDATE");
        System.out.println("Choose your option:");
        System.out.println("0. return to principal menu");
        System.out.println("1. Add");
        System.out.println("2. Modifer");
        System.out.println("3. Delete");

        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                FichierMenus.menuChoice();
                break;

            case 1:

                // Ajout d'un nouveau livre
                System.out.println("Welcome to book object");
                // Consommer le saut de ligne laissé par next()
                scanner.nextLine();
                System.out.println("Entrer le titre du livre");
                String titre = scanner.nextLine();
                System.out.println("Entrer le nom de l'auteur");
                String auteur = scanner.nextLine();

                //String isbn = scanner.next();
                // Consommer le saut de ligne laissé par next()
                //scanner.nextLine();
                System.out.println("Entrer la catégorie du livre");
                String categorie = scanner.nextLine();
                //System.out.println("Entrer l'année de publication");
                //String dateDeParution = scanner.nextLine();
                System.out.println("Entrer le nombre d'exemplaires");
                int nombreExemplaire = Integer.parseInt(scanner.next());

                // Génération de la requête SQL
                String query = RequeteContainer.insertLivre(titre, auteur, categorie, nombreExemplaire);
                System.out.println("Requête SQL générée : " + query); // Debug

                // Exécution de la requête SQL
                statement.executeUpdate(query);
                System.out.println("Ajout avec succès !");
                FichierMenus.postUpdateNavigation();
                break;

            case 2:
                // Modification d'une information de livre
                Scanner scanner0 = new Scanner(System.in);
                System.out.println("Bienvenue dans le menu de modification des livres");
                System.out.print("indiquer la donnée à entrer pour selectionner le livre : ");
                System.out.print("id ou titre \n");
                System.out.print("votre choix: ");
                String userInput = scanner0.nextLine();
                System.out.print("veuillez entrer " + userInput + " du livre \n");
                System.out.print("Saisir: ");
                String userInput1 = scanner0.nextLine();

                // Affichage des informations du livre sélectionné
                String display = RequeteContainer.searchElem("livres", userInput, userInput1);
                ResultSet resultSet0 = statement.executeQuery(display);
                displayElem(resultSet0);
                System.out.println("Indiquer la donnée à modifier :");

                System.out.println("1. Le titre du livre");
                System.out.println("2. Le nom de l'auteur");
                System.out.println("3. La catégorie du livre");
                System.out.println("4. Le nombre d'exemplaires");
                System.out.print("Votre choix : ");
                String userInput2 = scanner0.nextLine(); // Utilisation de nextLine pour capturer correctement l'input

                switch (userInput2) {

                    case "1":
                        // Titre
                        System.out.print("Nouvelle donnée (titre) :");
                        String userInput4 = scanner0.nextLine();
                        String updateQuery1 = RequeteContainer.updateElem("livres", "titre", userInput, userInput4, userInput1);

                        // statement.executeUpdate(updateQuery1);
                        int rowsUpdated1 = statement.executeUpdate(updateQuery1);
                        if (rowsUpdated1 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery1);
                        FichierMenus.postUpdateNavigation();
                        break;

                    case "2":
                        // Auteur
                        System.out.print("Nouvelle donnée (nom de l'auteur) : ");
                        String userInput5 = scanner0.nextLine();
                        String updateQuery2 = RequeteContainer.updateElem("livres", "auteur", userInput, userInput5, userInput1); // Changement du nom de la colonne

                        //statement.executeUpdate(updateQuery2);
                        int rowsUpdated2 = statement.executeUpdate(updateQuery2);
                        if (rowsUpdated2 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery2);
                        FichierMenus.postUpdateNavigation();
                        break;

                    case "3":
                        // Catégorie
                        System.out.print("Nouvelle donnée (catégorie): ");
                        String userInput7 = scanner0.nextLine();
                        String updateQuery4 = RequeteContainer.updateElem("livres", "categorie", userInput, userInput7, userInput1);

                        //statement.executeUpdate(updateQuery4);
                        int rowsUpdated4 = statement.executeUpdate(updateQuery4);
                        if (rowsUpdated4 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery4);
                        FichierMenus.postUpdateNavigation();
                        break;

                    case "4":
                        // Nombre d'exemplaires
                        System.out.print("Nouvelle donnée (nombre d'exemplaires) : ");
                        String userInput9 = scanner0.nextLine();
                        String updateQuery6 = RequeteContainer.updateElem("livres", "nombreExemplaires", userInput, userInput9, userInput1); // Changement du nom de la colonne

                        //statement.executeUpdate(updateQuery6);
                        int rowsUpdated6 = statement.executeUpdate(updateQuery6);
                        if (rowsUpdated6 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery6);
                        FichierMenus.postUpdateNavigation();
                        break;

                    default:
                        System.out.println("Choix invalide... retour au menu principal");
                        break;
                }
                break;

            case 3:
                // Suppression d'un livre par titre
                System.out.println("Bienvenue dans le menu de suppression des livres");
                System.out.print("indiquer la donnée à entrer pour selectionner le livre : ");
                System.out.print("id / titre \n");
                System.out.print("votre choix: ");
                String entre2 = scanner.next();
                scanner.nextLine();

                System.out.print("veuillez entrer " + entre2 + " du livre \n");
                System.out.print("Saisir: ");
                String entre5 = scanner.nextLine();
                String display1 = RequeteContainer.searchElem("livres", entre2, entre5);
                ResultSet resultSet1 = statement.executeQuery(display1);
                displayElem(resultSet1);
                System.out.print("voulez-vous vraiment supprimer le livre " + entre2 + ": " + entre5 + "\n");
                System.out.print("oui / non \n");
                String entre4 = scanner.next();

                if (entre4.equalsIgnoreCase("non")) {
                    System.out.println("retour au menu principal");
                    FichierMenus.menuChoice();
                } else {
                    String query1 = RequeteContainer.deleteElem("livres", entre2, entre5);
                    System.out.println("Requête SQL générée : " + query1); // Debug

                    // Exécution de la requête SQL
                    statement.executeUpdate(query1);
                    System.out.println("delete avec succès !");
                    FichierMenus.postUpdateNavigation();
                }
                break;
            default:
                System.out.println("choix invalide... retour au menu principal.");
                FichierMenus.menuChoice();
        }
        scanner.close();
    }

    //affichage des éléments
    public static void displayElem(ResultSet set) throws SQLException {
        if (set == null) {
            System.out.println("Le ResultSet est vide !");
            return;
        }

        // Afficher l'en-tête du tableau
        System.out.println("\nListe des livres : \n");
        String format = "| %-5s | %-42s | %-25s | %-25s | %-5s |\n";
        System.out.format("+------- +-------------------------------------------- +---------------------------+---------------------------+-------+\n");
        System.out.format("| ID | Titre | Auteur | Catégorie | NbEx |\n");
        System.out.format("+------- +-------------------------------------------- +---------------------------+---------------------------+-------+\n");

        // Parcourir et afficher les résultats
        while (set.next()) {
            int id = set.getInt("id");
            String titre = set.getString("titre");
            String auteur = set.getString("auteur");
            String categorie = set.getString("categorie");
            int nombreExemplaires = set.getInt("nombreExemplaires");

            // Formater chaque ligne
            System.out.format(format, id, titre, auteur, categorie,
                    nombreExemplaires);
        }

        // Afficher la ligne de fin du tableau
        System.out.format("+-------+--------------------------------------------+---------------------------+---------------------------+-------+\n");
    }
}











