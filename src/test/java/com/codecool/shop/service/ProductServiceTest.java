package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Test
    void getProductCategory_giveCorrectInt_returnCorrectCategory() throws IOException {

        ProductCategoryDao mockPCD = mock(ProductCategoryDao.class);
        ProductDao mockPD = mock(ProductDaoMem.class);
        ProductCategory mockCategory = mock(ProductCategory.class);
        SupplierDao mockSD = mock(SupplierDao.class);
        when(mockPCD.find(anyInt())).thenReturn(mockCategory);

        ProductService ps = new ProductService(mockPD, mockPCD, mockSD);
        assertEquals(ps.getProductCategory(anyInt()), mockCategory);
    }

    @Test
    void getProductsForCategory_giveCorrectInt_returnCorrectListOfCategories() throws IOException {

        ProductCategoryDao mockPCD = mock(ProductCategoryDao.class);
        ProductDao mockPD = mock(ProductDaoMem.class);
        ProductCategory mockCategory = mock(ProductCategory.class);
        SupplierDao mockSD = mock(SupplierDao.class);
        Product mockProduct1 = mock(Product.class);
        Product mockProduct2 = mock(Product.class);
        List<Product> mockList = new ArrayList<>();
        mockList.add(mockProduct1);
        mockList.add(mockProduct2);

        when(mockPCD.find(anyInt())).thenReturn(mockCategory);
        when(mockPD.getBy(mockCategory)).thenReturn(mockList);

        ProductService ps = new ProductService(mockPD, mockPCD, mockSD);
        assertEquals(ps.getProductsForCategory(anyInt()), mockList);
    }

    @Test
    void getSupplier_giveCorrectInt_returnCorrectSupplier() throws IOException {

        ProductCategoryDao mockPCD = mock(ProductCategoryDao.class);
        ProductDao mockPD = mock(ProductDaoMem.class);
        SupplierDao mockSD = mock(SupplierDao.class);
        Supplier mockSupplier = mock(Supplier.class);


        when(mockSD.find(anyInt())).thenReturn(mockSupplier);

        ProductService ps = new ProductService(mockPD, mockPCD, mockSD);
        assertEquals(ps.getSupplier(anyInt()), mockSupplier);
    }

    @Test
    void getProductsForSupplier_giveCorrectInt_returnCorrectListOfProducts() throws IOException {

        ProductCategoryDao mockPCD = mock(ProductCategoryDao.class);
        ProductDao mockPD = mock(ProductDaoMem.class);
        ProductCategory mockCategory = mock(ProductCategory.class);
        SupplierDao mockSD = mock(SupplierDao.class);
        Product mockProduct1 = mock(Product.class);
        Product mockProduct2 = mock(Product.class);
        Supplier mockSupplier = mock(Supplier.class);
        List<Product> mockList = new ArrayList<>();
        mockList.add(mockProduct1);
        mockList.add(mockProduct2);

        when(mockSD.find(anyInt())).thenReturn(mockSupplier);
        when(mockPD.getBy(mockSupplier)).thenReturn(mockList);

        ProductService ps = new ProductService(mockPD, mockPCD, mockSD);
        assertEquals(ps.getProductsForSupplier(anyInt()), mockList);
    }

    @Test
    void getAllCategories_returnCorrectListOfCategories() throws IOException {

        ProductCategoryDao mockPCD = mock(ProductCategoryDao.class);
        ProductDao mockPD = mock(ProductDaoMem.class);
        SupplierDao mockSD = mock(SupplierDao.class);
        ProductCategory mockProductCategory1 = mock(ProductCategory.class);
        ProductCategory mockProductCategory2 = mock(ProductCategory.class);
        List<ProductCategory> mockList = new ArrayList<>();
        mockList.add(mockProductCategory1);
        mockList.add(mockProductCategory2);

        when(mockPCD.getAll()).thenReturn(mockList);

        ProductService ps = new ProductService(mockPD, mockPCD, mockSD);
        assertEquals(ps.getAllCategories(), mockList);
    }

    @Test
    void getAllSuppliers_returnCorrectListOfSuppliers() throws IOException {

        ProductCategoryDao mockPCD = mock(ProductCategoryDao.class);
        ProductDao mockPD = mock(ProductDaoMem.class);
        SupplierDao mockSD = mock(SupplierDao.class);
        Supplier mockSupplier1 = mock(Supplier.class);
        Supplier mockSupplier2 = mock(Supplier.class);
        List<Supplier> mockList = new ArrayList<>();
        mockList.add(mockSupplier1);
        mockList.add(mockSupplier2);

        when(mockSD.getAll()).thenReturn(mockList);

        ProductService ps = new ProductService(mockPD, mockPCD, mockSD);
        assertEquals(ps.getAllSuppliers(), mockList);
    }

}