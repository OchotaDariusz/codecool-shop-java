package com.codecool.shop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order extends BaseModel {

    private BigDecimal amount;
    private OrderStatus status;
    private int userId;

    private LocalDateTime date;

    public Order(int userId) {
        this.amount = new BigDecimal(0);
        this.userId = userId;
        this.status = OrderStatus.ORDERED;
        this.date = LocalDateTime.now();
    }

    private enum OrderStatus {
        ORDERED,
        PAID,
        SHIPPED;

    }

    public OrderStatus getOrderStatus() {
        return status;
    }

    public void setOrderStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    private Map<Product, Integer> cart = new HashMap<>();


    public void addProductToCart(Product product) {
        if (cart.containsKey(product)) {
            increasProductQuantity(product);
        } else {
            cart.put(product, 1);
        }
        amount = amount.add(product.getDefaultPrice());
    }

    public void removeProductFromCart(Product product) {
        cart.remove(product);
        amount = amount.subtract(product.getDefaultPrice());
    }

    public void decreaseProductQuantity(Product product) {
        if (cart.containsKey(product)) {
            int numberOfProducts = cart.get(product);
            if (numberOfProducts == 1) {
                removeProductFromCart(product);
            } else {
                cart.put(product, numberOfProducts - 1);
            }
        }
        amount = amount.subtract(product.getDefaultPrice());
    }

    public void increasProductQuantity(Product product) {
        int numberOfProducts = cart.get(product);
        cart.put(product, numberOfProducts + 1);
        amount = amount.add(product.getDefaultPrice());
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }
}
