package ma.enset.examenjdbcfx.dao;

import java.util.List;

public interface DAO<T , U> {
    void ajouter(T O);
    void supprimer(U id);
    List<T> afficherListe();
}
