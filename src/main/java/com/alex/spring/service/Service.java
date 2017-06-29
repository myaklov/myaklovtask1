package com.alex.spring.service;

import com.alex.spring.model.User;

import java.util.List;

/**
 * Created by Alex on 28.06.2017.
 */
public interface Service {
    void addUser(User user);

    void deleteUser(int id);

    void updateUser(User user);

    User getUserById(int id);

    List<User> getListUsers();

    List<User> findByName(String name);
}
