package TpI_equipo9.Equipo9.DAO;

import java.io.Serializable;
import java.util.List;

import TpI_equipo9.Equipo9.config.DbConfig;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class JpaDAO<T extends Serializable> {
	
	private Class<T> clase;
	
	EntityManager eManager = DbConfig.getEntityManager();
			
	public final void setClase(Class<T> claseToSet)
	{
		this.clase=claseToSet;
	}
	
	public T findOne(int id)
	{
		return eManager.find(clase, id);
	}
	
	public List<T> findAll(){
		return eManager.createQuery("From" + clase.getName()).getResultList();
	}
	
	public void create(T entidad)
	{
		EntityTransaction tx= eManager.getTransaction();
		tx.begin();
		eManager.persist(entidad);
		tx.commit();
	}
	
	public T update(T entidad)
	{
		EntityTransaction tx= eManager.getTransaction();
		tx.begin();
		T entidadMerged=eManager.merge(entidad);
		tx.commit();
		return entidadMerged;
	}
	
	public void delete(T entidad)
	{
		EntityTransaction tx= eManager.getTransaction();
		tx.begin();
		eManager.remove(entidad);
		tx.commit();
	}
}
