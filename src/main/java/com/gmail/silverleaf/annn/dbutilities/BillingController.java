package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Billing;
import com.gmail.silverleaf.annn.dbobjects.Currency;
import com.gmail.silverleaf.annn.dbobjects.User;
import com.gmail.silverleaf.annn.exceptions.NotEnoughMoneyException;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class BillingController {
    private BillingDAO billingDAO;

    public BillingController(BillingDAO billingDAO) {
        super();
        this.billingDAO = billingDAO;
    }

    public BillingController() {
        super();
    }

    public BillingDAO getBillingDAO() {
        return billingDAO;
    }

    public void setBillingDAO(BillingDAO billingDAO) {
        this.billingDAO = billingDAO;
    }

    public List<Billing> getUserBillings(User user) throws NoResultException {
        return billingDAO.getUserBillings(user);
    }

    public Billing getUserCurrencyBilling(User user, Currency currency) throws NoResultException, NonUniqueResultException {
        return billingDAO.getUserCurrencyBilling(user, currency);
    }

    public void addMoney(Double sum, User user, Currency currency) throws PersistenceException, NotEnoughMoneyException {
        billingDAO.addMoney(sum, user, currency);
    }
}
