package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.User;

import javax.persistence.*;
import java.util.List;

public class UserDAOJpaImplementation implements UserDAO {
    private static final String GET_ALL_USERS = "select c from User c";
    private EntityManagerFactory emf;
    private EntityManager em = null;

    @Override
    public List<User> getAllUsers() throws NoResultException {
        createEntityManager();
        Query query = em.createQuery(GET_ALL_USERS, User.class);

        return query.getResultList();
    }

    @Override
    public User getUserById(int user_id) throws NoResultException, NonUniqueResultException {
        createEntityManager();
        return em.find(User.class, user_id);
    }

    private void createEntityManager() {
        if (em == null) {
            emf = Persistence.createEntityManagerFactory("JPA_Study");
            em = emf.createEntityManager();
        }
    }
}
