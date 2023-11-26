package TpI_equipo9.Services;

import java.util.List;

import TpI_equipo9.DAO.EspecialidadDAO;
import TpI_equipo9.Modelos.Especialidad;


public class EspecialidadService {
	 EspecialidadDAO dao=new EspecialidadDAO();

	public  void ingresarCliente(Especialidad esp)
	{
		dao.create(esp);
	}
	public  Especialidad buscarPorID(int id)
	{
		Especialidad esp=dao.findOne(id);
		if(esp==null) System.out.println("No se encontro ninguna especialidad con ID-> "+id);
		return esp;
	}
	
	public  List<Especialidad> buscarTodos()
	{
		List<Especialidad> res=dao.findAll();
		if(res.isEmpty()) System.out.println("No hay especialidades en la tabla.");
		return res;
	}
	public  void ActualizarDatos(Especialidad esp)
	{
		Especialidad espAct= dao.update(esp);
		if(espAct==null) System.out.println("No se pudo actualizar el registro"); //no se si funciona esto.Por el tipo de dato devuelto por em.Merge()
	}

}
