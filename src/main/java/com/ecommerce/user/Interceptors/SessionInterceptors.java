package com.ecommerce.user.Interceptors;

import com.ecommerce.user.service.RedisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Optional;

@Component
public class SessionInterceptors implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        String path = request.getRequestURI();

        if(path.endsWith("/login") || path.endsWith("/signup")){
            return true;
        }

        String sessionId = request.getHeader("suid");

        if (sessionId == null || !isValidSession(sessionId)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid session");
            return false;
        }

        return true;
    }

    private boolean isValidSession(String sessionId) {
        // Your session validation logic here
        Object user = redisService.getUser(sessionId);
        if(user != null){
            return true;
        }

        return false;

    }
}
