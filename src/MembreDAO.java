import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MembreDAO {

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
        try{
            statement = connection.createStatement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Méthode pour afficher tous les membres
    public static void displayMembres() throws SQLException {

        // Exécuter une requête
        String display = RequeteContainer.afficherAllElem("membres");
        ResultSet resultSet = statement.executeQuery(display);
        displayElem(resultSet);
        // Fermer les ressources
        resultSet.close();
        statement.close();
    }

    //méthode pour définir l'afficharge d'un resultat de recherche
    public static void displayElem(ResultSet set) throws SQLException {
        if (set == null) {
            System.out.println("Le ResultSet est vide !");
            return;
        }

        // Afficher l'entête du tableau
        String format = "| %-5s | %-18s | %-18s | %-35s | %-18s |\n";
        System.out.format("+-------+--------------------+--------------------+-------------------------------------+--------------------+\n");
        System.out.format("| ID | Nom | Prenom | email | Date Adhésion |\n");
        System.out.format("+-------+--------------------+--------------------+------------------------------------- +--------------------+\n");

        // Parcourir et afficher les résultats
        while (set.next()) {
            int id = set.getInt("id");
            String nom = set.getString("nom");
            String prenom = set.getString("prenom");
            String email = set.getString("email");
            LocalDate adhesionDate = set.getDate("adhesionDate").toLocalDate();

            // Formater chaque ligne
            System.out.format(format, id, nom, prenom, email, adhesionDate);
        }

        // Afficher la ligne de fin du tableau
        System.out.format("+-------+--------------------+--------------------+-------------------------------------+--------------------+\n");
    }

    //Méthode pour rechercher un livre
    static <V> void menuDeRechercheMembres() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le menu de recherche");
        System.out.println("Veuillez choisir une option de recherche:");
        System.out.println("0. Retour au menu principal");
        System.out.println("1. par nom");
        System.out.println("2. par prenom");
        System.out.println("3. par email");
        System.out.println("4. quitter\n");
        System.out.print("Votre choix: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 0:
                FichierMenus.menuChoice();
                break;

            case 1:
                String column1 = "nom";
                System.out.println("Veuillez Entrer un nom: ");
                String nom = scanner.next();
                String display = RequeteContainer.searchElem("membres", column1, nom);
                ResultSet resultSet0 = statement.executeQuery(display);

                System.out.println("liste des membres avec le " + nom);
                displayElem(resultSet0);
                FichierMenus.postUpdateNavigation();
                // Fermer les ressources
                resultSet0.close();
                statement.close();
                break;

            case 2:
                String column2 = "prenom";
                System.out.println("Veuillez Entrer une prenom: ");
                String prenom = scanner.next();
                String display1 = RequeteContainer.searchElem("membres", column2, prenom.toLowerCase());
                ResultSet resultSet1 = statement.executeQuery(display1);

                System.out.println("liste des membres avec le " + prenom);
                displayElem(resultSet1);
                FichierMenus.postUpdateNavigation();
                // Fermer les ressources
                resultSet1.close();
                statement.close();
                break;

            case 3:
                String column3 = "email";
                System.out.println("Veuillez Entrer un Auteur: ");
                String email = scanner.next();
                String display2 = RequeteContainer.searchElem("membres", column3, email.toLowerCase());
                ResultSet resultSet2 = statement.executeQuery(display2);

                System.out.println("liste des membres avec l'email " + email);
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
                System.out.println("Choix invalide... retour au menu principal");
        }
    }

    //Méthode qui gère l'ajout, la modification et la suppresion des membres
    static void menuAddDelUpdateMembres() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans le menu ADD/DEL/UPDATE");
        System.out.println("Veuillez choisir une option de tri:");
        System.out.println("0. retour au menu Principal");
        System.out.println("1. Ajouter");
        System.out.println("2. Modifier");
        System.out.println("3. Supprimer");

        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                FichierMenus.menuChoice();
                break;

            case 1:
                // Ajout d'un nouveau membre
                System.out.println("Bienvenue dans le menu d'ajout des membres");
                System.out.println("Entrer nom du membre");
                String nom = scanner.next();
                System.out.println("Entrer le prenom du membre");
                String prenom = scanner.next();
                System.out.println("Entrer l'email du membre");
                String email = scanner.next();

                // Génération de la requête SQL
                String query = RequeteContainer.insertMembre(nom, prenom, email);
                System.out.println("Requête SQL générée : " + query); // Debug

                // Exécution de la requête SQL
                statement.executeUpdate(query);
                System.out.println("Ajout avec succès !");
                FichierMenus.postUpdateNavigation();
                break;

            case 2:
                // Modification d'une information d'un membre
                Scanner scanner0 = new Scanner(System.in);
                System.out.println("Bienvenue dans le menu de modification des membres");
                System.out.print("indiquer la donnée à entrer pour selectionner le membre : ");
                System.out.print("id / nom / prenom \n");
                System.out.print("votre choix: ");
                String userInput = scanner0.nextLine();
                System.out.print("veuillez entrer " + userInput + " du membre \n");
                System.out.print("Saisir: ");
                String userInput1 = scanner0.nextLine();

                // Affichage des informations du membre sélectionné
                String display = RequeteContainer.searchElem("membres", userInput, userInput1);
                ResultSet resultSet0 = statement.executeQuery(display);
                displayElem(resultSet0);

                System.out.println("Indiquer la donnée à modifier :");
                System.out.println("1. le nom du membre");
                System.out.println("2. Le prenom du membre");
                System.out.println("3. L'email du membre");
                System.out.print("Votre choix : ");
                String userInput2 = scanner0.nextLine(); // Utilisation de nextLine pour capturer correctement l'input

                switch (userInput2) {
                    case "1":
                        // nom
                        System.out.print("Nouvelle donnée (nom du membre): ");
                        String userInput3 = scanner0.nextLine();
                        String updateQuery = RequeteContainer.updateElem("membres", "nom", userInput, userInput3, userInput1);

                        //statement.executeUpdate(updateQuery);
                        System.out.println("Requête SQL générée : " + updateQuery);
                        int rowsUpdated = statement.executeUpdate(updateQuery);
                        if (rowsUpdated > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery);
                        FichierMenus.postUpdateNavigation();
                        break;

                    case "2":

                        // prenom
                        System.out.print("Nouvelle donnée (prenom du membre) : ");
                        String userInput4 = scanner0.nextLine();
                        String updateQuery1 = RequeteContainer.updateElem("membres", "prenom", userInput, userInput4, userInput1);

                        //statement.executeUpdate(updateQuery1);
                        int rowsUpdated1 = statement.executeUpdate(updateQuery1);
                        if (rowsUpdated1 > 0) {
                            System.out.println("Modification réussie !");
                        } else {
                            System.out.println("Aucune ligne mise à jour.");
                        }
                        System.out.println("Requête exécutée : " + updateQuery1);
                        FichierMenus.postUpdateNavigation();
                        break;

                    case "3":
                        // email
                        System.out.print("Nouvelle donnée (email du membre) : ");
                        String userInput5 = scanner0.nextLine();
                        String updateQuery2 = RequeteContainer.updateElem("membres", "email", userInput, userInput5, userInput1); // Changement du nom de la colonne

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

                    default:
                        System.out.println("Choix invalide... retour au menu principal");
                        break;
                }
                break;

            case 3:
                // Suppression d'un membre par titre
                System.out.println("Bienvenue dans le menu de suppression des membres");
                System.out.print("indiquer la donnée à entrer pour selectionner le membre : ");
                System.out.print("id / nom / prenom \n");
                System.out.print("votre choix: ");
                String entre2 = scanner.next();

                System.out.print("veuillez entrer " + entre2 + " du membre \n");
                System.out.print("Saisir: ");
                String entre5 = scanner.next();

                String display1 = RequeteContainer.searchElem("membres", entre2, entre5);
                ResultSet resultSet1 = statement.executeQuery(display1);
                displayElem(resultSet1);

                System.out.print("voulez-vous vraiment supprimer le membre " + entre2 + ": " + entre5 + "\n");
                System.out.print("oui / non \n");
                String entre4 = scanner.next();

                if (entre4.equalsIgnoreCase("non")) {
                    System.out.println("retour au menu principal");
                    FichierMenus.menuChoice();
                } else {
                    String query1 = RequeteContainer.deleteElem("membres", entre2, entre5);
                    System.out.println("Requête SQL générée : " + query1); // Debug

                    // Exécution de la requête SQL
                    statement.executeUpdate(query1);
                    System.out.println("delete avec succès !");
                    FichierMenus.postUpdateNavigation();
                }
                break;
            default:
                System.out.println("choix invalide... retour au menu principal");
                FichierMenus.menuChoice();
        }
    }
/*
     private ArrayList<Membre> membres = new ArrayList<>();

     //Méthode pour ajouter un membre
     public void addMembre(Membre membre) {
            Membre.addMembre(membres, membre);
     }

    // Méthode pour modifier un membre
    public void UpdateMembre(int id, String nom, String prenom, String email, LocalDate adhesionDate) {
        Membre.updateMembre(membres, id, nom, prenom, email, adhesionDate);
    }

    // Méthode pour supprimer un membre
    public void deleteMembre(int id) {
        Membre.deleteMembre(membres, id);
    }
 */
}
