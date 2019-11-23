package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Currency;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

public interface RateDAO {
    public Double getCurrencyRate(int form_currency, int to_currency) throws NonUniqueResultException, NoResultException;
    public Double getCurrencyRate(Currency form_currency, Currency to_currency) throws NonUniqueResultException, NoResultException;
}
