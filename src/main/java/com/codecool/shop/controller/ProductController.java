package com.codecool.shop.controller;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    private final ProductInCartDao CART_DATA_STORE;
    private Order order;
    private final ProductDao PRODUCT_DATA_STORE;
    private final ProductService PRODUCT_SERVICE;
    private final OrderDao ORDER_DATA_STORE;
    private static final Logger logger = LogManager.getLogger();


    public ProductController(){
        this.PRODUCT_DATA_STORE = Initializer.productDataStore;
        this.ORDER_DATA_STORE = Initializer.orderDataStore;
        this.PRODUCT_SERVICE = new ProductService(
                PRODUCT_DATA_STORE,
                Initializer.productCategoryDataStore,
                Initializer.supplierDataStore);
        this.CART_DATA_STORE = Initializer.cartDataStore;
    }


    private WebContext setupWebContext(HttpServletRequest req, HttpServletResponse resp) {
        String category = req.getParameter("category");
        String supplier = req.getParameter("supplier");
        Map<String, String> defaultCategory = new HashMap<>();
        defaultCategory.put("name", "All categories");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        if (category == null && supplier == null) {
            setupContextVariables(
                    context,
                    defaultCategory,
                    null,
                    this.PRODUCT_DATA_STORE.getAll()
            );
        } else if (category != null && supplier == null) {
            setupContextVariables(
                    context,
                    this.PRODUCT_SERVICE.getProductCategory(Integer.parseInt(category)),
                    null,
                    this.PRODUCT_SERVICE.getProductsForCategory(Integer.parseInt(category))
            );
        } else if (category == null) {
            setupContextVariables(
                    context,
                    defaultCategory,
                    this.PRODUCT_SERVICE.getSupplier(Integer.parseInt(supplier)),
                    this.PRODUCT_SERVICE.getProductsForSupplier(Integer.parseInt(supplier))
            );
        }
        setupContextSessionVariables(req.getSession(), context);
        return context;
    }

    private void setupContextSessionVariables(HttpSession session, WebContext context) {
        if (session.getAttribute("username") != null) context.setVariable("loggedIn", true);
        else context.setVariable("loggedIn", false);
    }

    private void setupContextVariables(WebContext context, Object category, Supplier supplier, List<Product> products) {
        context.setVariable("category", category);
        context.setVariable("supplier", supplier);
        context.setVariable("products", products);
        context.setVariable("categories", this.PRODUCT_SERVICE.getAllCategories());
        context.setVariable("suppliers", this.PRODUCT_SERVICE.getAllSuppliers());
        context.setVariable("amountOfProductsInCart", getAmountOfProductsInCart());
    }

    private int getAmountOfProductsInCart() {
        int amountOfProductsInCart = 0;
        if (cartIsNotEmptyAndOrderExists(order)) {
            for (Product product : order.getCart().keySet()) {
                amountOfProductsInCart += order.getCart().get(product);
            }
        }
        return amountOfProductsInCart;
    }

    private static boolean cartIsNotEmptyAndOrderExists(Order order) {
        return order.getCart().size() != 0 && order.getOrderStatus() == Order.OrderStatus.NEW;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.order = new OrderService(ORDER_DATA_STORE).getOrderByUserId(1);
            new CartService(CART_DATA_STORE).updateCartInOrder(this.order);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            engine.process("product/index.html", setupWebContext(req, resp), resp.getWriter());
        } catch (IOException e) {
            logger.error("Threw a IOException in ProductController::doGetMethod, full stack trace follows:", e);
        }
    }


}
