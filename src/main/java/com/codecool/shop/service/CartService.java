package com.codecool.shop.service;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ProductInCartDao;
import com.codecool.shop.dao.implementation.ProductInCartDaoJdbc;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.List;

public class CartService {

    private ProductInCartDao cartDao;
    private ProductDao productDatastore;


    public CartService(ProductInCartDao cartDao) {
        this.cartDao = cartDao;
        this.productDatastore = Initializer.productDataStore;
    }
     public void addProductToCart(Product product, Order order){
         System.out.println("Cart service, adding product to cart");
        this.cartDao.addProduct(product, order);
     }

    public void increaseProductInCart(Product product, Order order){
        System.out.println("Cart service, increasing amount of product in cart");
        this.cartDao.addProduct(product, order);
    }

    public void decreaseProductInCart(Product product, Order order){
        System.out.println("Cart service, decreasing amount of product in cart");
        this.cartDao.removeProduct(product, order);
    }


    public void updateCartInOrder(Order order) {
        List<Integer> products = this.cartDao.getProductIdsFromCart(order);
        for(Integer productId: products){
            Product product = productDatastore.find(productId);
            order.addProductToCart(product);
        }
    }
}
