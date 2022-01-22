package controller;

import service.ImageQueries;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet (name="articleImageServlet", value="/articleImage")
@MultipartConfig
public class AdminArticleImageController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
/*
        try {
            if (AuthorizationService.isUserAllowed("/articleImage",
                    session.getAttribute("jwt").toString())) {
 */

        //String path = "/home/tevaearai/Documents/HEIG-VD/BA5/AMT/Projet/AMT/app/src/main/webapp/css/images/big/";
        String path = "../webapps" + req.getContextPath() + "/css/images/big/";
        String p = "/big/";

        Part filePart = req.getPart("image");
        Long id = (long) Integer.parseInt(req.getParameter("id"));

        String fileName = filePart.getSubmittedFileName();
        InputStream is = filePart.getInputStream();

        try {
            Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);

            ImageQueries.addImageToArticle(id, p + fileName);

            req.setAttribute("msg", "success: image added");
            req.getRequestDispatcher("/management").forward(req, resp);

        } catch(Exception e) {

            req.setAttribute("msg", "error: didn't work");
            req.getRequestDispatcher("/management").forward(req, resp);
        }
        req.setAttribute("msg", "error: feature is not implemented (yet)");
        req.getRequestDispatcher("/management").forward(req, resp);
    }

}
