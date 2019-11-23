package com.gmail.silverleaf.annn.dbutilities;

import com.gmail.silverleaf.annn.dbobjects.User;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers() throws NoResultException;
    public User getUserById(int user_id) throws NoResultException, NonUniqueResultException;
}
