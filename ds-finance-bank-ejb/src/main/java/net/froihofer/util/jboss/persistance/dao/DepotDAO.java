package net.froihofer.util.jboss.persistance.dao;

import net.froihofer.util.jboss.persistance.entity.Depot;
import net.froihofer.util.jboss.persistance.entity.Shares;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class DepotDAO {

    @PersistenceContext(unitName = "ds-finance-bank-depotunit")

    private EntityManager entityManager;

    public DepotDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ds-finance-bank-depotunit");
        entityManager = emf.createEntityManager();
    }

    public Depot findById(int id) {
        return entityManager.find(Depot.class, id);
    }

    public void persist(Depot depot) {
        entityManager.getTransaction().begin();
        Depot depot1 = entityManager.merge(depot);
        entityManager.persist(depot1);
        entityManager.getTransaction().commit();
    }

    public Depot merge(Depot depot) {
        return entityManager.merge(depot);
    }
}
