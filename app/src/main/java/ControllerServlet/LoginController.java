package ControllerServlet;

import Model.LoginModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.Cookie;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("logout") != null) {
            HttpSession session = req.getSession(false);
            session.invalidate();
        }
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LoginModel login = new LoginModel();
        String response = login.login(username, password);
        req.setAttribute("msg", response);
        //req.getRequestDispatcher("login.jsp").forward(req,resp);


        if (true) {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("token", "test");
            //session.setAttribute("id", id);
            //session.setAttribute("role", role);

            req.getRequestDispatcher("account.jsp").forward(req,resp);
        } else {
            req.setAttribute("msg", "error: bad credentials");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }

    }
}
