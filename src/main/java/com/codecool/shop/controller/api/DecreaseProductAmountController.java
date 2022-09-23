package com.codecool.shop.controller.api;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/cart/decrease")
public class DecreaseProductAmountController extends HttpServlet implements ProductRequestInterface {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product product = getRequestedProduct(req);

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        Order order = orderDataStore.getOrderByUserId(1); // id zamowienia Tomka

        order.decreaseProductQuantity(product);

    }

}
