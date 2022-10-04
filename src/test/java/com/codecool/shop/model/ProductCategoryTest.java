package com.codecool.shop.model;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class ProductCategoryTest {

    @Test
    public void newProductCategoryIsNotNull(){
        // given when
        ProductCategory productCategory = new ProductCategory("name", "department", "desc");
        // then
        assertThat(productCategory, notNullValue());
    }

    @Test
    public void productListIncreaseAfterAddProduct(){
        // given when
        ProductCategory productCategory = new ProductCategory("name", "department", "desc");
        Supplier supplier = new Supplier("lenovo", "desc");
        Product product = new Product("name", BigDecimal.valueOf(199), "USD","desc", productCategory, supplier );
        //then
        assertThat(productCategory.getProducts(), hasSize(1));
    }
}