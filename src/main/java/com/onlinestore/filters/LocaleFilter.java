package com.onlinestore.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Locale;

@WebFilter("/*")
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        String lang = req.getParameter("lang");

        if (lang != null) {
            Locale locale = new Locale(lang);
            session.setAttribute("lang", locale);
        }

        chain.doFilter(request, response);
    }
}
