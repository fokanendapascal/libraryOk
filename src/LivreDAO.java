import java.util.List;

public class LivreDAO implements DAO<Livre> {

    @Override
    public void create(Livre livre) {
        String sqlReq = "INSERT INTO livres (id, titre, auteur, categorie, nombreExemplaires) values (?, ?, ?, ?, ?)";
    }

    @Override
    public void delete(Livre livre) {
        String sqlReq = "DELETE FROM livres WHERE id = ? ";
    }

    @Override
    public void update(Livre livre) {
        String sqlReq = "UPDATE livres SET titre = ?, auteur = ?, categorie = ?, nombreExemplaires = ? where id = ? ";
    }

    @Override
    public Livre getById(int id) {
        String sqlReq = "SELECT * FROM livres WHERE id = ?";
        return null;
    }

    @Override
    public void afficherDetails() {

    }

    public Livre getByTitle(String titre){
        return null;
    }

    public List<Livre> getByAuthor(String auteur){

        return null;
    }

    public List<Livre> getByCategorie(String categorie){

        return null;
    }

}
