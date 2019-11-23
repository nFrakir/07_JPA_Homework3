package com.gmail.silverleaf.annn;

import com.gmail.silverleaf.annn.dbobjects.Currency;
import com.gmail.silverleaf.annn.dbobjects.Rate;
import com.gmail.silverleaf.annn.dbobjects.User;
import com.gmail.silverleaf.annn.dbutilities.RateDAO;
import com.gmail.silverleaf.annn.dbutilities.RateDAOJpaImplementation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("start");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA_Study");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("select c from Currency c", Currency.class);
        List<Currency> currencies = query.getResultList();

        for (Currency element: currencies) {
            System.out.println(element);
        }
    }
}
