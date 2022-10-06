package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void test_addProductToCart() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("7"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        Order order = new Order(1);
        order.getCart().put(product, 1);
        order.getAmount().add(product.getDefaultPrice());
        assertThat(order.getNumberOfItemsInCart(), is(1));
    }

    @Test
    void test_removeProductFromCart() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("7"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        Order order = new Order(1);
        order.addProductToCart(product);
        order.getCart().remove(product);
        assertThat(order.getNumberOfItemsInCart(), is(0));
    }

    @Test
    void test_decreaseProductQuantity() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("1"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        Product product2 = new Product("Name", new BigDecimal("2"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        Order order = new Order(1);
        order.addProductToCart(product);
        order.addProductToCart(product2);
        order.removeProductFromCart(product2);
        assertThat(order.getCart().size(), is(1));
    }

    @Test
    void test_increaseProductQuantity() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("7"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        Product product2 = new Product("Name", new BigDecimal("2"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        Order order = new Order(1);
        order.addProductToCart(product);
        order.addProductToCart(product2);
        assertThat(order.getCart().size(), is(2));
    }
}