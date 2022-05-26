package com.dev.store.servlet;

import com.dev.store.dto.BasketCreateDto;
import com.dev.store.dto.BasketReadDto;
import com.dev.store.dto.TechnicReadDto;
import com.dev.store.dto.UserReadDto;
import com.dev.store.service.BasketService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.postgresql.util.PSQLException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/basket")
public class BasketServlet extends HttpServlet {

    private final BasketService basketService = BasketService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserReadDto userReadDto = (UserReadDto) req.getSession().getAttribute("user");
        Long users_id = userReadDto.getId();

        req.setAttribute("baskets", basketService.findAllBasket(users_id));

        req.getRequestDispatcher(JspHelper.getPath("basket"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserReadDto userReadDto = (UserReadDto) req.getSession().getAttribute("user");
        Long users_id = userReadDto.getId();
        Long technic_id = Long.valueOf(req.getParameter("id"));

        Optional<BasketReadDto> technic = basketService.findByUsersIdAndTechnicId(users_id, technic_id);

        if (userReadDto != null && technic.isEmpty()) {
                BasketCreateDto basketCreateDto = BasketCreateDto.builder()
                        .users_id(users_id)
                        .technic_id(technic_id)
                        .build();

            basketService.create(basketCreateDto);

            var prevPage = req.getHeader("referer");
            var page = prevPage != null ? prevPage : "/";
            resp.sendRedirect(page);
        } else {
            var prevPage = req.getHeader("referer");
            var page = prevPage != null ? prevPage : "/";
            resp.sendRedirect(page);
        }
    }
}
