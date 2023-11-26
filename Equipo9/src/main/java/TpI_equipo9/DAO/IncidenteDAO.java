package TpI_equipo9.DAO;

import TpI_equipo9.Modelos.Incidente;

public class IncidenteDAO extends JpaDAO<Incidente> {
	public IncidenteDAO()
	{
		setClase(Incidente.class);
	}
	
}
