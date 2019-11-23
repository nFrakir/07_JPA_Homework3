package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Billing;
import com.gmail.silverleaf.annn.dbobjects.Currency;
import com.gmail.silverleaf.annn.dbobjects.User;
import com.gmail.silverleaf.annn.exceptions.NotEnoughMoneyException;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public interface BillingDAO {
    public List<Billing> getUserBillings(User user) throws NoResultException;
    public Billing getUserCurrencyBilling(User user, Currency currency) throws NoResultException, NonUniqueResultException;
    public void addMoney(Double sum, User user, Currency currency) throws PersistenceException, NotEnoughMoneyException;
}
