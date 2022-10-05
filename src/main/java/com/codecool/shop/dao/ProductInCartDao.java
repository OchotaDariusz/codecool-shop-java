package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

public interface ProductInCartDao {

    void addProduct(Product product, Order order);
    void removeProduct(Product product, Order order);

    void removeProductsOfGivenType(Product product, Order order);

}
