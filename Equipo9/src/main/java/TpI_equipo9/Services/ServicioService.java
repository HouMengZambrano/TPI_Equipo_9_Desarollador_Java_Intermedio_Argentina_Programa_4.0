package TpI_equipo9.Services;

import java.util.List;

import TpI_equipo9.DAO.ServicioDAO;
import TpI_equipo9.Modelos.Servicio;

public class ServicioService {
	ServicioDAO dao=new ServicioDAO();
	
	public void ingresarCliente(Servicio serv)
	{
		dao.create(serv);
	}
	public Servicio buscarPorID(int id)
	{
		Servicio serv=dao.findOne(id);
		if(serv==null) System.out.println("No se encontro ningun servicio con ID-> "+id);
		return serv;
	}
	
	public List<Servicio> buscarTodos()
	{
		List<Servicio> res=dao.findAll();
		if(res.isEmpty()) System.out.println("No hay servicios en la tabla.");
		return res;
	}
	public void ActualizarDatos(Servicio serv)
	{
		Servicio servAct= dao.update(serv);
		if(servAct==null) System.out.println("No se pudo actualizar el registro"); //no se si funciona esto.Por el tipo de dato devuelto por em.Merge()
	}
}
