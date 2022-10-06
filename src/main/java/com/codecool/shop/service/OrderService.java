package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductInCartDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductInCartDaoJdbc;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.List;

public class OrderService {
    private OrderDao orderDao;


    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order getOrderByUserId(int userId) {
        Order order = this.orderDao.findByUserId(userId);
        if (order == null) {
            System.out.println("creating new order");
            return this.orderDao.createNewOrder(userId);
        } else {
            System.out.println("using existing order");
        }
        return order;
    }

}
