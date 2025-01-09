public interface DAO<T> {
    public void create(T objet);
    public void update(T objet);
    public void delete(T objet);
    public T getById(int id);
    public void afficherDetails();
}
