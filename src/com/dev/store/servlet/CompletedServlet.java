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
import java.time.LocalDate;

@WebServlet("/orders/completed")
public class CompletedServlet extends HttpServlet {

    private final OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orders", orderService.findAllByStatus("ACCEPTED"));

        req.getRequestDispatcher(JspHelper.getPath("completed"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        OrderCreateDto createDto = OrderCreateDto.builder()
                .status("COMPLETED")
                .dateClose(LocalDate.now().toString())
                .build();

        orderService.update(id, createDto);

        resp.sendRedirect("/orders/completed");
    }
}
