package TpI_equipo9.Services;

import java.util.List;
import java.util.stream.Collectors;

import TpI_equipo9.DAO.TecnicoDAO;
import TpI_equipo9.Modelos.Especialidad;
import TpI_equipo9.Modelos.Incidente;
import TpI_equipo9.Modelos.Tecnico;

public class TecnicoService {
	TecnicoDAO dao=new TecnicoDAO();
	
	public void ingresarTecnico(Tecnico tec)
	{
		dao.create(tec);
	}
	
	// buscar 
	public List<Tecnico> buscarPorNombre(String nombre){
		return buscarTodos().stream().filter((tec)->tec.getNombre().equals(nombre)).collect(Collectors.toList());
	}
	
	public List<Tecnico> buscarPorApellido(String apellido){
		return buscarTodos().stream().filter((tec)->tec.getApellido().equals(apellido)).collect(Collectors.toList());
	}
	
	public List<Tecnico> buscarPorNroWhatsapp(String nroWhatsapp){
		return buscarTodos().stream().filter((tec)->tec.getNroWhatsapp().equals(nroWhatsapp)).collect(Collectors.toList());
	}
	
	public List<Tecnico> buscarPorEmail(String email){
		return buscarTodos().stream().filter((tec)->tec.getEmail().equals(email)).collect(Collectors.toList());
	}
	public List<Tecnico> buscarPorFechaAlta(String fecha){
		return buscarTodos().stream().filter((tec)->tec.getFechaAlta().toString().contains(fecha)).collect(Collectors.toList());
	}
	public List<Tecnico> buscarPorFechaBaja(String fecha){
		return buscarTodos().stream().filter((tec)->tec.getFechaBaja().toString().contains(fecha)).collect(Collectors.toList());
	}
	public List<Tecnico> buscarPorEspecialidad(Especialidad esp){
		return buscarTodos().stream().filter((tec)->tec.getEspecialidades().stream().anyMatch(e->e.getId()==esp.getId())).collect(Collectors.toList());
	}
	public List<Tecnico> buscarPorIncidente(Incidente inc){
		return buscarTodos().stream().filter((tec)->tec.getIncidentes().stream().anyMatch(i->i.getId()==inc.getId())).collect(Collectors.toList());
	}
	public Tecnico buscarPorID(int id)
	{
		Tecnico tec=dao.findOne(id);
		if(tec==null) System.out.println("No se encontro ningun tecnico con ID-> "+id);
		return tec;
	}
	
	public List<Tecnico> buscarTodos()
	{
		List<Tecnico> res=dao.findAll();
		if(res.isEmpty()) System.out.println("No hay tecnicos en la tabla.");
		return res;
	}
	public Tecnico ActualizarDatos(Tecnico tec)
	{
		return dao.update(tec);
	}
	public void borrarDatos(Tecnico tec)
	{
		dao.delete(tec);
	}
}
