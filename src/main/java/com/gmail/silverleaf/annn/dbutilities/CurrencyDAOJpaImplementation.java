package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Currency;

import javax.persistence.*;
import java.util.List;

public class CurrencyDAOJpaImplementation implements CurrencyDAO {
    private static final String GET_ALL_CURRENCIES = "select c from Currency c";
    private static final String GET_BASE_CURRENCY = "select c from Currency c where c.abbreviation = :abbreviation";

    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public List<Currency> getAllCurrencies() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_ALL_CURRENCIES, Currency.class);
        return query.getResultList();
    }

    @Override
    public Currency getBaseCurrency(String abbr) throws NoResultException, NonUniqueResultException {
        createEntityManager();
        Query query = em.createQuery(GET_BASE_CURRENCY, Currency.class);
        query.setParameter("abbreviation", abbr);
        return (Currency) query.getSingleResult();
    }

    @Override
    public Currency getCurrencyById(int id) throws NoResultException, NonUniqueResultException {
        createEntityManager();
        return em.find(Currency.class, id);
    }

    private void createEntityManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("JPA_Study");
            em = emf.createEntityManager();
        }
    }
}
