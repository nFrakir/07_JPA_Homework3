package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Currency;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

public class CurrencyController {
    private CurrencyDAO currencyDAO;

    public CurrencyController(CurrencyDAO currencyDAO) {
        super();
        this.currencyDAO = currencyDAO;
    }

    public CurrencyController() {
        super();
    }

    public CurrencyDAO getCurrencyDAO() {
        return currencyDAO;
    }

    public void setCurrencyDAO(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    public List<Currency> getAllCurrencies() throws NoResultException {
        return currencyDAO.getAllCurrencies();
    }

    public Currency getBaseCurrency(String abbr) throws NoResultException, NonUniqueResultException {
        return currencyDAO.getBaseCurrency(abbr);
    }

    public Currency getCurrencyById(int id) throws NoResultException, NonUniqueResultException {
        return currencyDAO.getCurrencyById(id);
    }
}
