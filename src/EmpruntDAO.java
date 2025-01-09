import java.time.LocalDate;

public class EmpruntDAO implements DAO<Emprunt> {
    public EmpruntDAO() {
    }

    @Override
    public void create(Emprunt emprunt) {
        String sqlReq = "INSERT INTO emprunts (idEmprunt,  membreId, livreId, dateEmprunt, dateRetourPrevue, dateRetourEffective) values (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void delete(Emprunt emprunt) {
        String sqlReq = "DELETE FROM emprunts WHERE id = ?";
    }

    @Override
    public void update(Emprunt emprunt) {
        String sqlReq = "UPDATE emprunts SET membreId = ?, livreId = ?, dateEmprunt = ?, dateRetourPrevue = ?, dateRetourEffective = ? WHERE idEmprunt = ?";
    }

    @Override
    public void afficherDetails() {

    }

    public Emprunt getById(int id) {
        String sqlReq = "SELECT * FROM emprunts WHERE idEmprunt = ?";
        return null;
    }

    public Emprunt getByDateEmprunt(LocalDate dateEmprunt){
        return null;
    }

    public Emprunt getByDateRetourPrevue(LocalDate dateRetourPrevue){
        return null;
    }

}
