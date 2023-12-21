package net.froihofer.util.jboss.persistance.dao;

import net.froihofer.util.jboss.persistance.entity.Customer;
import net.froihofer.util.jboss.persistance.entity.Shares;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class CustomerDAO {
    @PersistenceContext(unitName = "ds-finance-bank-depotunit")

    private EntityManager entityManager;

    public CustomerDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ds-finance-bank-depotunit");
        entityManager = emf.createEntityManager();
    }

    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }

    public void persist(Customer customer) {
        entityManager.getTransaction().begin();
        Customer managedDepot = entityManager.merge(customer);
        entityManager.persist(managedDepot);
        entityManager.getTransaction().commit();
    }
}
