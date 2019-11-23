package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Currency;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface CurrencyDAO {
    public List<Currency> getAllCurrencies() throws NoResultException;
    public Currency getBaseCurrency(String abbr) throws NoResultException, NonUniqueResultException;
    public Currency getCurrencyById(int id) throws NoResultException, NonUniqueResultException;
}
