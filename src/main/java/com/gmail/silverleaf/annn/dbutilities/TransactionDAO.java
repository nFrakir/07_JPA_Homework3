package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Billing;
import com.gmail.silverleaf.annn.dbobjects.Transaction;
import com.gmail.silverleaf.annn.dbobjects.User;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public interface TransactionDAO {
    public List<Transaction> getUserTransactions(User user) throws NoResultException;
    public void addTransaction(User user, Billing billing, Double total) throws PersistenceException;
}
