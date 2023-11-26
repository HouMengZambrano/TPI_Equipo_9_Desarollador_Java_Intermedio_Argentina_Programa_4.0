package TpI_equipo9.Equipo9.DAO;

import TpI_equipo9.Equipo9.Modelos.Cliente;

public class ClienteDAO extends JpaDAO<Cliente>{
	
	public ClienteDAO()
	{
		setClase(Cliente.class);
	}

}
