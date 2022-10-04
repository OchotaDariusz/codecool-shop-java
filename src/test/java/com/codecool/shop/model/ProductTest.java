package com.codecool.shop.model;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductTest {

    @Test
    public void test_getDefaultPrice() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("7"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        BigDecimal defaultBig = new BigDecimal("7");
        assertEquals(0, product.getDefaultPrice().compareTo(defaultBig));
    }

    @Test
    public void test_setDefaultPrice() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("3"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        BigDecimal newBig = new BigDecimal("7");
        product.setDefaultPrice(newBig);
        assertEquals(0, product.getDefaultPrice().compareTo(newBig));
    }

    @Test
    public void test_getDefaultCurrency() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("3"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        assertEquals(product.getDefaultCurrency().getCurrencyCode(), "USD");
    }

    @Test
    public void test_setDefaultCurrency() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("3"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        product.setDefaultCurrency(Currency.getInstance("EUR"));
        assertEquals(product.getDefaultCurrency().getCurrencyCode(), "EUR");
    }

    @Test
    public void test_getPriceAsString() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("3"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        assertEquals(product.getPriceAsString(), "3 USD");
    }

    @Test
    public void test_setPrice() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("3"), "USD", "Fantastic.", productCategoryMock, supplierMock);
        BigDecimal newBig = new BigDecimal("7");
        product.setPrice(newBig, "EUR");

        // Assert value
        assertEquals(0, product.getPrice().compareTo(newBig));
        // Assert currency
        assertEquals(product.getDefaultCurrency().toString(), "EUR");
    }

    @Test
    public void test_getProductCategory() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory productCategory = new ProductCategory("laptop", "Hardware","No desc.");
        Product product = new Product("Name", new BigDecimal("3"), "USD", "Fantastic.", productCategory, supplierMock);
        assertEquals(product.getProductCategory().getName(), "laptop");
    }

    @Test
    public void test_setProductCategory() {
        Supplier supplierMock = mock(Supplier.class);
        ProductCategory oldProductCategory = new ProductCategory("phone", "Hardware","No desc.");
        ProductCategory newProductCategory = new ProductCategory("laptop", "Hardware","No desc.");
        Product product = new Product("Name", new BigDecimal("3"), "USD", "Fantastic.", oldProductCategory, supplierMock);

        // Assert old category
        assertEquals(product.getProductCategory().getName(), "phone");
        // Assert new category
        product.setProductCategory(newProductCategory);
        assertEquals(product.getProductCategory().getName(), "laptop");
    }

    @Test
    public void test_getSupplier() {
        Supplier supplier = new Supplier("Asus", "Crap.");
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("3"), "USD", "Fantastic.", productCategoryMock, supplier);
        assertEquals(product.getSupplier().getName(), "Asus");
    }

    @Test
    void test_setSupplier() {
        Supplier oldSupplier = new Supplier("Asus", "Crap");
        Supplier newSupplier = new Supplier("Lenovo", "Another crap");
        ProductCategory productCategoryMock = mock(ProductCategory.class);
        Product product = new Product("Name", new BigDecimal("3"), "USD", "Fantastic.", productCategoryMock, oldSupplier);

        // Assert old supplier
        assertEquals(product.getSupplier().getName(), "Asus");
        // Assert new supplier
        product.setSupplier(newSupplier);
        assertEquals(product.getSupplier().getName(), "Lenovo");
    }
}