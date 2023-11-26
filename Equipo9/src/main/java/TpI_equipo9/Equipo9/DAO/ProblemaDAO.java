package TpI_equipo9.Equipo9.DAO;
import TpI_equipo9.Equipo9.Modelos.Problema;

public class ProblemaDAO extends JpaDAO <Problema>{
	public ProblemaDAO()
	{
		setClase(Problema.class);
	}

}
