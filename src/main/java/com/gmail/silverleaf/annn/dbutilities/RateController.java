package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Currency;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

public class RateController {
    private RateDAO rateDAO;

    public RateController(RateDAO rateDAO) {
        super();
        this.rateDAO = rateDAO;
    }

    public RateController() {
        super();
    }

    public Double getCurrencyRate(int from_currency, int to_currency) throws NonUniqueResultException, NoResultException {
        return rateDAO.getCurrencyRate(from_currency, to_currency);
    }

    public Double getCurrencyRate(Currency from_currency, Currency to_currency) throws NonUniqueResultException, NoResultException {
        return rateDAO.getCurrencyRate(from_currency, to_currency);
    }
}
