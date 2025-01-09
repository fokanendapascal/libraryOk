import java.time.LocalDate;
import java.util.List;

public class MembreDAO implements DAO<Membre>{

    @Override
    public void create(Membre membre) {
        String sqlReq = "INSERT INTO membres (id, nom, prenom, email, adhesionDate) values (?, ?, ?, ?, ?)";
    }

    @Override
    public void delete(Membre membre) {
        String sqlReq = "DELETE FROM membres WHERE id = ? ";
    }

    @Override
    public void update(Membre membre) {
        String sqlReq = "UPDATE membres SET nom = ?, prenom = ?, email = ?, adhesionDate = ? WHERE id = ? ";
    }

    @Override
    public void afficherDetails() {

    }

    public Membre getById(int id) {
        String sqlReq = "SELECT * FROM membres WHERE id = ?";
        return null;
    }

    public Membre getByNomPrenom(String nom, String prenom) {
        String sqlReq = "SELECT * FROM membres WHERE nom = ?, prenom = ?";
        return null;
    }
    public List<Membre> getByAdhesionDate(LocalDate adhesionDate) {
        String sqlReq = "SELECT * FROM membres WHERE adhesionDate = ?";
        return null;
    }
}
