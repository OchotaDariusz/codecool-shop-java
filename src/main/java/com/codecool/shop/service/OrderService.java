package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;

public class OrderService {
    private OrderDao orderDao;

    public OrderService() {
        this.orderDao = OrderDaoMem.getInstance();
    }

    public Order getOrderByUserId(int userId) {
        Order order = this.orderDao.findByUserId(userId);
        if (order == null) return this.orderDao.createNewOrder(userId);
        return order;
    }
}
