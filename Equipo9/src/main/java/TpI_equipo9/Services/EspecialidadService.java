package TpI_equipo9.Services;

import java.util.List;
import java.util.stream.Collectors;

import TpI_equipo9.DAO.EspecialidadDAO;
import TpI_equipo9.Modelos.Especialidad;
import TpI_equipo9.Modelos.Problema;
import TpI_equipo9.Modelos.Tecnico;


public class EspecialidadService {
	 EspecialidadDAO dao=new EspecialidadDAO();

	public  void ingresarEspecialidad(Especialidad esp)
	{
		dao.create(esp);
	}
	
	
	public  Especialidad buscarPorID(int id)
	{
		Especialidad esp=dao.findOne(id);
		if(esp==null) System.out.println("No se encontro ninguna especialidad con ID-> "+id);
		return esp;
	}
	public List<Especialidad> buscarPorNombre(String nombre){
		return buscarTodos().stream().filter((esp)->esp.getNombre().equals(nombre)).collect(Collectors.toList());
	}
	public List<Especialidad> buscarPorTecnico(Tecnico tec){
		return buscarTodos().stream().filter((esp)->esp.getTecnicos().stream().anyMatch(t->t.getId()==tec.getId())).collect(Collectors.toList());
	}	
	public List<Especialidad> buscarPorProblema(Problema prob){
		return buscarTodos().stream().filter((esp)->esp.getTecnicos().stream().anyMatch(p->p.getId()==prob.getId())).collect(Collectors.toList());
	}	
	public  List<Especialidad> buscarTodos()
	{
		List<Especialidad> res=dao.findAll();
		if(res.isEmpty()) System.out.println("No hay especialidades en la tabla.");
		return res;
	}
	public Especialidad ActualizarDatos(Especialidad esp)
	{
		return dao.update(esp);
	}
	public void borrarDatos(Especialidad esp)
	{
		dao.delete(esp);
	}

}
