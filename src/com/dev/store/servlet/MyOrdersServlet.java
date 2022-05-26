package com.dev.store.servlet;

import com.dev.store.dto.UserReadDto;
import com.dev.store.service.OrderService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/myOrders")
public class MyOrdersServlet extends HttpServlet {

    private final OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserReadDto userReadDto = (UserReadDto) req.getSession().getAttribute("user");
        Long usersId = userReadDto.getId();

        req.setAttribute("orders", orderService.findAllByUserId(usersId));

        req.getRequestDispatcher(JspHelper.getPath("myorders"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        if(orderService.delete(id)){
            resp.sendRedirect("/myOrders");
        }
    }
}
