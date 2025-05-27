package com.ecommerce.user.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.LogRecord;

@Component
public class SessionFIlter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String path = req.getRequestURI();

        // Allow login or public paths without session
        if (path.endsWith("/login") || path.endsWith("/signup")) {
            chain.doFilter(request, response);
            return;
        }

        if (session != null && session.getAttribute("USER_ID") != null) {
            chain.doFilter(request, response); // Proceed
        } else {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Session expired or not logged in");
        }
    }

}
