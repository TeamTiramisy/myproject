package com.dev.store.servlet;

import com.dev.store.dto.TechnicReadDto;
import com.dev.store.dto.UserReadDto;
import com.dev.store.service.BasketService;
import com.dev.store.service.OrderService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ordering")
public class OrderingServlet extends HttpServlet {

    private final OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserReadDto userReadDto = (UserReadDto) req.getSession().getAttribute("user");
        Long usersId = userReadDto.getId();

        req.setAttribute("baskets", orderService.findAllBasket(usersId));


        req.getRequestDispatcher(JspHelper.getPath("ordering"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long usersId = Long.valueOf(req.getParameter("id"));
        String[] amounts = req.getParameterValues("amount");

        orderService.save(usersId, amounts);

        resp.sendRedirect("/myOrders");

    }
}
