package TpI_equipo9.Services;

import java.util.List;
import java.util.stream.Collectors;

import TpI_equipo9.DAO.ServicioDAO;

import TpI_equipo9.Modelos.Servicio;

public class ServicioService {
	ServicioDAO dao=new ServicioDAO();
	
	public void ingresarServicio(Servicio serv)
	{
		dao.create(serv);
	}
	public Servicio buscarPorID(int id)
	{
		Servicio serv=dao.findOne(id);
		if(serv==null) System.out.println("No se encontro ningun servicio con ID-> "+id);
		return serv;
	}
	
	public List<Servicio> buscarPorNombre(String nombre){
		return buscarTodos().stream().filter((srv)->srv.getNombre().equals(nombre)).collect(Collectors.toList());
	}

	public List<Servicio> buscarPorPrecio(double precio){
		return buscarTodos().stream().filter((srv)->srv.getPrecio()==precio).collect(Collectors.toList());
	}
	
	
	public List<Servicio> buscarTodos()
	{
		List<Servicio> res=dao.findAll();
		if(res.isEmpty()) System.out.println("No hay servicios en la tabla.");
		return res;
	}
	public Servicio ActualizarDatos(Servicio serv)
	{
		return dao.update(serv);
	}
	public void borrarDatos(Servicio srv)
	{
		dao.delete(srv);
	}
}
