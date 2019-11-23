package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Billing;
import com.gmail.silverleaf.annn.dbobjects.Currency;
import com.gmail.silverleaf.annn.dbobjects.User;
import com.gmail.silverleaf.annn.exceptions.NotEnoughMoneyException;

import javax.persistence.*;
import java.util.List;

public class BillingDAOJpaImplementation implements BillingDAO {
    private static final String GET_USER_BILLINGS = "select c from Billing c where user = :user";
    private static final String GET_USER_CURRENCY_BILLING = "select c from Billing c where user = :user and currency = :currency";
    private EntityManager em;

    @Override
    public List<Billing> getUserBillings(User user) throws NoResultException {
        Query query = prepareQuery(GET_USER_BILLINGS);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public Billing getUserCurrencyBilling(User user, Currency currency) throws NoResultException, NonUniqueResultException {
        Query query = prepareQuery(GET_USER_CURRENCY_BILLING);
        query.setParameter("user", user);
        query.setParameter("currency", currency);
        return (Billing) query.getSingleResult();
    }

    @Override
    public void addMoney(Double sum, User user, Currency currency) throws PersistenceException, NotEnoughMoneyException {
        Billing billing = getUserCurrencyBilling(user, currency);
        if ((billing.getTotal() + sum) < 0) {
            throw new NotEnoughMoneyException();
        }
        em.getTransaction().begin();
        try {
            Double total = billing.getTotal();
            billing.setTotal(total + sum);
            em.persist(billing);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Query prepareQuery(String queryStr) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Study");
        em = emf.createEntityManager();
        return em.createQuery(queryStr, Billing.class);
    }
}
