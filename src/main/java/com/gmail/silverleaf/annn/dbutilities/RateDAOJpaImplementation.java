package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Currency;
import com.gmail.silverleaf.annn.dbobjects.Rate;

import javax.persistence.*;

public class RateDAOJpaImplementation implements RateDAO {
    private static final String GET_CURRENCY_RATE = "select c from Rate c " +
            "where c.currencyFrom = :from_currency and c.currencyTo = :to_currency";
    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public Double getCurrencyRate(int from_currency, int to_currency) throws NonUniqueResultException, NoResultException {
        createEntityManager();
        Currency cf = em.getReference(Currency.class, from_currency);
        Currency ct = em.getReference(Currency.class, to_currency);
        return getCurrencyRate(cf, ct);
    }

    @Override
    public Double getCurrencyRate(Currency from_currency, Currency to_currency) throws NonUniqueResultException, NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_CURRENCY_RATE, Rate.class);

        query.setParameter("from_currency", from_currency);
        query.setParameter("to_currency", to_currency);

        Rate rate = (Rate) query.getSingleResult();
        return rate.getRate();
    }

    private void createEntityManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("JPA_Study");
            em = emf.createEntityManager();
        }
    }
}
