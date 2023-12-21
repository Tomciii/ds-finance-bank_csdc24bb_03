package net.froihofer.util.jboss.persistance.dao;

import net.froihofer.util.jboss.persistance.entity.Bank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class BankDAO {
    @PersistenceContext(unitName = "ds-finance-bank-depotunit")

    private EntityManager entityManager;

    public BankDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ds-finance-bank-depotunit");
        entityManager = emf.createEntityManager();
    }

    public Bank findById(int id) {
        return entityManager.find(Bank.class, id);
    }

    public void persist(Bank bank) {
        entityManager.getTransaction().begin();
        Bank managedBank = entityManager.merge(bank);
        entityManager.persist(managedBank);
        entityManager.getTransaction().commit();
    }
}
