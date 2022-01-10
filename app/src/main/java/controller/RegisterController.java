package controller;

import service.LoginService;
import service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        req.getRequestDispatcher("register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // get form parameters
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (LoginService.register(username, password)) {
            req.setAttribute("msg", "success: account created");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        } else {
            req.setAttribute("msg", "error: bad credentials");
        }

        req.getRequestDispatcher("register.jsp").forward(req,resp);

    }
}
