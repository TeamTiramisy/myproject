package com.dev.store.servlet;

import com.dev.store.dto.OrderCreateDto;
import com.dev.store.service.OrderService;
import com.dev.store.util.JspHelper;
import com.dev.store.util.LocalDateFormatter;
import com.sun.net.httpserver.HttpsServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    private final OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orders", orderService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("orders"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate date = LocalDateFormatter.format(req.getParameter("date"));

        orderService.deleteDateClose(date);
        resp.sendRedirect("/orders");
    }
}
