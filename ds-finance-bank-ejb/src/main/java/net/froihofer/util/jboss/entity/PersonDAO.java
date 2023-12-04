package net.froihofer.util.jboss.entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonDAO {

    @PersistenceContext private EntityManager entityManager;

    public Person findById(int svnr) {
        return entityManager.find(Person.class, svnr);
    }

    public void persist(Person person) {
        entityManager.persist(person);
    }

    public void delete(Person person) {
        entityManager.remove(person);
    }

}
