
import config.DBConfig;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

public class main {
    public static void main(String[] args) {
        EntityManager entityManager = DBConfig.getEntityManager();

//                Configuration configuration = new Configuration();
//                configuration.configure("META-INF/persistence.xml");
//                //configuration.addPackage("TPI_Equipo9/src/main/java/model"); ESTE NO FUNCIONO
//                configuration.addAnnotatedClass(Cliente.class);
//                configuration.addAnnotatedClass(Especialidad.class);
//                configuration.addAnnotatedClass(Incidente.class);
//                configuration.addAnnotatedClass(Problema.class);
//                configuration.addAnnotatedClass(Servicio.class);
//                configuration.addAnnotatedClass(Tecnico.class);
//                configuration.addAnnotatedClass(Test.class);
//
//
//                SessionFactory sessionFactory = configuration.buildSessionFactory();
//
//                Session session = sessionFactory.openSession();
//
//                Transaction transaction = null;
//                try {
//                    transaction = session.beginTransaction();
//                    List<Test> test1 = session.createQuery("SELECT t FROM Test t", Test.class).getResultList();
//                    for (Test t: test1) {
//                        System.out.println(t);
//                    }
//                    transaction.commit();
//                } catch (Exception e) {
//                    if (transaction != null) {
//                        transaction.rollback();
//                    }
//                    e.printStackTrace();
//                } finally {
//                    session.close();
//                    sessionFactory.close();
//                }
//            }
        }
}




