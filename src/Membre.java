import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class Membre {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate adhesionDate;

    public Membre(int id, String nom, String prenom, String email, LocalDate adhesionDate){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adhesionDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getAdhesionDate() {
        return adhesionDate;
    }

    public void setAdhesionDate(LocalDate adhesionDate) {
        this.adhesionDate = adhesionDate;
    }

    // Méthodes pour ajouter, modifier et supprimer un membre
    public static void addMembre(ArrayList<Membre> membres, Membre membre) {
        membres.add(membre);
    }

    public static void updateMembre(ArrayList<Membre> membres, int id, String nom, String prenom, String email, LocalDate adhesionDate) {
        for (Membre membre : membres) {
            if (membre.getId() == id) {
                membre.setNom(nom);
                membre.setPrenom(prenom);
                membre.setEmail(email);
                membre.setAdhesionDate(adhesionDate);
            }
        }
    }

    public static void deleteMembre(ArrayList<Membre> membres, int id) {
        membres.removeIf(membre -> membre.getId() == id);
    }


    public Membre afficherDetails(Membre membre){
        System.out.printf("Les détails des membres sont: %d,%s,%s,%s, %s\n",getId(), getNom(), getPrenom(), getEmail(), getAdhesionDate());
        return membre;
    }

}
