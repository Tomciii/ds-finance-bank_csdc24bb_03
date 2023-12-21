package net.froihofer.util.jboss.persistance.dao;

import net.froihofer.util.jboss.persistance.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class EmployeeDAO {

    @PersistenceContext(unitName = "ds-finance-bank-depotunit")

    private EntityManager entityManager;

    public EmployeeDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ds-finance-bank-depotunit");
        entityManager = emf.createEntityManager();
    }

    public void persist(Employee employee) {
        entityManager.persist(employee);
    }
}
