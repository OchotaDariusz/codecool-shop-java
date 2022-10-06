package com.codecool.shop.controller.api;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductInCart;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.OrderService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/cart/increase")
public class IncreaseProductAmountController extends HttpServlet implements ProductRequestInterface {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Product requestedProduct = getRequestedProduct(req);
            Order order = new OrderService(Initializer.orderDataStore).getOrderByUserId(1);
            ProductInCart pic = new ProductInCart(order.getId(), requestedProduct.getId());
            new CartService(Initializer.cartDataStore).increaseProductInCart(requestedProduct, order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
