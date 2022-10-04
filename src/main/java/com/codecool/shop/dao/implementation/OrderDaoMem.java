package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {

    //private static OrderDaoMem instance = null;
    private List<Order> orders = new ArrayList<>();

    /* A private Constructor prevents any other class from instantiating.
     */
    public OrderDaoMem() {
    }

    /*
    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }
     */

    private void add(Order order) {
        order.setId(orders.size() + 1);
        orders.add(order);
    }

    @Override
    public Order createNewOrder(int userId) {
        Order order = new Order(userId);
        add(order);
        return order;
    }

    @Override
    public Order findByUserId(int userId) {
        return this.orders.stream()
                .filter(o -> o.getUserId() == userId)
                .filter(o -> o.getOrderStatus() == Order.OrderStatus.NEW)
                .findFirst()
                .orElse(null);
    }
}
