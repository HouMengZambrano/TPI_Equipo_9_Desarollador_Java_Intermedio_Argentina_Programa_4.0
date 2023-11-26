package TpI_equipo9.DAO;
import TpI_equipo9.Modelos.Servicio;
public class ServicioDAO extends JpaDAO<Servicio> {
	
		public ServicioDAO()
		{
			setClase(Servicio.class);
		}

	}

