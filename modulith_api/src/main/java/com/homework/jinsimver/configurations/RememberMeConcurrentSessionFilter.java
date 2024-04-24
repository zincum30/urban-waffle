//package com.homework.jinsimver.configurations;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.RememberMeServices;
//import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
//import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//
//import java.io.IOException;
//
//public class RememberMeConcurrentSessionFilter extends RememberMeAuthenticationFilter {
//
//    private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();
//
//    public void setSessionAuthenticationStrategy(SessionAuthenticationStrategy sessionStrategy) {
//        this.sessionStrategy = sessionStrategy;
//    }
//
//    public RememberMeConcurrentSessionFilter(AuthenticationManager authenticationManager, RememberMeServices rememberMeServices) {
//        super(authenticationManager, rememberMeServices);
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        super.doFilter(request, response, filterChain);
//    }
//
//
//    @Override
//    public void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
//        sessionStrategy.onAuthentication(authResult, request, response);
//    }
//}
