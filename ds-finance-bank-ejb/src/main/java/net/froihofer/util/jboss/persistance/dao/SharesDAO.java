package net.froihofer.util.jboss.persistance.dao;

import net.froihofer.util.jboss.persistance.entity.Shares;

import javax.persistence.*;
import java.util.List;

public class SharesDAO {


    @PersistenceContext(unitName = "ds-finance-bank-depotunit")

    private EntityManager entityManager;

    public SharesDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ds-finance-bank-depotunit");
        entityManager = emf.createEntityManager();
    }

    public List<Shares> findByStockName(String stockName) {
        String jpql = "SELECT s FROM Shares s WHERE s.stockName = :stockName";
        TypedQuery<Shares> query = entityManager.createQuery(jpql, Shares.class);
        query.setParameter("stockName", stockName);
        return query.getResultList();
    }

    public Shares findById(String id) {
        return entityManager.find(Shares.class, id);
    }

    public void persist(Shares shares) {
        entityManager.persist(shares);
    }

    public Shares merge(Shares shares) {
        return entityManager.merge(shares);
    }
}
