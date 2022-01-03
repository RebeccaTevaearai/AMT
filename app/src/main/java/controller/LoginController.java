package controller;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import service.LoginModel;
import service.SessionManager;

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
        HttpSession session = req.getSession();
        SessionManager.initSession(session);
        req.setAttribute("cartService", session.getAttribute("cartService"));

        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        SessionManager.initSession(session);
        req.setAttribute("cartService", session.getAttribute("cartService"));

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LoginModel login = new LoginModel();
        String loginResponse = login.login(username, password);

        if (loginResponse == null) {
            req.setAttribute("msg", "error: bad credentials");
            req.getRequestDispatcher("login.jsp").forward(req,resp);

        } else {
            Object obj = JSONValue.parse(loginResponse);
            JSONObject jsonObj = (JSONObject)obj;

            String jwt = (String)jsonObj.get("token");

            session.setAttribute("username", username);
            session.setAttribute("jwt", jwt);

            //req.getRequestDispatcher("/account").forward(req,resp);
            session.setAttribute("redirection", "account.jsp");
            req.getRequestDispatcher("/session").forward(req, resp);
        }

    }
}
