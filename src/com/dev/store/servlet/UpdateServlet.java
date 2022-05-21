package com.dev.store.servlet;

import com.dev.store.dto.TechnicCreateDto;
import com.dev.store.entity.Category;
import com.dev.store.exception.ValidationException;
import com.dev.store.service.TechnicService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    private final TechnicService technicService = TechnicService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        req.setAttribute("categories", Category.values());
        req.setAttribute("product", technicService.findById(id).orElseThrow());

        req.getRequestDispatcher(JspHelper.getPath("update"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        TechnicCreateDto technicCreateDto = TechnicCreateDto.builder()
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .price(req.getParameter("price"))
                .amount(req.getParameter("amount"))
                .build();

        try {
            technicService.update(id, technicCreateDto);
            resp.sendRedirect("/update?id=" + id);
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
