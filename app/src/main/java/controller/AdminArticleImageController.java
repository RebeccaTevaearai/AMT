package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet (name="articleImageServlet", value="/articleImage")
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

                final String name = req.getParameter("name");
                final Part filePart = req.getPart("file");
                final String fileName = AddArticleService.getFileName(filePart);
                final String path = "/css/image/big";
                OutputStream out = null;
                InputStream filecontent = null;
                final PrintWriter writer = resp.getWriter();
            }

 */
        req.setAttribute("msg", "error: feature is not implemented (yet)");
        req.getRequestDispatcher("/management").forward(req, resp);
    }

}
