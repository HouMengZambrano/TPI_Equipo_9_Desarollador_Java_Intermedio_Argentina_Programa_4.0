package TpI_equipo9.Equipo9.Services;

import java.util.List;

import TpI_equipo9.Equipo9.DAO.TecnicoDAO;
import TpI_equipo9.Equipo9.Modelos.Tecnico;

public class TecnicoService {
	TecnicoDAO dao=new TecnicoDAO();
	
	public void ingresarCliente(Tecnico tec)
	{
		dao.create(tec);
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
	public void ActualizarDatos(Tecnico tec)
	{
		Tecnico tecAct= dao.update(tec);
		if(tecAct==null) System.out.println("No se pudo actualizar el registro"); //no se si funciona esto.Por el tipo de dato devuelto por em.Merge()
	}
}
