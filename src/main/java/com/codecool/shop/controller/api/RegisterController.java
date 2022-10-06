package com.codecool.shop.controller.api;

import com.codecool.shop.config.Initializer;
import com.codecool.shop.model.User;
import com.codecool.shop.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.codecool.shop.controller.api.auth.Auth.hashPassword;

@WebServlet(urlPatterns = "/api/register")
public class RegisterController extends HttpServlet implements ProductRequestInterface {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

        registerUser(req);

        try {
            resp.sendRedirect("/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void registerUser(HttpServletRequest req) {
        String registerUsername = req.getParameter("registerUsername");
        String registerPassword1 = req.getParameter("registerPassword1");
        String registerPassword2 = req.getParameter("registerPassword2");
        System.out.println(registerUsername);
        System.out.println(registerPassword1);
        System.out.println(registerPassword2);
        if (registerPassword1.equals(registerPassword2)) {
            System.out.println("Registering new user");
            new UserService(Initializer.userDataStore)
                    .registerNewUser(new User(registerUsername, hashPassword(registerPassword1)));
            req.getSession().setAttribute("username", registerUsername);
        }
    }

}

