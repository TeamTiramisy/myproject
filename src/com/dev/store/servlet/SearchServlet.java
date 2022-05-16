package com.dev.store.servlet;

import com.dev.store.service.TechnicService;
import com.dev.store.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private final TechnicService technicService = TechnicService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        req.setAttribute("technics", technicService.findAllByName(name));

        req.getRequestDispatcher(JspHelper.getPath("search"))
                .forward(req, resp);
    }
}
