package TpI_equipo9.Services;

import java.util.List;
import java.util.stream.Collectors;

import TpI_equipo9.DAO.ClienteDAO;
import TpI_equipo9.Modelos.Cliente;
import TpI_equipo9.Modelos.Incidente;
import TpI_equipo9.Modelos.Servicio;


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
	public List<Cliente> buscarPorNroWhatsapp(String nroWhatsapp){
		return buscarTodos().stream().filter((cl)->cl.getNroWhatsapp().equals(nroWhatsapp)).collect(Collectors.toList());
	}
	public List<Cliente> buscarPorEmail(String email){
		return buscarTodos().stream().filter((cl)->cl.getEmail().equals(email)).collect(Collectors.toList());
	}
	public List<Cliente> buscarPorFechaAlta(String fecha){
		return buscarTodos().stream().filter((cl)->cl.getFechaAlta().toString().contains(fecha)).collect(Collectors.toList());
	}
	public List<Cliente> buscarPorFechaBaja(String fecha){
		return buscarTodos().stream().filter((cl)->cl.getFechaBaja().toString().contains(fecha)).collect(Collectors.toList());
	}
	public List<Cliente> buscarPorIncidente(Incidente inc){
		return buscarTodos().stream().filter((cl)->cl.getIncidentes().stream().anyMatch(i->i.getId()==inc.getId())).collect(Collectors.toList());
	}	
	public List<Cliente> buscarPorServicios(Servicio ser){
		return buscarTodos().stream().filter((cl)->cl.getServicios().stream().anyMatch(s->s.getId()==ser.getId())).collect(Collectors.toList());
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
	public Cliente ActualizarDatos(Cliente cl)
	{ 
		return dao.update(cl);
	}
	
	public void borrarDatos(Cliente cl)
	{
		dao.delete(cl);
	}
}
