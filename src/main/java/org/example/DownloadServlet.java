package org.example;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/files/download")
public class DownloadServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("GET method isn't available");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rawPath = req.getParameter("path");

        if (rawPath != null) {
            byte[] bytes = rawPath.getBytes(StandardCharsets.ISO_8859_1);
            String path = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(path);
            String fileName = Paths.get(path).getFileName().toString();
            resp.setContentType("text/plain");
            resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            try (InputStream in = new FileInputStream(path); OutputStream out = resp.getOutputStream()) {
                byte[] buffer = new byte[1000000000];
                int numBytesRead;
                while ((numBytesRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, numBytesRead);
                }
            } catch (FileNotFoundException e) {
                resp.sendError(404);
            }
            resp.sendRedirect(req.getRequestURL().toString());
        } else {
            resp.sendError(404);
        }
    }
}
