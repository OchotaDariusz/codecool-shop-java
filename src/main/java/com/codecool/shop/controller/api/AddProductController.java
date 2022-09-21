package com.codecool.shop.controller.api;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/cart/add")
public class AddProductController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String productId = req.getReader().readLine();

        JsonObject jsonObject = new Gson().fromJson(productId, JsonObject.class);

        System.out.println(productId); //TODO: extract id from json
        System.out.println(jsonObject.get("product_id").getAsInt());
        // get order object from order dao + call addProductToCart method on that object
        // check if user have opened order if not create new one.
        // get user by user id from current session.


        OrderDao orderDataStore = OrderDaoMem.getInstance();
        Order order = orderDataStore.getOrderById(1); // id zamowienia Tomka

        ProductDao productDataStore = ProductDaoMem.getInstance();
        Product product = productDataStore.find(jsonObject.get("product_id").getAsInt());

        order.addProductToCart(product);

    }

}
