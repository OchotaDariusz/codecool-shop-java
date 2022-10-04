package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Test
    void getProductCategory_getCorrectInt_returnCorrectCategory() throws IOException {

        ProductCategoryDao mockPCD = mock(ProductCategoryDao.class);
        ProductDao mockPD = mock(ProductDaoMem.class);
        ProductCategory mockCategory = mock(ProductCategory.class);
        SupplierDao mockSD = mock(SupplierDao.class);
        when(mockPCD.find(anyInt())).thenReturn(mockCategory);

        //public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao, SupplierDao supplierDao)
        ProductService ps = new ProductService(mockPD, mockPCD, mockSD);
        assertEquals(ps.getProductCategory(anyInt()), mockCategory);
    }
}