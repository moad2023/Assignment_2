
package Controller.Admin;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceManager {
    private static PersistenceManager instance;
    private EntityManagerFactory entityManagerFactory;

    private PersistenceManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("JavaFX-ProjectPU");
    }

    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    
    public static PersistenceManager getInstance() {
        if (instance == null) {
            instance = new PersistenceManager();
        }
        return instance;
    }

    public boolean persistEntity(Object entity) {
        boolean isgood = true;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (RuntimeException e) {
            isgood = false;
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
        
        return isgood;
    }
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}

