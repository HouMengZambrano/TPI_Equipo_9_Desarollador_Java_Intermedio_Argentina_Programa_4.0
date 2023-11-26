package TpI_equipo9.Equipo9.DAO;

import java.util.List;

public interface DAO<T> {
	
	public void findOne(int id);
	public List<T> findAll();
	public void create(T entidad);
	public T update (T entidad);
	public void delete(T entidad);
	
}
