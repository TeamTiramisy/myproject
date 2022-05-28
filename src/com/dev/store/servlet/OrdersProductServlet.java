package com.dev.store.servlet;

import com.dev.store.dto.OrderCreateDto;
import com.dev.store.service.OrderService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ordersProduct")
public class OrdersProductServlet extends HttpServlet {

    private final OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        req.setAttribute("orders", orderService.findAllByProduct(name));

        req.getRequestDispatcher(JspHelper.getPath("ordersProduct"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        OrderCreateDto createDto = OrderCreateDto.builder()
                .status("REJECTED")
                .build();

        orderService.update(id, createDto);

        resp.sendRedirect("/orders/processing");
    }
}
