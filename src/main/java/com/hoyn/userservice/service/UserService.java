package com.hoyn.userservice.service;

import com.hoyn.userservice.model.User;
import java.util.List;

/**
 * Created by dennyma on 10/10/2016.
 */
public interface UserService {
    User findById(long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    void deleteAllUsers();

    public boolean isUserExist(User user);
}
