package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Order;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.order = new OrderService().getOrderByUserId(1);

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