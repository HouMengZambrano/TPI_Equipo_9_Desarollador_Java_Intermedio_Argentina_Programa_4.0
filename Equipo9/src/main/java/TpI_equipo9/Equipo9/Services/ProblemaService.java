package TpI_equipo9.Equipo9.Services;

import java.util.List;

import TpI_equipo9.Equipo9.DAO.ProblemaDAO;
import TpI_equipo9.Equipo9.Modelos.Problema;

public class ProblemaService {

	ProblemaDAO dao=new ProblemaDAO();
	
	public void ingresarCliente(Problema prob)
	{
		dao.create(prob);
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
	public void ActualizarDatos(Problema prob)
	{
		Problema probAct= dao.update(prob);
		if(probAct==null) System.out.println("No se pudo actualizar el registro"); //no se si funciona esto.Por el tipo de dato devuelto por em.Merge()
	}
}
