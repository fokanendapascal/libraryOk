import java.util.*;

public class Livre {
    private int id;
    private String titre;
    private String auteur;
    private String categorie;
    private int nombreExemplaires;


    public Livre(int id, String titre, String auteur, String categorie, int nombreExemplaires) {
       this.id = 0;
        this.titre = "";
        this.auteur = "";
        this.categorie = "";
        this.nombreExemplaires = 0;
    }


    public int getId(){
        return id;
    }

    public void setId(){
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = this.titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = this.auteur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = this.categorie;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = this.nombreExemplaires;
    }

    // Méthodes pour ajouter, modifier et supprimer un livre
    public static void addLivre(ArrayList<Livre> livres, Livre livre) {
        livres.add(livre);
    }

    public static void updateLivre(ArrayList<Livre> livres, int id,String titre, String auteur, String categorie, int nombreExemplaires) {
        for (Livre livre : livres) {
            if (livre.getId() == id) {
                livre.setTitre(titre);
                livre.setAuteur(auteur);
                livre.setCategorie(categorie);
                livre.setNombreExemplaires(nombreExemplaires);
            }
        }
    }
/*
    public static void deleteLivre(ArrayList<Livre> livres, int id) {
        livres.removeIf(livre -> livre.getId() == id);
    }

*/




/*
   public Livre afficherDetails(Livre livre) {
        System.out.printf("Les détails des livres sont: %d,%s,%s,%s,%d\n", getId(), getTitre(), getAuteur(), getCategorie(), getNombreExemplaires());
       return livre;
   }
*/

}
