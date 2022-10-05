package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductInCartDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.OrderService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/cart")
public class OrderController extends HttpServlet {
    private Order order;
    private final OrderDao ORDER_DATA_STORE;
    private final ProductInCartDao CART_DATA_STORE;


    public OrderController(){

        this.ORDER_DATA_STORE = Initializer.orderDataStore;
        this.CART_DATA_STORE = Initializer.cartDataStore;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.order = new OrderService(ORDER_DATA_STORE).getOrderByUserId(1);
            new CartService(CART_DATA_STORE).updateCartInOrder(this.order);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("cart/index.html", initContext(req, resp), resp.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private WebContext initContext(HttpServletRequest req, HttpServletResponse resp) {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("products", this.order.getCart().keySet());
        context.setVariable("productsAmount", this.order.getCart());
        context.setVariable("orderSum", this.order.getAmount());
        return context;
    }

}