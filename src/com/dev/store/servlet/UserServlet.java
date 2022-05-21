package com.dev.store.servlet;

import com.dev.store.dto.UserCreateDto;
import com.dev.store.entity.BlackList;
import com.dev.store.entity.Category;
import com.dev.store.entity.Role;
import com.dev.store.service.UserService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        req.setAttribute("roles", Role.values());
        req.setAttribute("list", BlackList.values());

        req.setAttribute("user", userService.findById(id).orElseThrow());

        req.getRequestDispatcher(JspHelper.getPath("user"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        UserCreateDto userCreateDto = UserCreateDto.builder()
                .role(req.getParameter("role"))
                .blackList(req.getParameter("blacklist"))
                .build();
        userService.updateRoleAndBlacklist(id, userCreateDto);

        resp.sendRedirect("/user?id=" + id);

    }
}
