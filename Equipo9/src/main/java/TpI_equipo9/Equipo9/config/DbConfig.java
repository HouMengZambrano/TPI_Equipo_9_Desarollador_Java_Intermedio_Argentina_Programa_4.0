package TpI_equipo9.Equipo9.config;


import javax.persistence.*;

public class DbConfig {
	static EntityManagerFactory factory=null;
	
	public static EntityManager getEntityManager()
	{
			if(factory==null)
				factory=Persistence.createEntityManagerFactory("JPA_PU");
			EntityManager manager= factory.createEntityManager();
			return manager;
	}

}
