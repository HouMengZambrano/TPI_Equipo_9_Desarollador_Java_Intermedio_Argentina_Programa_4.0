package TpI_equipo9.Services;

import java.util.List;
import java.util.stream.Collectors;

import TpI_equipo9.DAO.IncidenteDAO;
import TpI_equipo9.Modelos.Cliente;
import TpI_equipo9.Modelos.Incidente;
import TpI_equipo9.Modelos.Tecnico;

public class IncidenteService {
	IncidenteDAO dao=new IncidenteDAO();
	
	public void ingresarIncidente(Incidente inc)
	{
		dao.create(inc);
	}
	public Incidente buscarPorID(int id)
	{
		Incidente tec=dao.findOne(id);
		if(tec==null) System.out.println("No se encontro ningun incidente con ID-> "+id);
		return tec;
	}
	
	public List<Incidente> buscarPorEstado(String estadoActual){
		return buscarTodos().stream().filter((inc)->inc.getEstadoActual().equals(estadoActual)).collect(Collectors.toList());
	}
	
	public List<Incidente> buscarPorComplejidad(int complejidad){
		return buscarTodos().stream().filter((inc)->inc.getComplejidad()==complejidad).collect(Collectors.toList());
	}

	public List<Incidente> buscarPorFechaAlta(String fecha){
		return buscarTodos().stream().filter((inc)->inc.getFechaAlta().toString().contains(fecha)).collect(Collectors.toList());
	}
	public List<Incidente> buscarPorFechaBaja(String fecha){
		return buscarTodos().stream().filter((inc)->inc.getFechaResol().toString().contains(fecha)).collect(Collectors.toList());
	}
	public List<Incidente> buscarPorCliente(Cliente cl){
		return buscarTodos().stream().filter((inc)->inc.getCliente().getId()==cl.getId()).collect(Collectors.toList());
	}	
	public List<Incidente> buscarPorTecnico(Tecnico tec){
		return buscarTodos().stream().filter((inc)->inc.getTecnicos().stream().anyMatch(t->t.getId()==tec.getId())).collect(Collectors.toList());
	}	
	public List<Incidente> buscarPorTiempoRes(double tem){
		return buscarTodos().stream().filter((inc)->inc.getTiempoResolucion()==tem).collect(Collectors.toList());
	}	
	
	public List<Incidente> buscarTodos()
	{
		List<Incidente> res=dao.findAll();
		if(res.isEmpty()) System.out.println("No hay incidentes en la tabla.");
		return res;
	}
	public Incidente ActualizarDatos(Incidente inc)
	{
		return dao.update(inc);
	}
	public void borrarDatos(Incidente inc)
	{
		dao.delete(inc);
	}

}
