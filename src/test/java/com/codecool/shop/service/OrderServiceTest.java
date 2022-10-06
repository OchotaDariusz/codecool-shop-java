package com.codecool.shop.service;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void getOrderByUserId() {
        int userId = 1;
        OrderDao mockOrderDao = mock(OrderDao.class);
        mockOrderDao.createNewOrder(userId);
        Order order = new Order(userId);
        assertThat(order.getUserId(), is(userId));

    }
}