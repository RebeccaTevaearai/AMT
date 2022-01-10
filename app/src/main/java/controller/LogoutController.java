package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * Controller to logout
 */
@WebServlet(name = "logoutServlet", value = "/logout")
public class LogoutController extends HttpServlet {

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

        HttpSession session = req.getSession();
        //session.invalidate();
        session.removeAttribute("username");
        session.removeAttribute("jwt");
        req.getRequestDispatcher("/login").forward(req, resp);

    }
}
