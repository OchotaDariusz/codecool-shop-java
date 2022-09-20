package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        //get chosen category/supplier from url parameters
        String category = req.getParameter("category");
        String supplier = req.getParameter("supplier");

        //setup WebContext
        if(category==null){
            context.setVariable("category_name", "All categories");
            context.setVariable("category", productService.getProductCategory(1));
            System.out.println(productService.getProductCategory(1));
            context.setVariable("products", productDataStore.getAll());
        } else {
            context.setVariable("category name", productService.getProductCategory(Integer.parseInt(category)).getName());
            context.setVariable("category", productService.getProductCategory(Integer.parseInt(category)));
            context.setVariable("products", productService.getProductsForCategory(Integer.parseInt(category)));
        }
        context.setVariable("categories", productService.getAllCategories());
        /*
        if(supplier==null){
            supplier = "0";
        }
        */


        /*
        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("products", productService.getProductsForCategory(1));
        context.setVariable("categories", productService.getAllCategories());
         */
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());
    }

}
