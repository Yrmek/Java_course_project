package com.onlinestore.filters;

import com.onlinestore.entities.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        boolean isLoginRequest = uri.endsWith("login.jsp") || uri.endsWith("/login");

        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        boolean isLoggedIn = user != null;

        // ✅ Разрешаем доступ к странице логина и процессу логина
        if (isLoginRequest) {
            chain.doFilter(request, response);
            return;
        }

        // ✅ Если не авторизован → на логин
        if (!isLoggedIn) {
            httpResponse.sendRedirect(contextPath + "/login.jsp");
            return;
        }

        // ✅ Ограничиваем доступ к админке
        if (uri.startsWith(contextPath + "/admin") && !"ROLE_ADMIN".equals(user.getRole())) {
            httpResponse.sendRedirect(contextPath + "/access-denied.jsp");
            return;
        }

        // ✅ Всё ок — пропускаем дальше
        chain.doFilter(request, response);
    }
}
