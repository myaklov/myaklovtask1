package com.alex.spring.service;

import com.alex.spring.dao.Dao;
import com.alex.spring.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alex on 28.06.2017.
 */
@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Transactional
    public void addUser(User user) {
        this.dao.addUser(user);

    }
    @Transactional
    public void deleteUser(int id) {

        this.dao.deleteUser(id);
    }
    @Transactional
    public void updateUser(User user) {
        this.dao.updateUser(user);
    }
    @Transactional
    public User getUserById(int id) {
        return this.dao.getUserById(id);
    }
    @Transactional
    public List<User> getListUsers() {
        return this.dao.getListUsers();
    }
    @Transactional
    public List<User> findByName(String name) {
        return this.dao.findByName(name);
    }
}
