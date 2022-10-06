package com.codecool.shop.service;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.google.gson.JsonObject;

public class OrderService {
    private OrderDao orderDao;


    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Order getOrderByUserId(int userId) {
        Order order = this.orderDao.findByUserId(userId);
        if (order == null) {
            return this.orderDao.createNewOrder(userId);
        }
        return order;
    }

    public void checkoutOrder(JsonObject jsonObject) {
        Order order = getOrderByUserId(1);
        fillOrderDetails(jsonObject, order);
        orderDao.finaliseOrder(order);
    }

    private void fillOrderDetails(JsonObject jsonObject, Order order) {
        order.setFirstName(jsonObject.get("firstName").getAsString());
        order.setLastName(jsonObject.get("lastName").getAsString());
        order.setUserName(jsonObject.get("username").getAsString());
        order.setEmail(jsonObject.get("email").getAsString());
        order.setAddress(jsonObject.get("address").getAsString());
        order.setAddress2(jsonObject.get("address2").getAsString());
        order.setCountry(jsonObject.get("country").getAsString());
        order.setCity(jsonObject.get("city").getAsString());
        order.setZip(jsonObject.get("zip").getAsString());
        order.setCardName(jsonObject.get("ccname").getAsString());
        order.setCardNumber(jsonObject.get("ccnumber").getAsString());
        order.setCardExpiration(jsonObject.get("ccexpiration").getAsString());
        order.setCardCvv(jsonObject.get("cccvv").getAsString());
        order.setOrderStatus(Order.OrderStatus.PAID);
    }
}
