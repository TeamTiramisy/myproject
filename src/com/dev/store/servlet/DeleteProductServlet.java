package com.dev.store.servlet;

import com.dev.store.service.TechnicService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletion")
public class DeleteProductServlet extends HttpServlet {

    private final TechnicService technicService = TechnicService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        if (technicService.delete(id)) {
            resp.sendRedirect("/");
        }
    }
}
