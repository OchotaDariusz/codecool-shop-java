package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoMem implements UserDao {

    private List<User> users = new ArrayList<>();
    //private static UserDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    public UserDaoMem() {
    }

    /*
    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

     */

    @Override
    public void add(User user) {
        user.setId(users.size() + 1);
        users.add(user);
    }

    @Override
    public User find(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User find(String username) {
        return users.stream()
                .filter(u -> Objects.equals(u.getName(), username))
                .findFirst()
                .orElse(null);
    }

}
