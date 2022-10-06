package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

public interface OrderDao {

    Order createNewOrder(int userId);
    Order findByUserId(int userId);
    void finaliseOrder(Order order);

}
