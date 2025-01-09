import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprunt extends EmpruntDAO {
    private int idEmprunt;
    private int membreId;
    private int livreId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective;

    public Emprunt(int idEmprunt, int membreId, int livreId, LocalDate dateEmprunt, LocalDate dateRetourPrevue, LocalDate dateRetourEffective){
        super();
        this.idEmprunt = idEmprunt;
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetourEffective = dateRetourEffective;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public int getMembreId() {
        return membreId;
    }

    public void setMembreId(int membreId) {
        this.membreId = membreId;
    }

    public int getLivreId() {
        return livreId;
    }

    public void setLivreId(int livreId) {
        this.livreId = livreId;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetourPrevue() {
        return dateRetourPrevue;
    }

    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public LocalDate getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(LocalDate dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    public void calculerPenalite(LocalDate x, LocalDate y) {
        int nbreJours = (int) x.until(y, ChronoUnit.DAYS);
        int penalite = 0;
        if (nbreJours <= 0){
            System.out.println("Pas de pénalité " );
        }else{
            penalite = 100 * nbreJours;
            System.out.println("La pénalité est de: " +penalite);
        }
    }

    public void afficherDetails() {
        if (dateRetourPrevue.isBefore(dateRetourEffective)) {
            System.out.printf("Les emprunts en retard : %d, %d, %d, %s, %s, %s,\n", getIdEmprunt(), getMembreId(), getLivreId(), getDateEmprunt(), getDateRetourPrevue(), getDateRetourEffective());
        } else {
            System.out.printf("Les emprunts en cours : %d, %d, %d, %s, %s, %s,\n", getIdEmprunt(), getMembreId(), getLivreId(), getDateEmprunt(), getDateRetourPrevue(), getDateRetourEffective());
        }
    }

}
