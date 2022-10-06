package com.codecool.shop.controller.api;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.model.User;
import com.codecool.shop.service.UserService;

import static com.codecool.shop.controller.api.auth.Auth.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/api/login")
public class LoginController extends HttpServlet implements ProductRequestInterface {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        String loginUsername = req.getParameter("loginUsername");
        String loginPassword = req.getParameter("loginPassword");

        User user = new UserService(Initializer.userDataStore).getUserByUsername(loginUsername);
        if (user != null) {
            System.out.println("Found user");
            if (verifyPasswordHash(loginPassword, user.getPassword())) {
                HttpSession session = req.getSession();
                session.setAttribute("username", loginUsername);
            }
        } else {
            System.out.println("User not found");
        }

        try {
            resp.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
