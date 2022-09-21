package com.codecool.shop.controller.api;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CurrentCartServlet", urlPatterns = "/apiCurrentCart")
public class CurrentCartController extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String allInfo = "{\n" +
                "  \"allitems\": [\n" +
                "    {\n" +
                "      \"name\": \"item1\",\n" +
                "      \"description\": \"very nice item\",\n" +
                "      \"price\": 10\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"item2\",\n" +
                "      \"description\": \"very nice item\",\n" +
                "      \"price\": 20\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"item1\",\n" +
                "      \"description\": \"very nice item\",\n" +
                "      \"price\": 30\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(allInfo);

    }
}

