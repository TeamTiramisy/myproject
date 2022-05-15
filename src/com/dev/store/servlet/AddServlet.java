package com.dev.store.servlet;

import com.dev.store.dto.TechnicCreateDto;
import com.dev.store.entity.Category;
import com.dev.store.entity.Gender;
import com.dev.store.exception.ValidationException;
import com.dev.store.service.TechnicService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/add")
public class AddServlet extends HttpServlet {

    private final TechnicService technicService = TechnicService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", Category.values());

        req.getRequestDispatcher(JspHelper.getPath("add"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TechnicCreateDto technicCreateDto = TechnicCreateDto.builder()
                .name(req.getParameter("name"))
                .category(req.getParameter("category"))
                .description(req.getParameter("description"))
                .price(req.getParameter("price"))
                .amount(req.getParameter("amount"))
                .image(req.getPart("image"))
                .build();

        try {
            Long id = technicService.create(technicCreateDto);
            resp.sendRedirect("/product?id=" + id);
        } catch (ValidationException exception){
            req.setAttribute("errors", exception.getErrors());
            doGet(req, resp);
        }
    }
}
