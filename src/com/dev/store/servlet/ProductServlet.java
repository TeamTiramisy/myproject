package com.dev.store.servlet;

import com.dev.store.service.TechnicService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private final TechnicService technicService = TechnicService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = Long.valueOf(req.getParameter("id"));

        req.setAttribute("product", technicService.findById(id).orElseThrow());

        req.getRequestDispatcher(JspHelper.getPath("product"))
                .forward(req, resp);
    }
}
