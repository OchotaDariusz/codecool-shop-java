package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.List;

public interface ProductInCartDao {

    void addProduct(Product product, Order order);
    void removeProduct(Product product, Order order);

    void removeProductsOfGivenType(Product product, Order order);

    List<Integer> getProductIdsFromCart(Order order);
}
