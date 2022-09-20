package com.codecool.shop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order extends BaseModel {



    private BigDecimal amount;
    private OrderStatus status;

    private LocalDateTime date;

    public Order() {
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


    public boolean addProductToCart(Product product) {
        try {
            if (cart.containsKey(product)) {
                int numberOfProducts = cart.get(product);
                cart.put(product, numberOfProducts + 1);
            } else {
                cart.put(product, 1);
            }
            amount = amount.add(product.getDefaultPrice());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeProductFromCart(Product product) {
        try {
            cart.remove(product);
            amount = amount.subtract(product.getDefaultPrice());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean decreaseProductQuantity(Product product) {
        try {
            if (cart.containsKey(product)) {
                int numberOfProducts = cart.get(product);
                if (numberOfProducts == 1) {
                    removeProductFromCart(product);
                } else {
                    cart.put(product, numberOfProducts - 1);
                }
            }
            amount = amount.subtract(product.getDefaultPrice());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean increasProductQuantity(Product product) {
        try {
            int numberOfProducts = cart.get(product);
            cart.put(product, numberOfProducts + 1);
            amount = amount.add(product.getDefaultPrice());
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
