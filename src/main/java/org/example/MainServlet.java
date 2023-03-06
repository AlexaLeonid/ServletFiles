package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/files")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathParam = req.getParameter("path");

        if (pathParam == null) {
            resp.sendRedirect("http://localhost:8000/ServletFiles/files?path=C:/Users/Sasacompik/OneDrive");
            return;
        }

        req.setAttribute("path", pathParam);
        File file = new File(pathParam);
        String parentDirectory = file.getParent();
        req.setAttribute("parentDirectory", parentDirectory);
        if(file.isDirectory()){
            File folder = new File(pathParam);
            File[] files = folder.listFiles();

            req.setAttribute("file", file);
            req.setAttribute("files", files);

            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            req.setAttribute("dateFormat", dateFormat);
            req.setAttribute("dateGeneration", dateFormat.format(date));

            req.getRequestDispatcher("mainPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("POST method isn't available");
    }
}