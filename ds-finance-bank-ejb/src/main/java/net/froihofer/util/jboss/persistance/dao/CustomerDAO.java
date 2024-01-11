package net.froihofer.util.jboss.persistance.dao;

import net.froihofer.util.jboss.persistance.entity.Customer;
import net.froihofer.util.jboss.persistance.entity.Employee;
import net.froihofer.util.jboss.persistance.entity.Shares;

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

    public boolean findByUsername(String username){
        String jpql = "SELECT s FROM Customer s WHERE s.username = :username";
        TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
        query.setParameter("username", username);
        //System.out.println(query.getResultList());
        return !query.getResultList().isEmpty();
    }

    public void persist(Customer customer) {
        entityManager.getTransaction().begin();
        Customer managedDepot = entityManager.merge(customer);
        entityManager.persist(managedDepot);
        entityManager.getTransaction().commit();
    }
}
