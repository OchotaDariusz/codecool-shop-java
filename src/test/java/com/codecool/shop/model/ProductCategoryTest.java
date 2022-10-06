package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {

    @Test
    void test_addProduct() {
        ProductCategory productCategory = new ProductCategory("name", "department", "desc");
        Product productMock = mock(Product.class);
        productCategory.addProduct(productMock);
        assertThat(productCategory.getProducts(), hasSize(1));
    }
}