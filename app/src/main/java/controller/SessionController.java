package controller;

import service.LoginModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller to verify session validity
 */
@WebServlet (name = "SessionServlet", value = "/session")
public class SessionController extends HttpServlet {
    /**
     * SessionController init
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     *
     * @param req Servlet request
     * @param resp Servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     *
     * @param req Servlet request
     * @param resp Servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    req.getRequestDispatcher("account.jsp").forward(req, resp);
                }
            }
        }

        req.getRequestDispatcher("/").forward(req, resp);
    }

}
