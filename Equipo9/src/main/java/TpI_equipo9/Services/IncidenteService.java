package TpI_equipo9.Services;

import java.util.List;

import TpI_equipo9.DAO.IncidenteDAO;
import TpI_equipo9.Modelos.Incidente;

public class IncidenteService {
	IncidenteDAO dao=new IncidenteDAO();
	
	public void ingresarCliente(Incidente inc)
	{
		dao.create(inc);
	}
	public Incidente buscarPorID(int id)
	{
		Incidente tec=dao.findOne(id);
		if(tec==null) System.out.println("No se encontro ningun incidente con ID-> "+id);
		return tec;
	}
	
	public List<Incidente> buscarTodos()
	{
		List<Incidente> res=dao.findAll();
		if(res.isEmpty()) System.out.println("No hay incidentes en la tabla.");
		return res;
	}
	public void ActualizarDatos(Incidente inc)
	{
		Incidente incAct= dao.update(inc);
		if(incAct==null) System.out.println("No se pudo actualizar el registro"); //no se si funciona esto.Por el tipo de dato devuelto por em.Merge()
	}

}
