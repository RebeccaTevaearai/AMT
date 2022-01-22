package controller;

import service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Controller that manage the user account
 */
@WebServlet (name = "AccountServlet", value = "/account")
public class AccountController extends HttpServlet {

    /**
     * AccountController init
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
        this.doPost(req, resp);
    }

    /**
     * Serve the account page if user is connected
     * @param req Servlet request
     * @param resp Servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            if (AuthorizationService.isUserAllowed("account.jsp", session.getAttribute("jwt").toString())) {
                req.getRequestDispatcher("WEB-INF/account.jsp").forward(req, resp);
            }

            req.setAttribute("msg", "error: access denied");
            req.getRequestDispatcher("/loginpage").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("msg", "error: token not valid");
            req.getRequestDispatcher("/loginpage").forward(req, resp);
        }
    }
}
