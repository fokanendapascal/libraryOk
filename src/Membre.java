import java.time.LocalDate;

public class Membre extends MembreDAO {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate adhesionDate;

    public Membre(int id, String nom, String prenom, String email, LocalDate adhesionDate){
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adhesionDate = adhesionDate;
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

    public void afficherDetails(){
        System.out.printf("Les détails des membres sont: %d,%s,%s,%s, %s\n",getId(), getNom(), getPrenom(), getEmail(), getAdhesionDate());
    }

}
