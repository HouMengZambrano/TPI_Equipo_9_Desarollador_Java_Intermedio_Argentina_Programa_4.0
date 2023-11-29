package TpI_equipo9.DAO;

import TpI_equipo9.Modelos.Tecnico;

public class TecnicoDAO extends JpaDAO<Tecnico> {
	public TecnicoDAO()
	{
		setClase(Tecnico.class);
	}
	

}
