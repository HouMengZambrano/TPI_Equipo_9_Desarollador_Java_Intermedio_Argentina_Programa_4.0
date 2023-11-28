package TpI_equipo9.Services;

import java.util.List;
import java.util.stream.Collectors;

import TpI_equipo9.DAO.ProblemaDAO;
import TpI_equipo9.Modelos.Especialidad;
import TpI_equipo9.Modelos.Problema;
import TpI_equipo9.Modelos.Servicio;


public class ProblemaService {

	ProblemaDAO dao=new ProblemaDAO();
	
	public void ingresarProblema(Problema prob)
	{
		dao.create(prob);
	}
	public List<Problema> buscarPorTipo(String tipo){
		return buscarTodos().stream().filter((prob)->prob.getTipo().equals(tipo)).collect(Collectors.toList());
	}
	public List<Problema> buscarPorTiempoMax(double Tmax){
		return buscarTodos().stream().filter((prob)->prob.getTiempoMax()==Tmax).collect(Collectors.toList());
	}
	public List<Problema> buscarPorEspecialidad(Especialidad esp){
		return buscarTodos().stream().filter((prob)->prob.getEspecialidades().stream().anyMatch(e->e.getId()==esp.getId())).collect(Collectors.toList());
	}
	
	
	public List<Problema> buscarPorServicios(Servicio ser){
		return buscarTodos().stream().filter((prob)->prob.getServicios().stream().anyMatch(s->s.getId()==ser.getId())).collect(Collectors.toList());
	}	
	
	
	public Problema buscarPorID(int id)
	{
		Problema prob=dao.findOne(id);
		if(prob==null) System.out.println("No se encontro ningun problema con ID-> "+id);
		return prob;
	}
	
	public List<Problema> buscarTodos()
	{
		List<Problema> res=dao.findAll();
		if(res.isEmpty()) System.out.println("No hay 'problemas' en la tabla.");
		return res;
	}
	public Problema ActualizarDatos(Problema prob)
	{
		return dao.update(prob);
	}
	public void borrarDatos(Problema prob)
	{
		dao.delete(prob);
	}
}
