package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductInCartDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {
    private Order order;
    private final OrderDao orderDao;
    private static final Logger logger = LogManager.getLogger();

    private final ProductInCartDao CART_DATA_STORE;

    
    
    public PaymentController(){
        this.orderDao = Initializer.orderDataStore;
        this.CART_DATA_STORE = Initializer.cartDataStore;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.order = new OrderService(orderDao).getOrderByUserId(1);
            new CartService(CART_DATA_STORE).updateCartInOrder(this.order);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("payment/index.html", initContext(req, resp), resp.getWriter());
        } catch (IOException e) {
            logger.error("Threw an IOException in PaymentController::doGetMethod, full stack trace follows:", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            CART_DATA_STORE.emptyCart(this.order);
            OrderService service = new OrderService(Initializer.orderDataStore);
            service.checkoutOrder(new Gson().fromJson(req.getReader().readLine(), JsonObject.class));
        } catch (IOException e) {
            logger.error("Threw an IOException in PaymentController::doPostMethod, full stack trace follows:", e);
        }
    }

    private WebContext initContext(HttpServletRequest req, HttpServletResponse resp) {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("products", this.order.getCart().keySet());
        context.setVariable("productsAmount", this.order.getCart());
        context.setVariable("orderSum", this.order.getAmount());
        context.setVariable("numberOfProducts", this.order.getNumberOfItemsInCart());
        return context;
    }

}

