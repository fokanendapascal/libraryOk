import java.util.Scanner;
import java.sql.SQLException;

public class FichierMenus {

    // Menus de notre application
    public FichierMenus() throws SQLException {
    }

    static void menuChoice() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome To Library Management\n");
        System.out.println("Please choose your options from 1 to 4: \n");
        System.out.println("1. Book Management");
        System.out.println("2. Member Management");
        System.out.println("3. Book Loan And Penalties");
        System.out.println("4. Exit\n");
        System.out.println("Make A Choice: ");

        int choice = scanner.nextInt();
        switch (choice) {

            case 1:
                generiqueMenuHead("livres");
                int choice1 = scanner.nextInt();
                switch (choice1) {
                    case 0:
                        menuChoice();
                        break;
                    case 1:
                        LivreDAO.displayLivres();
                        generiqueMenuBody("livres");
                        choice1 = scanner.nextInt();
                        optionDeSortie(scanner, choice1);
                        break;
                    case 2:
                        LivreDAO.menuAddDelUpdateLivres();
                        break;
                    case 3:
                        LivreDAO.menuDeRechercheLivres();
                        break;
                    default:
                        System.out.println("Invalid choice... return to menu");
                        System.out.println("Your choice: ");
                        choice1 = scanner.nextInt();
                }
                break;
            case 2:
                generiqueMenuHead("membres");
                int choice2 = scanner.nextInt();
                switch (choice2) {
                    case 0:
                        menuChoice();
                        break;
                    case 1:
                        MembreDAO.displayMembres();
                        generiqueMenuBody("membres");
                        choice2 = scanner.nextInt();
                        optionDeSortie(scanner, choice2);
                        break;
                    case 2:
                        MembreDAO.menuAddDelUpdateMembres();
                        break;
                    case 3:
                        MembreDAO.menuDeRechercheMembres();
                        break;
                    default:
                        System.out.println("Invalid Choice... return to menu");
                        System.out.println("Your choice: ");
                        choice2 = scanner.nextInt();
                }
                break;

            case 3:
                EmpruntDAO.menuEmpruntPenalite();
                break;

            case 4:
                end();
                System.exit(0);
                break;

            default:
                System.out.println("Invalid Choice... return to main menu");
                menuChoice();
        }
    }

    private static void optionDeSortie(Scanner scanner, int choice1) throws SQLException {
        switch (choice1) {
            case 1:
                menuChoice();
                break;
            case 2:
                end();
                System.exit(0);
                break;
            default:
                System.out.println("invalid choice... return to menu");
                System.out.println("Your choice: ");
                choice1 = scanner.nextInt();
        }
    }

    // Méthode pour gérer la navigation post-mise à jour
    static void postUpdateNavigation() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWill You want to return to main menu ? :");
            System.out.println("1 : Return To principal menu");
            System.out.println("2 : Exit\n");
            System.out.println("your Choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                menuChoice();
                break; // Sort de la boucle après le retour au menu
            } else if (choice.equals("2")) {
                System.out.println("Thank you...");
                System.exit(0); // Quitte l'application
            } else {
                System.out.println("Choice invalid enter choice between '1' and '2'.");
            }
        }
    }

    static void end () {
        System.out.println("Thanks You ");
    }

    public static void generiqueMenuHead (String elem){
        System.out.println("Book Management " + elem);
        System.out.println("0. Return to main menu");
        System.out.println("1. List Of " + elem);
        System.out.println("2. Add / Update / Delate " + elem);
        System.out.println("3. Search an " + elem);
        System.out.print("Your Choice: ");
    }

    public static void generiqueMenuBody (String elem){
        System.out.println("this is the list of " + elem + " in our library \n");
        System.out.println("1. return to main menu");
        System.out.println("2. Exit\n");
        System.out.println("Your Choice: ");
    }
}
