package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {

    private List<Order> data = new ArrayList<>();
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Order order) {
        order.setId(data.size() + 1);
        data.add(order);
    }

    @Override
    public Order getOrderByUserId(int userId) {
        Order order = data.stream().filter(t -> t.getUserId() == userId).findFirst().orElse(null);
        if (order == null || order.getOrderStatus() == Order.OrderStatus.PAID || order.getOrderStatus() == Order.OrderStatus.SHIPPED) {
            order = new Order(userId);
        }
        add(order);
        return order;
    }

    @Override
    public void remove(int id) {
        data.remove(getOrderByUserId(id));
    }


}
