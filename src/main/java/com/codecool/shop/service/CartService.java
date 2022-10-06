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
        this.cartDao.addProduct(product, order);
        updateCartInOrder(order);
     }

    public void increaseProductInCart(Product product, Order order){
        this.cartDao.addProduct(product, order);
        updateCartInOrder(order);
    }

    public void decreaseProductInCart(Product product, Order order){
        this.cartDao.removeProduct(product, order);
        updateCartInOrder(order);
    }

    public void removeAllProductOfAKind(Product product, Order order){
        this.cartDao.removeProductsOfGivenType(product, order);
        updateCartInOrder(order);
    }

    public void removeAllProducts(Order order){
        this.cartDao.emptyCart(order);
    }


    public void updateCartInOrder(Order order) {
        order.emptyCart();
        List<Integer> products = this.cartDao.getProductIdsFromCart(order);
        for(Integer productId: products){
            Product product = productDatastore.find(productId);
            order.addProductToCartDB(product);
        }
    }
}
