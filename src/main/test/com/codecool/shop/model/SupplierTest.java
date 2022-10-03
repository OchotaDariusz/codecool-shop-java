package com.codecool.shop.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;

class SupplierTest {

    @Test
    public void test_add_2_Products() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("1"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        Product product2 = new Product("Name", new BigDecimal("1"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        Supplier supplier = new Supplier("Name", "desc");
        supplier.addProduct(product);
        supplier.addProduct(product2);
        Assertions.assertEquals(supplier.getProducts().size(), 2);
    }

}