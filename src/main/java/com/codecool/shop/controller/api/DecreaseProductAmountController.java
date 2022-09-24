package com.codecool.shop.controller.api;

import com.codecool.shop.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/cart/decrease")
public class DecreaseProductAmountController extends HttpServlet implements ProductRequestInterface {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            new OrderService().getOrderByUserId(1).decreaseProductQuantity(getRequestedProduct(req));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
