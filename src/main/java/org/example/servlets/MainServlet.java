package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/files")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathParam = req.getParameter("path");
        HttpSession session = req.getSession();
        Object login = session.getAttribute("login").toString();


        if (pathParam == null || !pathParam.startsWith("C:\\students\\" + login.toString())) {
            pathParam = "C:\\students\\" + login.toString();
        }

        File file = new File(pathParam);
        String parentDirectory = file.getParent();

        if (parentDirectory == null || !parentDirectory.startsWith("C:\\students\\" + login) ){
            parentDirectory = "C:\\students\\" + login;
            file = new File(parentDirectory);
        }
        if (file.isDirectory()) {

            req.setAttribute("path", pathParam);
            req.setAttribute("parentDirectory", parentDirectory);
            file.mkdir();
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

    public boolean isInDirectory(File homeFile, File childFile) throws IOException {
        String homePath = homeFile.getAbsolutePath();
        String childPath = childFile.getAbsolutePath();
        return childPath.contains(homePath);
      /*  homeFile = homeFile.;
        childFile = childFile.getCanonicalFile();

        File parentFile = childFile;
        while(parentFile != null){
            if(homeFile.equals(parentFile)){
                return true;
            }
            parentFile = parentFile.getParentFile();
        }
        return false;*/
    }
}