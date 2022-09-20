package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.HashMap;

public class SupplierDatabase {

    private static HashMap<String, String> allSuppliers;

    private static void addSuppliersToDatabase(){
        allSuppliers.put("Amazon", "Digital content and services");
        allSuppliers.put("Lenovo", "Computers");
        allSuppliers.put("Sony", "TV and multimedia");
        allSuppliers.put("HP", "Computers and printers");
        allSuppliers.put("Apple", "Computers and smartphones");

    }

    public static HashMap getAllSuppliers(){
        addSuppliersToDatabase();
        return allSuppliers;
    }
}

