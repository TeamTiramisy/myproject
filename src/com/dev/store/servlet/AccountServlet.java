package com.dev.store.servlet;

import com.dev.store.dto.UserCreateDto;
import com.dev.store.dto.UserReadDto;
import com.dev.store.exception.ValidationException;
import com.dev.store.service.OrderService;
import com.dev.store.service.UserService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();
    private final OrderService orderService = OrderService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserReadDto userReadDto = (UserReadDto) req.getSession().getAttribute("user");
        Long id = userReadDto.getId();

        req.setAttribute("orders" ,orderService.findAllByStatusByUserId("ACCEPTED", id));

        req.getRequestDispatcher(JspHelper.getPath("account"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserReadDto userReadDto = (UserReadDto) req.getSession().getAttribute("user");
        Long id = userReadDto.getId();

        if (userReadDto != null) {
            UserCreateDto userCreateDto = UserCreateDto.builder()
                    .firstname(req.getParameter("firstname"))
                    .lastname(req.getParameter("lastname"))
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .tel(req.getParameter("tel"))
                    .address(req.getParameter("address"))
                    .role(userReadDto.getRole())
                    .gender(userReadDto.getGender())
                    .blackList(userReadDto.getBlackList())
                    .build();

            try {
                userService.update(id, userCreateDto);
                req.getSession().setAttribute("user", userService.findById(id).orElseThrow());
                resp.sendRedirect("/account");
            } catch (ValidationException exception) {
                req.setAttribute("errors", exception.getErrors());
                doGet(req, resp);
            }
        }
    }


}
