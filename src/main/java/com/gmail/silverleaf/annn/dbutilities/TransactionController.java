package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.Billing;
import com.gmail.silverleaf.annn.dbobjects.Transaction;
import com.gmail.silverleaf.annn.dbobjects.User;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class TransactionController {
    private TransactionDAO transactionDAO;

    public TransactionController(TransactionDAO transactionDAO) {
        super();
        this.transactionDAO = transactionDAO;
    }

    public TransactionController() {
        super();
    }

    public TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }

    public void setTransactionDAO(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public List<Transaction> getUserTransactions(User user) throws NoResultException {
        return transactionDAO.getUserTransactions(user);
    }

    public void addTransaction(User user, Billing billing, Double total) throws PersistenceException {
        transactionDAO.addTransaction(user, billing, total);
    }
}
