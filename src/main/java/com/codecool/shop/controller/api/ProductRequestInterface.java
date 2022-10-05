package com.codecool.shop.controller.api;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ProductRequestInterface {

    default Product getRequestedProduct(HttpServletRequest req) throws IOException {

        String productId = req.getReader().readLine();
        System.out.println(productId);
        productId = "{\"product_id\":\"5\"}";
        JsonObject jsonObject = new Gson().fromJson(productId, JsonObject.class);
        System.out.println(jsonObject);

        return Initializer.productDataStore.find(jsonObject.get("product_id").getAsInt());
    }

}
