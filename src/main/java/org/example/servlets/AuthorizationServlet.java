package org.example.servlets;

import org.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("authorizationPage.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if(login == null || password == null || login == "" || password == ""){
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write("Wrong format of login, password or e-mail ");
            return;
        }
        UserService userService = new UserService();

        if(!userService.isReal(login, password)){
            req.setAttribute("error", "User doesn't exist");
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }else{
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(3600);
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            resp.sendRedirect(req.getContextPath() + "/files");
        }
    }
}
