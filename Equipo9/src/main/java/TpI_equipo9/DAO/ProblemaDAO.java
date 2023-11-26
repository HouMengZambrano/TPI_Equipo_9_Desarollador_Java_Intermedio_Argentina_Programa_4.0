package TpI_equipo9.DAO;
import TpI_equipo9.Modelos.Problema;

public class ProblemaDAO extends JpaDAO <Problema>{
	public ProblemaDAO()
	{
		setClase(Problema.class);
	}

}
