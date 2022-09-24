package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.User;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = UserDaoMem.getInstance();
    }

    public User getUserById(int id) {
        return this.userDao.find(id);
    }
}
