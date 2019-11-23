package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Billing;
import com.gmail.silverleaf.annn.dbobjects.Transaction;
import com.gmail.silverleaf.annn.dbobjects.User;

import javax.persistence.*;
import java.util.List;

public class TransactionDAOJpaImplementation implements TransactionDAO {
    private static final String GET_USER_TRANSACTIONS = "select c from Transaction c where user = :user";

    @Override
    public List<Transaction> getUserTransactions(User user) throws NoResultException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Study");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(GET_USER_TRANSACTIONS, Transaction.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public void addTransaction(User user, Billing billing, Double total) throws PersistenceException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Study");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        try {
            Transaction transaction = new Transaction(total, billing, user);
            em.persist(transaction);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
