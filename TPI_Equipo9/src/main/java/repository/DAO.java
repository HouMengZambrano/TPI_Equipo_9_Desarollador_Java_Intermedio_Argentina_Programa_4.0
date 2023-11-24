package repository;
import config.DBConfig;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.io.Serializable;
import java.util.List;

//Esta clase se encarga de hacer el manejo de datos
public abstract class DAO<T extends Serializable> {
    private Class <T> clase;
    EntityManager entityManager = DBConfig.getEntityManager();

    public final void setClase(Class<T> claseToSet){
        this.clase = claseToSet;
    }


    public T find(Integer id){
        return entityManager.find(clase, id);
    }

    public List<T> findAll(){
        return  entityManager.createQuery("from " + clase.getName()).getResultList();
    }
    public void create ( T entity){

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist( entity );
        tx.commit();
    }

    public T update (T entity){
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        T entityMerged = entityManager.merge( entity );
        tx.commit();
        return entityMerged;
    }

    public void delete( T entity ){
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove( entity );
        tx.commit();
    }


}
