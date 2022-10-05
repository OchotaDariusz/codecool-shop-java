package com.codecool.shop.model;

public class User extends BaseModel {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password) {

        super(name);
        this.password = password;
    }
}
