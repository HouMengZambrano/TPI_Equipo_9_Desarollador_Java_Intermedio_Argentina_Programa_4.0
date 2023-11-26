package TpI_equipo9.DAO;
import TpI_equipo9.Modelos.Especialidad;

public class EspecialidadDAO extends JpaDAO<Especialidad> {
	public EspecialidadDAO()
	{
		setClase(Especialidad.class);
	}

}
