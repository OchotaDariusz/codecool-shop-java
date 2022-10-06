package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ProductInCart extends BaseModel{

    private int productId;
    private int orderId;



    public ProductInCart(int orderId, int productId){
        this.orderId = orderId;
        this.productId = productId;
    }

}
