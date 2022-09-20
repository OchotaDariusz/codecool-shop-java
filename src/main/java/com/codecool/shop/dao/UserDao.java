package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {

    void add(User user);
    User getUserById(int id);
    void remove(int id);

}
