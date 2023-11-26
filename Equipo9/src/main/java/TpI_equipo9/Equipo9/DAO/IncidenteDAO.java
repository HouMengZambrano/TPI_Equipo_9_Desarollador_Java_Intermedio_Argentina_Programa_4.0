package TpI_equipo9.Equipo9.DAO;

import TpI_equipo9.Equipo9.Modelos.Incidente;

public class IncidenteDAO extends JpaDAO<Incidente> {
	public IncidenteDAO()
	{
		setClase(Incidente.class);
	}

}
