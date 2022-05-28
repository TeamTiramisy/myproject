package com.dev.store.servlet;

import com.dev.store.service.OrderService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/orders/user")
public class OrdersUserServlet extends HttpServlet {

    private final OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long usersId = Long.valueOf(req.getParameter("id"));

        req.setAttribute("orders", orderService.findAllByUserId(usersId));

        req.getRequestDispatcher(JspHelper.getPath("ordersUser"))
                .forward(req, resp);
    }
}
