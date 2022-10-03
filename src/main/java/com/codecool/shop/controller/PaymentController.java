package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.OrderService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
    private final OrderDao ORDER_DATA_STORE;
    
    
    public PaymentController(){
        this.ORDER_DATA_STORE = Initializer.orderDataStore;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.order = new OrderService(ORDER_DATA_STORE).getOrderByUserId(1);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("payment/index.html", initContext(req, resp), resp.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            fillOrderDetails(new Gson().fromJson(req.getReader().readLine(), JsonObject.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillOrderDetails(JsonObject jsonObject) {
        this.order.setFirstName(jsonObject.get("firstName").getAsString());
        this.order.setLastName(jsonObject.get("lastName").getAsString());
        this.order.setUserName(jsonObject.get("username").getAsString());
        this.order.setEmail(jsonObject.get("email").getAsString());
        this.order.setAddress(jsonObject.get("address").getAsString());
        this.order.setAddress2(jsonObject.get("address2").getAsString());
        this.order.setCountry(jsonObject.get("country").getAsString());
        this.order.setCity(jsonObject.get("city").getAsString());
        this.order.setZip(jsonObject.get("zip").getAsString());
        this.order.setCardName(jsonObject.get("ccname").getAsString());
        this.order.setCardNumber(jsonObject.get("ccnumber").getAsString());
        this.order.setCardExpiration(jsonObject.get("ccexpiration").getAsString());
        this.order.setCardCvv(jsonObject.get("cccvv").getAsString());
        this.order.setOrderStatus(Order.OrderStatus.PAID);
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

