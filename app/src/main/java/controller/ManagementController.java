package controller;

import service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (name="ManagementServlet", value="/management")
public class ManagementController extends HttpServlet {

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

        try {
            if (AuthorizationService.isUserAllowed("management.jsp", session.getAttribute("jwt").toString())) {
                req.getRequestDispatcher("WEB-INF/management.jsp").forward(req, resp);
            }

            req.setAttribute("msg", "error: access denied");
            req.getRequestDispatcher("/").forward(req, resp);

        } catch (Exception e) {
            req.setAttribute("msg", "error: token not valid");
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }
}
