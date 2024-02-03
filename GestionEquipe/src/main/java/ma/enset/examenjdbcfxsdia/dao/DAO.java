package ma.enset.examenjdbcfxsdia.dao;

import java.util.List;

public interface DAO<T> {
    List<T> findAll();

    T findById(int id);

    void save(T entity);

    void delete(int id);

    void update(T entity);
}
