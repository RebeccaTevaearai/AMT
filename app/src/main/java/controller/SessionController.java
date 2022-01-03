package controller;

import service.LoginModel;
import service.SessionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Controller to verify session validity
 */
@WebServlet (name = "SessionServlet", value = "/session")
public class SessionController extends HttpServlet {
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
        SessionManager.initSession(session);
        req.setAttribute("cartService", session.getAttribute("cartService"));

        if (session.getAttribute("redirection") == null) {
            req.getRequestDispatcher("/").forward(req, resp);
        } else {

            String redirection = session.getAttribute("redirection").toString();

            if (session.getAttribute("jwt") == null) {
                req.getRequestDispatcher("/").forward(req, resp);

            } else {

                String jwt = session.getAttribute("jwt").toString();

                // if jwt is valid, perform the redirection. Else redirect to main page
                if (SessionManager.checkJWT(jwt)) {
                    req.getRequestDispatcher(redirection).forward(req, resp);
                } else {
                    req.getRequestDispatcher("/").forward(req, resp);
                }
            }
        }
    }

}
