package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        OrderDao orderDataStore = OrderDaoMem.getInstance();
        Order order = orderDataStore.getOrderByUserId(1);

        context.setVariable("products", order.getCart().keySet());
        context.setVariable("productsAmount", order.getCart());
        context.setVariable("orderSum", order.getAmount());

        engine.process("payment/index.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OrderDao od = OrderDaoMem.getInstance();
        Order currentOrder = od.getOrderByUserId(1);

        String paymentInfo = req.getReader().readLine();
        JsonObject jsonObject = new Gson().fromJson(paymentInfo, JsonObject.class);

        currentOrder.setFirstName(jsonObject.get("firstName").getAsString());
        currentOrder.setLastName(jsonObject.get("lastName").getAsString());
        currentOrder.setUserName(jsonObject.get("username").getAsString());
        currentOrder.setEmail(jsonObject.get("email").getAsString());
        currentOrder.setAddress(jsonObject.get("address").getAsString());
        currentOrder.setAddress2(jsonObject.get("address2").getAsString());
        currentOrder.setCountry(jsonObject.get("country").getAsString());
        currentOrder.setCity(jsonObject.get("city").getAsString());
        currentOrder.setZip(jsonObject.get("zip").getAsString());
        currentOrder.setCardName(jsonObject.get("ccname").getAsString());
        currentOrder.setCardNumber(jsonObject.get("ccnumber").getAsString());
        currentOrder.setCardExpiration(jsonObject.get("ccexpiration").getAsString());
        currentOrder.setCardCvv(jsonObject.get("cccvv").getAsString());
        currentOrder.setOrderStatus(Order.OrderStatus.PAID);

        System.out.println(jsonObject.get("firstName").getAsString());

    }

}

