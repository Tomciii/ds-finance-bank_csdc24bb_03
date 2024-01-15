package net.froihofer.util.jboss.persistance.dao;

import net.froihofer.util.jboss.persistance.entity.Customer;

import javax.crypto.Cipher;
import javax.persistence.*;

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

    public boolean userExists(String username){
        String jpql = "SELECT s FROM Customer s WHERE s.username = :username";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        query.setParameter("username", username);
        return !query.getResultList().isEmpty();
    }

    public Customer findByUsername(String username){
        String jpql = "SELECT s FROM Customer s WHERE s.username = :username";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        query.setParameter("username", username);

        if (query.getResultList().isEmpty()) {
            return null;
        }

        return query.getSingleResult();
    }

    public void persist(Customer customer) {
        entityManager.getTransaction().begin();
        Customer managedDepot = entityManager.merge(customer);
        entityManager.persist(managedDepot);
        entityManager.getTransaction().commit();
    }
}
