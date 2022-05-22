package com.dev.store.servlet;

import com.dev.store.service.BasketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/clear")
public class ClearServlet extends HttpServlet {

    private final BasketService basketService = BasketService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String technicId = req.getParameter("technicId");

        if (basketService.deleteProduct(userId, technicId)){
            resp.sendRedirect("/basket");
        }
    }
}
