package com.codecool.shop.service;

import com.codecool.shop.dao.ProductInCartDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

public class CartService {

    private ProductInCartDao cartDao;


    public CartService(ProductInCartDao cartDao) {
        this.cartDao = cartDao;
    }
     public void addProductToCart(Product product, Order order){
         System.out.println("Cart service, adding product to cart");
         System.out.println(product);
         System.out.println(order);
        this.cartDao.addProduct(product, order);
     }
}
