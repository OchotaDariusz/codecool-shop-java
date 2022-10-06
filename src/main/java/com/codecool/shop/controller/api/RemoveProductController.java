package com.codecool.shop.controller.api;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductInCart;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/cart/remove")
public class RemoveProductController extends HttpServlet implements ProductRequestInterface {
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Product requestedProduct = getRequestedProduct(req);
            Order order = new OrderService(Initializer.orderDataStore).getOrderByUserId(1);
            new CartService(Initializer.cartDataStore).removeAllProductOfAKind(requestedProduct, order);
        } catch (IOException e) {
            logger.error("Threw a IOException in RemoveProductController::doPostMethod, full stack trace follows:", e);

        }
    }

}
