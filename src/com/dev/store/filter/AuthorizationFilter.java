package com.dev.store.filter;

import com.dev.store.dto.UserReadDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of("/login", "/registration");

    private static final Set<String> ADMIN_PATH = Set.of("/admin", "/add", "/users" , "/user", "/update");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(uri) || isUserLoginIn(servletRequest)) {
            if (isAdmin(servletRequest)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (isAdminPath(uri)){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect("/");
            }
        } else {
                ((HttpServletResponse) servletResponse).sendRedirect("/login");
            }
        }

    private boolean isAdmin(ServletRequest servletRequest) {
        var user = (UserReadDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null && user.getRole().equals("ADMIN");
    }

    private boolean isAdminPath(String uri) {
        return ADMIN_PATH.stream().noneMatch(uri::startsWith);
    }

    private boolean isUserLoginIn(ServletRequest servletRequest) {
        var user = (UserReadDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }
}
