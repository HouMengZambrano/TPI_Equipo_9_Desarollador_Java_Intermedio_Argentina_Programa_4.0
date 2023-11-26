package TpI_equipo9.Equipo9.Services;

import java.util.List;
import java.util.stream.Collectors;

import TpI_equipo9.Equipo9.DAO.ClienteDAO;
import TpI_equipo9.Equipo9.Modelos.Cliente;

public class ClienteService {

	 ClienteDAO dao=new ClienteDAO();
	
	public  void ingresarCliente(Cliente cl)
	{
		dao.create(cl);
	}
	public List<Cliente> buscarPorNombre(String nombre){
		return buscarTodos().stream().filter((cl)->cl.getNombre().equals(nombre)).collect(Collectors.toList());
	}
	public List<Cliente> buscarPorCUIT(String CUIT){
		return buscarTodos().stream().filter((cl)->cl.getCUIT().equals(CUIT)).collect(Collectors.toList());
	}
	public  Cliente buscarPorID(int id)
	{
		Cliente cl=dao.findOne(id);
		if(cl==null) System.out.println("No se encontro ningun cliente con ID-> "+id);
		return cl;
	}
	
	public  List<Cliente> buscarTodos()
	{
		List<Cliente> res=dao.findAll();
		if(res.isEmpty()) System.out.println("No hay clientes en la tabla.");
		return res;
	}
	public  void ActualizarDatos(Cliente cl)
	{
		Cliente clAct= dao.update(cl);
		if(clAct==null) System.out.println("No se pudo actualizar el registro"); //no se si funciona esto.Por el tipo de dato devuelto por em.Merge()
	}

}
