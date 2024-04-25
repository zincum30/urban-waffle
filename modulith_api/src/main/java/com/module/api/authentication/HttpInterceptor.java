package com.module.api.authentication;

import com.module.api.annotation.JwtRequired;
import com.module.api.authentication.jwt.JwtProvider;
import com.querydsl.core.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.lang.reflect.Method;


@Component
@RequiredArgsConstructor
public class HttpInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;
    private static final String AUTHORIZATION_HEADER = "AUTHORIZATION";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String jwt = resolveToken(request);

        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(JwtRequired.class) || handlerMethod.getBeanType().isAnnotationPresent(JwtRequired.class)) {
            try {
                Authentication authentication = jwtProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception ex) {
                // System.out.println(ex.getLocalizedMessage());
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            }
        }
        return true;
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (!StringUtils.isNullOrEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
