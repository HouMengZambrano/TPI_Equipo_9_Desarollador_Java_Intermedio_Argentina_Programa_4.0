package TpI_equipo9.Equipo9.DAO;

import TpI_equipo9.Equipo9.Modelos.Tecnico;

public class TecnicoDAO extends JpaDAO<Tecnico> {
	public TecnicoDAO()
	{
		setClase(Tecnico.class);
	}
	

}
