package net.froihofer.util.jboss.persistance.dao;

import net.froihofer.util.jboss.persistance.entity.Customer;
import net.froihofer.util.jboss.persistance.entity.Employee;
import net.froihofer.util.jboss.persistance.entity.Shares;

import javax.persistence.*;

public class EmployeeDAO {

    @PersistenceContext(unitName = "ds-finance-bank-depotunit")

    private EntityManager entityManager;

    public EmployeeDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ds-finance-bank-depotunit");
        entityManager = emf.createEntityManager();
    }

    public boolean findByUsername(String username){
        String jpql = "SELECT s FROM Employee s WHERE s.username = :username";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("username", username);
        //System.out.println(query.getResultList());
        return !query.getResultList().isEmpty();

    }

    public void persist(Employee employee) {
        entityManager.persist(employee);
    }
}
