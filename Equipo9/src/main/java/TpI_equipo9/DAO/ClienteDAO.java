package TpI_equipo9.DAO;



import TpI_equipo9.Modelos.Cliente;

public class ClienteDAO extends JpaDAO<Cliente>{
	
	public ClienteDAO()
	{
		setClase(Cliente.class);
	}

}
