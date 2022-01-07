package controller;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import service.LoginService;
import service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CheckLoginController extends HttpServlet {

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
        AuthorizationService.initSession(session);
        req.setAttribute("cartService", session.getAttribute("cartService"));

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LoginService login = new LoginService();
        String loginResponse = login.login(username, password);

        if (loginResponse == null) {
            req.setAttribute("msg", "error: bad credentials");
            req.getRequestDispatcher("/login").forward(req,resp);

        } else {
            Object obj = JSONValue.parse(loginResponse);
            JSONObject jsonObj = (JSONObject)obj;

            if (jsonObj == null) {
                req.setAttribute("msg", "error: bad credentials");
                req.getRequestDispatcher("/login").forward(req,resp);
            }

            String jwt = (String)jsonObj.get("token");

            session.setAttribute("username", username);
            session.setAttribute("jwt", jwt);

            req.getRequestDispatcher("/accounttt").forward(req,resp);
        }
    }
}
