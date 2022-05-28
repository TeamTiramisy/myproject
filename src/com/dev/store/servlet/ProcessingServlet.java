package com.dev.store.servlet;

import com.dev.store.dto.OrderCreateDto;
import com.dev.store.service.OrderService;
import com.dev.store.util.JspHelper;
import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/orders/processing")
public class ProcessingServlet extends HttpServlet {

    private final OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orders", orderService.findAllByStatus("PROCESSING"));

        req.getRequestDispatcher(JspHelper.getPath("processing"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        OrderCreateDto createDto = OrderCreateDto.builder()
                .status("ACCEPTED")
                .build();

        orderService.update(id, createDto);

        resp.sendRedirect("/orders/processing");
    }
}
