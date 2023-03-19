package org.example.servlets;

import org.example.models.UserProfile;
import org.example.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registrationPage.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserService userService = new UserService();

        if(!userService.addNewUser(login, password, email)){
            resp.setContentType("text/html;charset=utf-8");
            req.setAttribute("error", "Wrong format of login, password or e-mail");
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
