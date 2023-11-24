package service;

import java.util.Collection;
import java.util.Optional;

public interface ServicioDAO<T> {

    T findById(Integer id);
    Collection<T> findAll();
    void create(T t);
    void update( T t);
    void delete( T t);

}
