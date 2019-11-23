package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.User;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

public class UserController {
    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        super();
        this.userDAO = userDAO;
    }

    public UserController() {
        super();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> getAllUsers() throws NoResultException {
        return userDAO.getAllUsers();
    }

    public User getUserById(int user_id) throws NoResultException, NonUniqueResultException {
        return userDAO.getUserById(user_id);
    }
}
