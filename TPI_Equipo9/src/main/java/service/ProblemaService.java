package service;

import model.Problema;
import repository.ProblemaDAO;
import java.util.Collection;


public class ProblemaService implements ServicioDAO<Problema> {

    private ProblemaDAO problemaDAO = new ProblemaDAO();


    @Override
    public Problema findById(Integer id) {
      Problema problema = problemaDAO.find(id);
        if(problema != null){
            return problema;
        }else{
            System.out.println("No se encontro el Problema");
            return null;
        }
    }

    @Override
    public Collection<Problema> findAll() {
        return problemaDAO.findAll();
    }

    @Override
    public void create(Problema problema) {
        problemaDAO.create(problema);
    }

    @Override
    public void update(Problema problema) {
        problemaDAO.update(problema);
    }

    @Override
    public void delete(Problema problema) {
    problemaDAO.delete(problema);
    }
}
