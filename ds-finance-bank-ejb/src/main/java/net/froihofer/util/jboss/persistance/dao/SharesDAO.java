package net.froihofer.util.jboss.persistance.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class SharesDAO {


    @PersistenceContext(unitName = "ds-finance-bank-sharesunit")

    private EntityManager entityManager;

    public SharesDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ds-finance-bank-sharesunit");
        entityManager = emf.createEntityManager();
    }
}