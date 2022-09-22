package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


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
        context.setVariable("numberOfProducts", order.getNumberOfItemsInCart());

        engine.process("payment/index.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        OrderDao od = OrderDaoMem.getInstance();
        Order currentOrder = od.getOrderByUserId(1);

        currentOrder.setFirstName(req.getParameter("firstName")); ;
        currentOrder.setLastName(req.getParameter("lastName"));
        currentOrder.setUserName(req.getParameter("username"));
        currentOrder.setEmail(req.getParameter("email"));
        currentOrder.setAddress(req.getParameter("address"));
        currentOrder.setAddress2(req.getParameter("address2"));
        currentOrder.setCountry(req.getParameter("country"));
        currentOrder.setCity(req.getParameter("city"));
        currentOrder.setZip(req.getParameter("zip"));
        currentOrder.setCardName(req.getParameter("cc-name"));
        currentOrder.setCardNumber(req.getParameter("cc-number"));
        currentOrder.setCardExpiration(req.getParameter("cc-expiration"));
        currentOrder.setCardCvv(req.getParameter("cc-cvv"));
        currentOrder.setOrderStatus(Order.OrderStatus.PAID);

        System.out.println("POST request received");
        System.out.println(currentOrder.toString());

        PrintWriter out = resp.getWriter();
        out.println("Thank you for your order!");



    }

}

