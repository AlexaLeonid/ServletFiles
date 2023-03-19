package org.example.filter;

import org.example.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=UTF-8");

        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession(false);

        boolean isLoggedIn = false;
        if(session != null){
            Object login = session.getAttribute("login");
            Object password = session.getAttribute("password");
            if(login != null && password != null){
                UserService userService = new UserService();
                isLoggedIn = userService.isReal(login.toString(), password.toString());
            }
        }
        String logInURI = req.getContextPath() + "/authorization";
        String signUpURI = req.getContextPath() + "/registration";

        boolean isLogInReq = req.getRequestURI().equals(logInURI);
        boolean isSignUpReq = req.getRequestURI().equals(signUpURI);

        if(isSignUpReq){
            filterChain.doFilter(servletRequest, servletResponse);
        }else if(isLoggedIn && isLogInReq){
            resp.sendRedirect(req.getContextPath() + "/files");
        }else if(isLoggedIn || isLogInReq){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            resp.sendRedirect(logInURI);
        }
    }
}
