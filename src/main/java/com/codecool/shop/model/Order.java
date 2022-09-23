package com.codecool.shop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Order extends BaseModel {

    private BigDecimal amount;
    private OrderStatus status;
    private int userId;
    private int itemsInCart;
    private LocalDateTime date;
    //storing payment data
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String address;
    private String address2;
    private String country;
    private String city;
    private String zip;
    private String cardName;
    private String cardNumber;
    private String cardExpiration;
    private String cardCvv;
    private Map<Product, Integer> cart = new HashMap<>();

    public Order(int userId) {
        this.amount = new BigDecimal(0);
        this.userId = userId;
        this.status = OrderStatus.ORDERED;
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Order{" +
                "amount=" + amount +
                ", status=" + status +
                ", userId=" + userId +
                ", date=" + date +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpiration='" + cardExpiration + '\'' +
                ", cardCvv='" + cardCvv + '\'' +
                ", cart=" + cart +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardExpiration(String cardExpiration) {
        this.cardExpiration = cardExpiration;
    }

    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
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

    public int getUserId() {
        return userId;
    }

    public void addProductToCart(Product product) {
        if (cart.containsKey(product)) {
            increasProductQuantity(product);
        } else {
            cart.put(product, 1);
            amount = amount.add(product.getDefaultPrice());
        }
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
                amount = amount.subtract(product.getDefaultPrice());
            }
        }
    }

    public void increasProductQuantity(Product product) {
        int numberOfProducts = cart.get(product);
        cart.put(product, numberOfProducts + 1);
        amount = amount.add(product.getDefaultPrice());
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public int getNumberOfItemsInCart() {
        for (Integer num : cart.values()) {
            itemsInCart += num;
        }
        return itemsInCart;
    }

    public enum OrderStatus {
        ORDERED,
        PAID,
        SHIPPED;

    }
}