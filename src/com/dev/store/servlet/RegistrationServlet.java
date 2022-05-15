package com.dev.store.servlet;

import com.dev.store.dto.UserCreateDto;
import com.dev.store.entity.Gender;
import com.dev.store.entity.Role;
import com.dev.store.exception.ValidationException;
import com.dev.store.service.UserService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("genders", Gender.values());

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .firstname(req.getParameter("firstname"))
                .lastname(req.getParameter("lastname"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .tel(req.getParameter("tel"))
                .address(req.getParameter("address"))
                .role("USER")
                .gender(req.getParameter("gender"))
                .build();

        try {
            userService.create(userCreateDto);
            resp.sendRedirect("/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
