public class Livre extends LivreDAO {
    private int id;
    private String titre;
    private String auteur;
    private String categorie;
    private int nombreExemplaires;

    public Livre(int id, String titre, String auteur, String categorie, int nombreExemplaires) {
        super();
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nombreExemplaires = nombreExemplaires;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }

    public void afficherDetails() {
        System.out.printf("Les détails des livres sont: %d,%s,%s,%s,%d\n",getId(), getTitre(), getAuteur(), getCategorie(), getNombreExemplaires());
    }
}
