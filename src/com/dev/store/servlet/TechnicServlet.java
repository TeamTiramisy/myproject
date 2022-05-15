package com.dev.store.servlet;

import com.dev.store.service.TechnicService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/technic")
public class TechnicServlet extends HttpServlet {

    private final TechnicService technicService = TechnicService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var category = String.valueOf(req.getParameter("category"));
        req.setAttribute("categories", technicService.findAllByCategory(category));

        req.getRequestDispatcher(JspHelper.getPath("technic"))
                .forward(req, resp);
    }
}
