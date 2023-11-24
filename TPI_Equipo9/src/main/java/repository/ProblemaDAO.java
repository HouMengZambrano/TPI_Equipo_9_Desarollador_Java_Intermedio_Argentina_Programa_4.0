package repository;

import model.Problema;

public class ProblemaDAO extends DAO<Problema> {
    public ProblemaDAO() {
        setClase(Problema.class);
    }
}
